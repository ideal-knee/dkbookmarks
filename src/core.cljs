(ns dkbookmarks.core
  (:require [dommy.core :as dommy :refer-macros [sel1]]
            [weasel.repl :as repl] ) )

(repl/connect "ws://localhost:9001")
(enable-console-print!)

(defn print-object [object]
  (.log js/console object) )

(defn with-root-node [callback]
  (.getTree js/chrome.bookmarks (fn [results] (callback (first results)))) )

(defn append-element [parent element]
  (dommy/append! parent element)
  element )

(defn new-list []
  (dommy/create-element :ul) )

(defn new-list-item [text]
  (dommy/set-text! (dommy/create-element :li) text) )

(defn print-node
  ([node] (print-node node (append-element (sel1 :#root) (new-list))))
  ([node parent-ul]
     (append-element parent-ul (new-list-item (let [title (.-title node)]
                                                (if (empty? title)
                                                  "untitled"
                                                  title ) )))
     (let [child-ul (append-element parent-ul (new-list))]
       (doseq [child (.-children node)]
         (print-node child child-ul) ) ) ) )

(with-root-node print-node)
