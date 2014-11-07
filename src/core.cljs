(ns dkbookmarks.core
  (:require [dommy.core :as dommy :refer-macros [sel1]]) )

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
  (dommy/set-text! (dommy/create-element :ul) text) )

(defn print-node
  ([node] (print-node node (append-element (sel1 :#root) (new-list))))
  ([node parent-ul]
     (append-element parent-ul (new-list-item (.-title node)))
     (let [child-ul (append-element parent-ul (new-list))]
       (doseq [child (.-children node)]
         (print-node child child-ul) ) ) ) )

(with-root-node print-node)

