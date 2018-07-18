#!/bin/sh

command="java -jar /opt/information-leakage/xxe-thorntail.jar -s /opt/information-leakage/project-defaults.yml"
echo "Executing: ${command}"
echo "######################################################################"
eval ${command}

