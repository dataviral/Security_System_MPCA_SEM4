#!/bin/sh

a = 1

for i in {1..255}
do
	ping 192.168.1.$a
done
 
