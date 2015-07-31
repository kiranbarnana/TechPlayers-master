#! /bin/sh
src/TechPlayers --update-config-with-doc -c /dev/null -o etc/defaults.cfg
src/TechPlayers --show-config > documentation/htdocs/config.txt

