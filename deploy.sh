#!/bin/bash

set -e

echo "======================================"
echo "Starting Deployment Automation"
echo "Tool: Vagrant + Apache Tomcat"
echo "======================================"

PROJECT_DIR="/vagrant/guns-lite"
WAR_SOURCE="$PROJECT_DIR/guns-admin/target/guns-lite.war"
TOMCAT_WEBAPPS="/var/lib/tomcat9/webapps"
APP_NAME="guns-lite"

echo "[1/5] Navigating to project directory..."
cd "$PROJECT_DIR"

echo "[2/5] Building application package using Maven..."
mvn clean package

echo "[3/5] Removing old deployment from Tomcat..."
sudo rm -rf "$TOMCAT_WEBAPPS/$APP_NAME"
sudo rm -f "$TOMCAT_WEBAPPS/$APP_NAME.war"

echo "[4/5] Deploying new WAR file to Tomcat..."
sudo cp "$WAR_SOURCE" "$TOMCAT_WEBAPPS/"

echo "[5/5] Restarting Tomcat service..."
sudo systemctl restart tomcat9

echo "======================================"
echo "Deployment completed successfully"
echo "WAR file deployed to:"
echo "$TOMCAT_WEBAPPS/$APP_NAME.war"
echo ""
echo "Application URL inside VM:"
echo "http://localhost:8080/guns-lite"
echo ""
echo "Application URL from Windows host:"
echo "http://localhost:8086/guns-lite"
echo "======================================"
