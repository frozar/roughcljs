(ns roughcljs.app
  (:require [reagent.core :as reagent]
            [roughcljs.core :as rough]
            )
  )

(defn all-shape []
  [:<>
   (rough/line 0 0 100 0
               {:rough-option {:fill "black"}
                :group-option {:id "line-id"
                               :transform "translate (20 50)"}})
   (rough/rectangle 0 0 200 100
                    {:rough-option {:fill "black"}
                     :group-option {:id "rectangle-id"
                                    :transform "translate (150 20)"
                                    :pointer-events "bounding-box"
                                    :on-click (fn [evt] (js/alert "Amazing event"))}})
   (rough/ellipse 0 0 200 100
                  {:rough-option {:fill "black"
                                  :fillStyle "zigzag-line"}
                   :group-option {:id "ellipse-id"
                                  :transform "translate (500 80)"}})
   (rough/circle 0 0 150
                 {:rough-option {:fill "black"}
                  :group-option {:id "circle-id"
                                 :transform "translate (700 80)"}})
   (rough/linearPath [[0, 10], [100, 20], [60, 120], [0, 100]]
                     {:rough-option {:fill "black"}
                      :group-option {:id "linearPath-id"
                                     :transform "translate (20 150)"}})
   (rough/polygon [[0, 10], [100, 20], [60, 120], [0, 100]]
                  {:rough-option {:fill "red"
                                  :fillStyle "dashed"}
                   :group-option {:id "polygon-id"
                                  :transform "translate (150 150)"}})
   (rough/arc 0 0 200 180 Math/PI (* Math/PI 1.6) true
              {:rough-option {:stroke "pink"
                              :strokeWidth 2
                              :fill "black"
                              :fillStyle "dots"
                              }
               :group-option {:id "arc-id"
                              :transform "translate (350 250)"}})
   (let [points (for [i (range 20)]
                  (let [x (-> i (* (/ 400 20)) (+ 10))
                        xdeg (* (/ Math/PI 100) x)
                        y (-> xdeg Math/sin (* 90) Math/round (+ 500))]
                    [x y]))]
     (rough/curve points
                  {:rough-option {:stroke "blue"
                                  :strokeWidth 2}
                   :group-option {:id "curve-id"
                                  :transform "translate (400 -200)"
                                  }}))
   (rough/path "M80 80 A 45 45, 0, 0, 0, 125 125 L 125 80 Z"
               {:rough-option {:fill "green"
                               :stroke "black"
                               :fillStyle "dots"
                               }
                :group-option {:id "path-id"
                               :transform "translate (-50 230)"
                               }})
   (rough/path "M37,17v15H14V17z M50,0H0v50h50z"
               {:rough-option {:fill "green"
                               :stroke "black"
                               :fillStyle "cross-hatch"
                               }
                :group-option {:id "path-id"
                               :transform "translate (120 300)"
                               }})
   (rough/path "M 0 100 L 200 100 L 100 300 z"
               {:rough-option {:fill "red"
                               :stroke "black"
                               :strokeWidth 2
                               :roughness 3
                               :fillStyle "zigzag"
                               }
                :group-option {:id "path-id"
                               :transform "translate (200 200)"
                               }})
   ]
  )

(defn current-page []
  [:svg {:id "svg-canvas"
         :style
         {:border     "none"
          :background "white"
          :position   "fixed"
          :top        0
          :left       0
          :height     "100%"
          :width      "100%"
          }
         }
   [all-shape]
   ]
  )

(defn mount-root [component]
  (reagent/render [component] (.getElementById js/document "app"))
  )

(defn ^:dev/after-load reload! []
  (mount-root current-page)
  )

(defn init! []
  (reload!)
  )
