(ns tiy-clojure-map-filter.core
 (:require [clojure.string :as str]
           )
  )



 (defn -main []
       (println "Enter a category (Furniture, Alcohol, Toiletries, Shoes, Food, Jewelry:")
       (let [purchases (slurp "purchases.csv")

             ;splits purchases into vectors of lines
             purchases (str/split-lines purchases)
             purchases (map (fn [line] (clojure.string/split line #",")) purchases)
             headers (map keyword (first purchases))
             purchases (rest purchases)
             purchases (map (fn [line] (zipmap headers line)) purchases)

             category (read-line)
             purchases (filter (fn [line] (= (get line :category) category)) purchases)
             file-text (pr-str purchases)]
         (spit (str "filtered_purchases.edn") file-text)))