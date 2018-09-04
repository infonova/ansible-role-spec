# ansible-role-spec
run validation of config before applying changes


## tl;dr

combines information about required, optional and not used variables/facts (spec) with inventory data and performs basic validation as well es some configuration tests provided by the role itself.

spec of role variables can also be used to generate sample-data (that could be dumped into a host_vars file).

### There could be warning features for

* unused variables
* shadowed variable definitions (mb meta-marker for warning exclusion?)

## opinion

Ansible configuration management consists of

* deployment code (playbooks, roles, tasks)
* configuration
* and to some extent state of target environments (which actually should only be result of the former 2)

To build trust into arbitrary configuration changes before applying them one should opt for:

* version control
  * deployment code
  * configuration
  * control host environment
* role unit tests
* *configuration tests* (validation or "can this make sense").

server_spec tests as much as they can add value are unfortunately out of the equation in this context as they're only suitable for testing after applying changes.
