#!/bin/bash
clear && mvn com.github.ekryd.sortpom:sortpom-maven-plugin:2.15.0:sort --file ../pom.xml -U
find ../. -name '*pom.xml.bak' -delete