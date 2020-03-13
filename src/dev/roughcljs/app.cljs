(ns roughcljs.app
  (:require [reagent.dom :as rd]
            [roughcljs.core :as rough]
            )
  )

(def R-shape
  "m 101.51716,386.37069 h 11.136 c 7.488,0 15.168,2.304 19.392,5.76 5.568,4.416 8.448,11.328 9.792,24 l 0.96,9.792 c 2.304,21.696 9.408,30.528 24.576,30.528 15.744,0 23.232,-10.56 23.808,-33.6 h -8.064 c -0.768,13.248 -3.072,19.008 -8.256,19.008 -4.416,0 -6.336,-3.264 -9.6,-16.512 l -3.072,-12.096 c -4.224,-17.664 -11.712,-25.728 -26.688,-29.568 27.648,-3.84 40.896,-14.976 40.896,-34.368 0,-21.888 -18.432,-34.368 -50.688,-34.368 H 59.277156 v 9.024 h 3.456 c 15.552,0 17.28,0.96 17.28,9.024 v 5.568 91.584 5.376 c 0,8.064 -1.728,9.024 -17.28,9.024 h -3.456 v 9.024 h 62.976004 v -9.024 h -3.456 c -15.552,0 -17.28,-0.96 -17.28,-9.024 v -5.376 z m 0,-9.024 v -42.24 c 0,-7.68 0.192,-8.256 2.112,-9.792 1.536,-1.152 2.88,-1.344 11.136,-1.344 h 10.368 c 19.008,0 27.648,8.064 27.648,25.536 0,9.792 -2.496,17.28 -7.488,21.312 -4.8,4.032 -13.44,6.528 -22.656,6.528 z")

(def o-shape
  "m 241.41916,361.21869 c -23.424,0 -41.856,20.928 -41.856,47.808 0,26.304 18.432,47.424 41.472,47.424 23.04,0 41.472,-21.12 41.472,-47.616 0,-26.304 -18.432,-47.616 -41.088,-47.616 z m 0,8.256 c 13.44,0 20.928,14.016 20.928,39.36 0,25.536 -7.488,39.36 -21.312,39.36 -13.824,0 -21.312,-13.824 -21.312,-39.168 0,-26.112 7.296,-39.552 21.696,-39.552 z")

(def u-shape
  "m 368.13916,454.91469 34.944,-1.344 v -8.256 h -4.8 c -10.56,0 -12.096,-1.728 -12.096,-14.016 v -67.2 l -36.096,2.112 v 8.256 h 9.792 c 7.104,0 8.256,1.344 8.256,9.6 v 23.616 c 0,10.944 -1.152,17.28 -4.032,23.616 -4.032,8.448 -11.712,13.44 -21.12,13.44 -5.952,0 -10.944,-2.112 -13.44,-5.952 -2.496,-3.648 -2.88,-5.568 -2.88,-16.128 v -58.56 l -33.6,2.112 v 8.256 h 7.296 c 7.104,0 8.256,1.344 8.256,9.6 v 41.472 c 0,9.792 0.768,14.208 3.648,18.624 5.184,7.872 14.4,12.288 25.92,12.288 12.672,0 21.504,-5.184 29.952,-17.472 z")

(def g-shape
  "m 444.04216,439.36269 c -10.56,-0.192 -11.136,-0.384 -13.44,-1.728 -1.344,-0.768 -2.304,-2.88 -2.304,-4.992 0,-5.76 5.184,-8.256 18.048,-8.64 18.24,-0.384 21.12,-0.768 27.456,-3.648 11.52,-5.184 18.048,-15.168 18.048,-27.84 0,-7.68 -1.728,-12.48 -7.104,-18.816 3.264,-3.456 4.992,-4.608 6.528,-4.608 0.96,0 1.536,0.576 1.92,2.688 0.768,4.608 3.84,7.104 8.64,7.104 4.8,0 8.64,-4.032 8.64,-9.216 0,-6.528 -5.184,-11.136 -12.672,-11.136 -6.144,0 -9.6,1.92 -18.432,9.984 -8.064,-5.376 -14.208,-7.296 -23.808,-7.296 -22.848,0 -37.824,12.672 -37.824,31.872 0,11.328 6.144,21.12 16.128,25.728 -12.864,2.88 -18.24,8.64 -18.24,19.2 0,8.448 3.84,13.44 12.864,16.512 -11.904,4.224 -17.664,10.368 -17.664,18.24 0,12.672 14.784,19.776 40.512,19.776 32.064,0 48.192,-10.176 48.192,-30.336 0,-9.6 -4.416,-17.28 -11.52,-20.16 -4.992,-1.92 -11.136,-2.688 -24.384,-2.688 z m 11.328,-69.888 c 12.096,0 18.816,8.448 18.816,23.232 0,14.976 -7.296,24 -19.584,24 -11.904,0 -19.392,-9.024 -19.392,-23.424 0,-15.168 7.296,-23.808 20.16,-23.808 z m -1.728,86.4 c 15.936,0 17.088,0 19.968,0.384 7.68,0.768 12.48,4.992 12.48,11.136 0,10.944 -12.48,17.28 -33.984,17.28 -16.128,0 -25.344,-4.8 -25.344,-13.44 0,-6.336 3.456,-10.56 12.672,-15.36 z")

(def h-shape
  "m 547.93516,312.06669 -35.52,2.112 v 8.256 h 9.216 c 7.104,0 8.256,1.344 8.256,9.6 v 97.92 7.68 c 0,7.68 -2.112,9.024 -14.4,9.024 h -0.768 v 6.912 h 48.576 v -6.912 h -0.768 c -12.48,0 -14.592,-1.344 -14.592,-9.024 v -7.68 -19.776 c 0,-9.6 1.344,-18.24 3.456,-22.656 4.032,-8.64 12.672,-14.592 20.928,-14.592 4.992,0 10.752,2.88 13.632,6.72 2.88,4.224 4.224,9.984 4.224,19.392 v 30.912 7.68 c 0,7.68 -2.112,9.024 -14.592,9.024 h -0.768 v 6.912 h 48.576 v -6.912 h -0.768 c -12.288,0 -14.4,-1.344 -14.4,-9.024 v -7.68 -30.144 c 0,-13.824 -0.576,-17.28 -4.032,-23.808 -4.8,-9.216 -14.4,-14.784 -25.92,-14.784 -12.288,0 -21.696,5.76 -30.336,18.624 z")

(def C-shape
  "m 749.59816,313.60269 h -7.296 l -7.68,11.328 c -13.632,-9.984 -21.12,-12.864 -33.984,-12.864 -18.624,0 -33.984,7.488 -47.04,23.04 -12.288,14.592 -18.048,30.72 -18.048,50.496 0,41.28 27.072,70.848 64.896,70.848 30.72,0 50.304,-18.048 54.72,-50.304 l -9.024,-1.536 c -1.92,10.176 -4.224,17.088 -7.68,22.848 -7.872,13.248 -20.16,19.968 -35.712,19.968 -28.416,0 -41.856,-19.776 -41.856,-61.056 0,-21.696 2.88,-36.288 9.408,-47.808 5.952,-10.752 17.856,-17.472 30.144,-17.472 13.44,0 25.344,7.104 32.64,19.392 3.648,6.336 6.528,13.824 10.944,28.608 h 8.448 z")

(def L-shape
  "m 889.69216,400.96269 h -8.448 c -1.92,13.056 -6.528,26.112 -11.52,32.448 -5.952,7.488 -17.088,11.136 -33.216,11.136 h -8.064 c -5.76,0 -10.56,-1.152 -12.288,-2.88 -1.344,-1.152 -1.536,-2.304 -1.536,-6.336 v -96.768 -5.568 c 0,-8.064 1.728,-9.024 17.28,-9.024 h 3.456 v -9.024 h -62.976 v 9.024 h 3.456 c 15.552,0 17.28,0.96 17.28,9.024 v 5.568 91.392 5.568 c 0,8.064 -1.728,9.024 -17.28,9.024 h -3.456 v 9.024 h 113.28 z")

(def J-shape
  "m 976.08616,338.56269 v -5.568 c 0,-8.064 1.728,-9.024 17.28,-9.024 h 3.648 v -9.024 h -62.976 v 9.024 h 3.648 c 15.36,0 17.088,0.96 17.088,9.024 v 5.568 61.824 c 0,16.896 -0.576,24.576 -2.496,30.336 -3.648,10.944 -11.52,16.704 -22.656,16.704 -8.064,0 -15.552,-3.264 -19.584,-8.448 -2.112,-2.688 -3.648,-6.528 -3.648,-8.832 0,-1.728 0.96,-3.072 2.304,-3.072 h 0.576 c 3.264,0.768 4.992,0.96 7.104,0.96 6.912,0 12.096,-5.952 12.096,-13.824 0,-8.064 -5.952,-13.824 -14.4,-13.824 -11.136,0 -19.2,9.792 -19.2,23.424 0,9.216 4.224,18.624 11.136,24.384 6.336,5.568 15.36,8.256 27.072,8.256 16.896,0 29.184,-5.568 35.904,-16.32 4.992,-7.68 7.104,-19.392 7.104,-38.016 z")

(def S-shape
  "m 1105.6172,313.79469 h -7.104 l -7.68,11.52 c -9.024,-8.64 -21.312,-13.248 -34.944,-13.248 -25.152,0 -42.048,16.128 -42.048,40.128 0,20.928 10.368,31.296 38.592,38.592 l 18.24,4.608 c 14.208,3.648 15.552,4.032 19.584,7.104 5.76,4.416 8.832,10.752 8.832,18.24 0,7.68 -2.88,14.016 -8.64,19.2 -6.336,5.568 -12.672,7.68 -23.232,7.68 -14.208,0 -24.384,-4.416 -33.408,-14.4 -8.064,-9.024 -12.096,-18.048 -14.976,-32.832 h -8.256 l 0.768,54.72 h 7.488 l 8.64,-13.056 c 12.864,10.56 23.616,14.4 40.32,14.4 28.224,0 46.272,-16.512 46.272,-42.24 0,-11.904 -4.032,-22.08 -11.52,-29.376 -5.184,-4.992 -12.672,-8.256 -28.032,-12.096 l -20.544,-5.184 c -17.088,-4.416 -25.152,-11.904 -25.152,-23.616 0,-13.44 10.944,-22.656 27.264,-22.656 13.44,0 24.384,5.76 32.064,16.704 5.568,7.872 9.024,15.936 11.52,25.728 h 8.256 z")

(defn roughcljs-logo []
  [:<>
   (let [paths (list R-shape
                     o-shape
                     u-shape
                     g-shape
                     h-shape
                     C-shape
                     L-shape
                     J-shape
                     S-shape
                     )]
     (for [[path i] (zipmap paths (range (count paths)))]
       (with-meta
         [rough/path
          path
          {:rough-option {:fill "#4285f4"
                          :stroke "black"
                          :strokeWidth 1
                          :roughness 1.3
                          :fillStyle "zigzag"
                          :seed 1
                          }
           :group-option {:id (str "path-" i)
                          }}]
         {:key i}
         )
       ))
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
   [:g {:transform "scale(0.5 0.5)"}
    [roughcljs-logo]]
   ]
  )

(defn mount-root [component]
  (rd/render [component] (.getElementById js/document "app"))
  )

(defn ^:dev/after-load reload! []
  (mount-root current-page)
  )

(defn init! []
  (reload!)
  )
