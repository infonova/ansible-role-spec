# issues i stumbled upon

## watcher mechanism

* editing in intellij does not really "change" the file. inode-stuff etc. so need to take a closer look at this later

## inventory parsing

* will skip ini file support for now
* inventory parsing is super complex and we should not try to reimplement this in clojure probably.
    * eg. dynamic inventory, multiple static inventory files etc.
* maybe the best way to do this is to shell out and let ansible itself produce a hosts/var-map with groupmappings. need to look into this asap.
