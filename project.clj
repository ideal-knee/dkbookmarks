(defproject dkbookmarks "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html" }

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2356"] ]
  :plugins [[lein-cljsbuild "1.0.3"]]
  :source-paths []

  :cljsbuild {:builds [{:source-paths ["src"]
                        :compiler {:output-to "gen/script.js"
                                   :optimizations :simple
                                   :pretty-print true } }]} )

