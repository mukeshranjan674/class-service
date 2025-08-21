#!/bin/bash

APP_NAME="Category"
JAR_NAME="Category-1.jar"
LOG_FILE="category.log"

echo ">>> Pulling latest code from Github..."
git reset --hard
git pull origin main

echo ">>> Building project..."
mvn clean package -DskipTests

echo ">>> Stopping old process..."
pkill -f "$JAR_NAME"

echo ">>> Starting new JAR..."
nohup java -jar target/$JAR_NAME > $LOG_FILE 2>&1 &

NEW_PID=$!
echo ">>> $APP_NAME  started with PID $NEW_PID"
