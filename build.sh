#!/bin/bash

# CD to Frontend directory
cd ./cafe-frontend

# Install Frontend Dependencies
npm install

# Build Frontend
npm run build

# Build Frontend Docker Image
docker build -t cafe-frontend:only .

# CD to Backend directory
cd ../cafe-backend

# Build Backend
mvn clean package

# Build Backend Docker Image
docker build -t cafe-backend:only .

# CD back to root directory
cd ..
