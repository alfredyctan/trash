#!/bin/bash
mvn deploy:deploy-file \
-Durl=http://vm1:8081/repository/maven-vendors \
-DrepositoryId=nexus.vm1 \
-Dfile=chronograf-1.3.10.0_linux_amd64.tar.gz \
-DgroupId=com.influxdata \
-DartifactId=chronograf \
-Dversion=1.3.10.0 \
-Dpackaging=tar.gz \
-Dclassifier=linux_amd64 \
-DgeneratePom=true \
-DgeneratePom.description="Chronograf" \
-DuniqueVersion=true


mvn deploy:deploy-file \
-Durl=http://vm1:8081/repository/maven-vendors \
-DrepositoryId=nexus.vm1 \
-Dfile=telegraf-1.4.3_linux_amd64.tar.gz \
-DgroupId=com.influxdata \
-DartifactId=telegraf \
-Dversion=1.4.3 \
-Dpackaging=tar.gz \
-Dclassifier=linux_amd64 \
-DgeneratePom=true \
-DgeneratePom.description="Telegraf" \
-DuniqueVersion=true

