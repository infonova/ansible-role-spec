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

(defn parse-playbook
  "Recursively (import_playbook) parse playbook files.
  A parsed playbook is a sequence of plays which define roles to run."
  [playbook]
  (flatten (for [entry (parse-yaml-file playbook)]
             (if (entry :import_playbook)
               (parse-playbook (entry :import_playbook))
               entry))))

(comment
  (parse-playbook "site.yml"))


(defn expand-group [group-or-host]
  (if (inventory group-or-host)
    (inventory group-or-host)
    [group-or-host]))

(defn parse-hosts-definition [hosts-def-string]
  (let [groups-n-hosts (clojure.string/split hosts #",")]
    (mapcat expand-group groups-n-hosts)))

(defn part-of-limit? [limit hosts]
  (let [limitvec (clojure.string/split limit #"\,")
        hostsvec (parse-hosts-definition hosts)]
    (cond
      (= hosts "all") true
      (seq? (filter #{} limitvec)))))

(comment
  (seq? (filter #{"host1"} (clojure.string/split "host1,host2" #"\,")))
  (part-of-limit? "host1,host2" "host3"))

(defn get-relevant-plays [usecases]
  (for [{:keys [test-description
                playbook
                inventories
                limit] :as usecase} usecases]
    ; plays are relevant iff:
    ;  (and
    ;    (contains (contains (get-plays playbook) play))
    ;    (or
    ;      (no limit)
    ;      (match limit (gethosts play))))

    (filter part-of-limit? (parse-playbook playbook))))

(comment
  (get-relevant-plays cfg))

; projects have use cases to test (defined in ars.yml)
;;; use cases are essentially ansible-playbook calls with various paramaters
; use cases have plays (among params etc.)

(def relevant-plays (get-relevant-plays cfg))

; plays have hosts
; hosts have var map

; plays have roles
; roles have specs

; in order to validate role specs they need the specific var map they would get passed in a real ansible-playbook run

(defn get-specs [x])

(get-specs relevant-plays)

(comment
  (def watches (atom #{}))
  (swap! watches conj (watching/watch! {} [project-path] #(println "foo" %&)))
  (watching/stop! (first @watches))
                

  ((into {} cfg) :playbook)
  ((first cfg) :test-description)
  ((second cfg) :test-description))