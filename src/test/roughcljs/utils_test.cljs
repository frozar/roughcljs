(ns roughcljs.utils-test
  (:require [roughcljs.utils :as utils]
            [cljs.test :refer (deftest is)]))

(deftest style-str->js-json_basic
  (is
   (=
    (utils/style-str->js-json "stroke: rgb(0, 0, 0); stroke-width: 1; fill: none;")
    "{\"stroke\": \"rgb(0, 0, 0)\", \"stroke-width\": \"1\", \"fill\": \"none\"}")))

(deftest style-str->js-json_unescape-first
  (is
   (=
    (utils/style-str->js-json "stroke: none; stroke-width: 0; fill: url(\"#rough-8655511569012857\");")
    "{\"stroke\": \"none\", \"stroke-width\": \"0\", \"fill\": \"url(#rough-8655511569012857)\"}")))

(deftest js-json->hashmap_basic
  (is
   (=
    (utils/js-json->hashmap "{\"stroke\": \"rgb(0, 0, 0)\", \"stroke-width\": \"1\", \"fill\": \"none\"}")
    {:stroke "rgb(0, 0, 0)", :stroke-width "1", :fill "none"})))

(deftest style-str->hashmap_basic
  (is
   (=
    (utils/style-str->hashmap "stroke: rgb(0, 0, 0); stroke-width: 1; fill: none;")
    {:stroke "rgb(0, 0, 0)", :stroke-width "1", :fill "none"})))

(def training-data
  [:g
   {}
   [:path
    {:d "path1",
     :style "stroke: rgb(1, 1, 1); stroke-width: 1; fill: none;"}]
   [:path
    {:d "path2",
     :style "stroke: rgb(2, 2, 2); stroke-width: 1; fill: none;"}]])

(deftest fix-style-field_basic
  (is
   (=
    (utils/fix-style-field training-data)
    [:g
     {}
     [:path
      {:d "path1",
       :style {:stroke "rgb(1, 1, 1)", :stroke-width "1", :fill "none"}}]
     [:path
      {:d "path2",
       :style {:stroke "rgb(2, 2, 2)", :stroke-width "1", :fill "none"}}]])))

(deftest apply-group-option_basic
  (is
   (=
    (utils/apply-group-option training-data {:id "amazing-element"})
    [:g
     {:id "amazing-element"}
     [:path
      {:d "path1",
       :style "stroke: rgb(1, 1, 1); stroke-width: 1; fill: none;"}]
     [:path
      {:d "path2",
       :style "stroke: rgb(2, 2, 2); stroke-width: 1; fill: none;"}]]
    )))

(deftest rename-key_basic
  (is
   (=
    (utils/rename-key {:viewbox "0 0 100 100"
                       :patternunits "objectBoundingBox"}
                      :viewbox
                      :viewBox)
    {:viewBox "0 0 100 100"
     :patternunits "objectBoundingBox"}))
  (is
   (=
    (utils/rename-key {:viewbox "0 0 100 100"
                       :patternunits "objectBoundingBox"}
                      :patternunits
                      :patternUnits)
    {:viewbox "0 0 100 100"
     :patternUnits "objectBoundingBox"}))
  )

(def training-data-2
  [:pattern
   {:id "rough-4748955896075857",
    :x "0",
    :y "0",
    :width "1",
    :height "1",
    :viewbox "0 0 100 100",
    :patternunits "objectBoundingBox"}
   [:path
    {:d "M0 0", :style "stroke: black; stroke-width: 0.5; fill: none;"}]])

(deftest fix-group-option_basic
  (is
   (=
    (utils/fix-group-option training-data-2)
    [:pattern
     {:id "rough-4748955896075857",
      :x "0",
      :y "0",
      :width "1",
      :height "1",
      :viewBox "0 0 100 100",
      :patternUnits "objectBoundingBox"}
     [:path
      {:d "M0 0", :style "stroke: black; stroke-width: 0.5; fill: none;"}]]
    )))
