(ns roughcljs.utils
  (:require
   [clojure.string :as string]
   [com.rpl.specter :as sp])
  )

;; Handle the flat 'style' string value to get a clojure hashmap
(defn- string-escape [s]
  (str "\"" s "\""))

(defn- string-unescape [s]
  (string/replace s "\"" ""))

(defn- style-str->js-json [arg]
  (-> arg
      butlast
      (#(apply str %))
      (#(string-unescape %))
      (string/split  #"; ")
      (#(map (fn [field] (string/split field #": ")) %))
      (#(for [[k v] %]
          [(str (string-escape k) ": " (string-escape v))]))
      flatten
      (#(string/join ", " %))
      (#(str "{" % "}"))
      ))

(defn- js-json->hashmap [arg]
  (-> arg
      (#(.parse js/JSON %))
      (#(js->clj % :keywordize-keys true))
      ))

(defn- style-str->hashmap [arg]
  (-> arg
      style-str->js-json
      js-json->hashmap
      ))

(defn fix-style-field [hiccup-node]
  "Fixing the style field with specter"
  (sp/transform
   [(sp/srange-dynamic #((constantly 2)) #(count %))
    sp/ALL
    (sp/nthpath 1)
    :style
    ]
   style-str->hashmap
   hiccup-node)
  )

(defn apply-group-option [hiccup-node group-option]
  "Apply option to the surrounding ':g' tag"
  (sp/transform
   [(sp/nthpath 1)
    ]
   #(merge % group-option)
   hiccup-node)
  )

(defn- rename-key [hashmap old-key new-key]
  (if (old-key hashmap)
    (let [value (old-key hashmap)]
      (-> hashmap
          (dissoc old-key)
          (merge {new-key value})))
    hashmap)
  )

(defn fix-group-option [hiccup-node]
  """
Rename option to comply with brower:
 :viewbox      -> :viewBox
 :patternunits -> :patternUnits
"""
  (sp/transform
   [(sp/nthpath 1)
    ]
   (fn [group-option]
     (-> group-option
         (rename-key :viewbox :viewBox)
         (rename-key :patternunits :patternUnits)
         ))
   hiccup-node))
