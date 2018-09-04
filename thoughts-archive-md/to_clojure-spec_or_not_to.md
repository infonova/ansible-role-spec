# TODO

## Ideas (to be discussed)

* use clj spec?
  * PRO: test/sample data generation built in
  * PRO: i learn spec
  * CON: i need to learn spec first
  * can also cover dependency validation 
* configuration data parser could be a bit more challenging than i thought
  * inventory ini/yml/dynamic
  * host_var / group_var locations and precedency etc.


### (clj-)spec? 

* tested entity is a host's (composed) var map.
* a validation run would 
  1) parse inventory and role-sequence per host (from playbook)
  1) foreach host and each of its roles run (s/valid ::role-foo-requestmap (:var-map host))
* keymap-validation is a spec on this composed host var map
* https://clojure.github.io/spec.alpha/clojure.spec.alpha-api.html#clojure.spec.alpha/cat

* inventory is just one huge map data structure (that i should write down/visualize)
  * set of host entities (var maps)
  * set of groups (var maps)
  * global var map
  * hierarchy:
    * global map
    * groups->groups
    * groups->hosts
    
* mb i should start by prototyping a parser?
  * parsing could be done by ansible itself (shell out), but this would flatten the origins of the vars
* the map should 