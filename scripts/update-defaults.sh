#! /bin/sh
src/TechPlayers -c /dev/null --update-config-with-doc > etc/defaults.cfg
cp etc/defaults.cfg documentation/htdocs/default.cfg

