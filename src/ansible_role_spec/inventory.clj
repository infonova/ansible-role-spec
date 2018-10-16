(ns ansible-role-spec.inventory
  (:require [clojure.string :as str])
  (:import (java.io File)))

(def project-path "sample_project")
;(def inventories ["production" "staging"])
(def inventories ["production"])

(defn parse-inventory
  "Given a set of inventory identifyers parse their respective folders.
  Result is a map holding all groups with all hosts and all their group and host vars."
  [project-path inventories]
  (map
    (fn [i] (->> i
                 (#(str project-path File/separator "inventories" File/separator % File/separator "inventory"))
                 (slurp)
                 (str/split-lines)
                 (remove empty?)
                 (reduce)))
    inventories))

(comment
  (parse-inventory project-path inventories)
  (empty? "")
  (map
    #(str/split-lines (slurp (str project-path File/separator "inventories" File/separator % File/separator "inventory")))
    inventories))