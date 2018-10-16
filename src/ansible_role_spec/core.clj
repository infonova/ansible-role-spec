(ns ansible-role-spec.core
  (:require
    [clj-yaml.core :as yaml]
    [ansible-role-spec.watching :as watching])
  (:import (java.io File)))


; for now hardcode project-path to our local sample_project
(def project-path "sample_project")

(defn parse-yaml-file [file]
  (println (str "parsing: " project-path File/separator file))
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

; projects have use cases to test (defined in ars.yml)
;;; use cases are essentially ansible-playbook calls with various paramaters
; use cases have plays (among params etc.)

(def relevant-plays (get-relevant-plays cfg))

; plays have hosts
; hosts have var map

; plays have roles
; roles have specs

; in order to validate role specs they need the specific var map they would get passed in a real ansible-playbook run

(get-specs relevant-plays)

(comment
  (def watches (atom #{}))
  (swap! watches conj (watching/watch! {} [project-path] #(println "foo" %&)))
  (watching/stop! (first @watches))
                

  ((into {} cfg) :playbook)
  ((first cfg) :test-description)
  ((second cfg) :test-description))