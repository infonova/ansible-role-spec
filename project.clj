(defproject ansible-role-spec "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.10.0-beta3"]
                 ;[org.clojure/clojure "1.8.0"]
                 [exoscale/clj-yaml "0.5.6"]
                 [hawk "0.2.11"
                  :exclusions [org.clojure/clojure]]
                 [org.clojure/core.async "0.4.474"]])