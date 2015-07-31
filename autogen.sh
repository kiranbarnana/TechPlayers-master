#! /bin/sh

autoreconf --force --install
cd Techplayers-master
mkdir lib
pwd
ls
sh ./configure
make
make check
make install DESTDIR=~/kiranbarnana/TechPlayers/TechPlayers-master/lib/
make clean