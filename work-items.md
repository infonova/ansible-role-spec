# workitems

## parser

given a playbook (ans possibly a requirements.yml) (later on we should provide possibly multiple ansible-playbook commands)
read inventory/host_vars/group_vars etc. 
result should be at least a map where top level entity is a host.

## testing framework

* should be running in background
* watch project
* mb watch dependent roles (requirements.yml) in case we depend on snapshot-ish (anything but a tag - tarballs?)
* give feedback within (split-)seconds (browser/terminal/sound/???)
* run specs
    * minimum "spec" is a check for all required variables

## gather/load specs from roles

ss. 

## future: generate specs from yaml

## future: IDE support

## future: generate "services"
    ansible-spec svc add jenkins inventory