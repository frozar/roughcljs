(ns roughcljs.core
  (:require ["roughjs/bin/rough" :refer [default] :rename {default rough}]
            [roughcljs.utils :as utils]
            [hickory.core :as hickory]
            )
  )

(defn- meta-compute-shape [shape-key]
  (let [svg-js-elt (.createElement js/document "svg")
        rough-canvas (.svg rough svg-js-elt)
        ;; set the seed to draw a reproductive shape
        default-rough-option {:seed 7}
        rought-option->js (fn [rough-option]
                            (->> rough-option
                                 (merge default-rough-option)
                                 clj->js))]
    (case shape-key
      :line
      (fn [x y width height rough-option]
        (.line rough-canvas x y width height (rought-option->js rough-option)))
      :rectangle
      (fn [x y width height rough-option]
        (.rectangle rough-canvas x y width height (rought-option->js rough-option)))
      :ellipse
      (fn [x y width height rough-option]
        (.ellipse rough-canvas x y width height (rought-option->js rough-option)))
      :circle
      (fn [x y diameter rough-option]
        (.circle rough-canvas x y diameter (rought-option->js rough-option)))
      :linearPath
      (fn [points rough-option]
        (.linearPath rough-canvas (clj->js points) (rought-option->js rough-option)))
      :polygon
      (fn [points rough-option]
        (.polygon rough-canvas (clj->js points) (rought-option->js rough-option)))
      :arc
      (fn [x y width height start stop closed rough-option]
        (.arc rough-canvas x y width height start stop closed (rought-option->js rough-option)))
      :curve
      (fn [points rough-option]
        (.curve rough-canvas (clj->js points) (rought-option->js rough-option)))

      :path
      (fn [d rough-option]
        (let [path-rough-HTML-node
              (.path rough-canvas (clj->js d) (rought-option->js rough-option))

              pattern-rough-HTML-node
              (.-svg rough-canvas)]
          [path-rough-HTML-node pattern-rough-HTML-node]
          )
        )
      )))

(defn- parse-rough-js-node->hiccup [rough-js-node shape-key]
  (-> rough-js-node
      (#(case shape-key
          :any-shape-except-pattern (.-outerHTML %)
          :pattern (-> %
                    (.getElementsByTagName "pattern")
                    (aget 0)
                    (.-outerHTML)
                    )
          ))
      (hickory/parse-fragment)
      (#(map hickory/as-hiccup %))
      first
      ))

(defn- postprocess-hiccup [hiccup-node group-option]
  (-> hiccup-node
      (utils/fix-style-field)
      (utils/apply-group-option group-option)
      (utils/fix-group-option)
      ))

(defn- rough-js-node->hiccup
  ([rough-js-node group-option]
   (rough-js-node->hiccup rough-js-node group-option :any-shape-except-pattern))
  ([rough-js-node group-option shape-key]
   (-> rough-js-node
       (parse-rough-js-node->hiccup shape-key)
       (postprocess-hiccup group-option)
       )))

(defn- rough-hiccup [keyword & args]
  (let [option        (if (map? (last args)) (last args))
        rough-option  (:rough-option option)
        group-option  (:group-option option)
        compute-shape (meta-compute-shape keyword)
        ]
    (case keyword
      :line
      (let [[x1 y1 x2 y2] args
            rough-js-node (compute-shape x1 y1 x2 y2 rough-option)]
        (rough-js-node->hiccup rough-js-node group-option))
      :rectangle
      (let [[x y width height] args
            rough-js-node (compute-shape x y width height rough-option)]
        (rough-js-node->hiccup rough-js-node group-option))
      :ellipse
      (let [[x y width height] args
            rough-js-node (compute-shape x y width height rough-option)]
        (rough-js-node->hiccup rough-js-node group-option))
      :circle
      (let [[x y diameter] args
            rough-js-node (compute-shape x y diameter rough-option)]
        (rough-js-node->hiccup rough-js-node group-option))
      :linearPath
      (let [[points] args
            rough-js-node (compute-shape points rough-option)]
        (rough-js-node->hiccup rough-js-node group-option))
      :polygon
      (let [[points] args
            rough-js-node (compute-shape points rough-option)]
        (rough-js-node->hiccup rough-js-node group-option))
      :arc
      (let [[x y width height start stop closed] args
            rough-js-node (compute-shape x y width height start stop closed rough-option)]
        (rough-js-node->hiccup rough-js-node group-option))
      :curve
      (let [[points] args
            rough-js-node (compute-shape points rough-option)]
        (rough-js-node->hiccup rough-js-node group-option))
      :path
      (let [[d] args
            [path-rough-js-node pattern-rough-js-node] (compute-shape d rough-option)]
        (if (:fill rough-option)
          [:<>
           (rough-js-node->hiccup pattern-rough-js-node {} :pattern)
           (rough-js-node->hiccup path-rough-js-node group-option)
           ]
          (rough-js-node->hiccup path-rough-js-node group-option))
        )
      )))

(defn line
  ([x1 y1 x2 y2]
   (line x1 y1 x2 y2 nil))
  ([x1 y1 x2 y2 option]
   (rough-hiccup :line x1 y1 x2 y2 option)))

(defn rectangle
  ([x y width height]
   (rectangle x y width height nil))
  ([x y width height option]
   (rough-hiccup :rectangle x y width height option)))

(defn ellipse
  ([x y width height]
   (ellipse x y width height nil))
  ([x y width height option]
   (rough-hiccup :ellipse x y width height option)))

(defn circle
  ([x y diameter]
   (circle x y diameter nil))
  ([x y diameter option]
   (rough-hiccup :circle x y diameter option)))

(defn linearPath
  ([points]
   (linearPath points nil))
  ([points option]
   (rough-hiccup :linearPath points option)))

(defn polygon
  ([points]
   (polygon points nil))
  ([points option]
   (rough-hiccup :polygon points option)))

(defn arc
  ([x y width height start stop closed]
   (arc x y width height start stop closed nil))
  ([x y width height start stop closed option]
   (rough-hiccup :arc x y width height start stop closed option)))

(defn curve
  ([points]
   (curve points nil))
  ([points option]
   (rough-hiccup :curve points option)))

(defn path
  ([d]
   (path d nil))
  ([d option]
   (rough-hiccup :path d option)))
