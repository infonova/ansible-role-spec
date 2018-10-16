(ns ansible-role-spec.core
  (:require
    [clj-yaml.core :as yaml]
    [ansible-role-spec.watching :as watching])
  (:import (java.io File)))


(def project-path "sample_project")

(defn parse-yaml-file [file]
  (println (str project-path File/separator file))
  (yaml/parse-string (slurp (str project-path File/separator file))))

; config contains use cases to validate
; we detect role-calls to validate (aka plays with roles directive)
; we parse var-map for hosts relevant to role calls

(def cfg (parse-yaml-file "ars.yml"))

(defn get-relevant-plays [usecases]
  (for [{:keys [test-description
                playbook
                inventories
                limit] :as usecase} usecases]
    ;TODO extract relevant plays from playbooks-code while considering limit and inventory config
    [test-description playbook inventories limit]))

(get-relevant-plays cfg)

(comment
  (watching/watch! {} [project-path] #(println "foo" %&))
  ((into {} cfg) :playbook)
  ((first cfg) :test-description)
  ((second cfg) :test-description))