(defproject frozar/roughcljs "0.1.1"
  :description "Clojurescript wrapper around roughjs library"
  :url "https://github.com/frozar/roughcljs"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}

  :dependencies [[org.clojure/clojurescript "1.10.597" :scope "provided"]
                 [hiccup "1.0.5"]
                 [hickory "0.7.1"]
                 [com.rpl/specter "1.1.3"]
                 ]

  :source-paths ["src/main"]

  :repositories
  {"clojars" {:url "https://clojars.org/frozar"
              :sign-releases false}}
  )
