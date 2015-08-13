(ns dkbookmarks.core
  (:require [dommy.core :as dommy :refer-macros [sel1]]
            [weasel.repl :as repl]
            [ajax.core :as ajax] ) )

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

(defn depth-first-traverse [consume-fn node]
  (let [child-consume-fn (consume-fn node)]
    (doseq [child (.-children node)]
      (depth-first-traverse child-consume-fn child) ) ) )

(defn print-node
  ([node] (print-node (append-element (sel1 :#root) (new-list)) node))
  ([parent-ul node]
   (append-element parent-ul (new-list-item (let [title (.-title node)]
                                              (if (empty? title)
                                                "untitled"
                                                title ) )))
   (let [child-ul (append-element parent-ul (new-list))]
     (partial print-node child-ul) ) ) )

;; Render as HTML list
(with-root-node (partial depth-first-traverse print-node))

;; Print to console
(defn do-tree [f]
  (with-root-node (partial depth-first-traverse
                           (letfn [(f-caller [o]
                                     (f o)
                                     f-caller )]
                             f-caller ) )) )

#_(do-tree print-object)
#_(do-tree #(.log js/console (.-title %)))

;; AJAX
#_(ajax/GET "http://localhost:3000/" :handler #(print-object %))

;; Accumulate nodes
(def nodes (atom []))
(do-tree #(swap! nodes conj %))
