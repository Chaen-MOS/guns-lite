#!/bin/bash

echo "======================================"
echo "Starting Test Automation"
echo "Tool: JUnit + Maven Surefire + JaCoCo"
echo "======================================"

echo "[1/2] Running unit tests and generating Surefire test reports..."
mvn -pl guns-admin -am test

echo "======================================"
echo "[2/2] Generating JaCoCo code coverage report..."
mvn -pl guns-admin -am org.jacoco:jacoco-maven-plugin:0.8.8:report

echo "======================================"
echo "Test automation completed"
echo "Test report location:"
echo "guns-admin/target/surefire-reports"
echo ""
echo "Coverage report location:"
echo "guns-admin/target/site/jacoco/index.html"
echo "======================================"
