#!/bin/bash

# Exit on fail
set -e

echo "Running post installation script for example-service"

# Reload services to add example-service
systemctl daemon-reload

# Status of process
systemctl status example-serivice --no-pager

echo "example-service installed. You can start it with:"
echo " sudo systemctl start example-service"
