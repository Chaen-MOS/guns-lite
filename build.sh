#!/bin/bash

echo "======================================"
echo "Starting Maven build automation"
echo "======================================"

mvn clean package

echo "======================================"
echo "Build completed"
echo "Generated WAR file:"
echo "guns-admin/target/guns-lite.war"
echo "======================================"