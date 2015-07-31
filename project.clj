(defproject dkbookmarks "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html" }

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "0.0-3308"]
                 [prismatic/dommy "1.0.0"]
                 [weasel "0.7.0"]
                 [cljs-ajax "0.3.14"] ]
  :plugins [[lein-cljsbuild "1.0.3"]
            [cider/cider-nrepl "0.9.1"] ]
  :source-paths []

  :profiles {:dev {:dependencies [[com.cemerick/piggieback "0.2.1"]
                                  [org.clojure/tools.nrepl "0.2.10"]]
                                    :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}}

  :cljsbuild {:builds [{:source-paths ["src"]
                        :compiler {:output-to "gen/script.js"
                                   :optimizations :simple
                                   :pretty-print true } }]} )
