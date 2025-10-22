#!/bin/bash

# List of ports to check and free
PORTS=("8080" "8083" "5432" "6001")

for PORT in "${PORTS[@]}"; do
  echo "Checking port $PORT..."

  PID=$(sudo lsof -t -i :$PORT)

  if [ -n "$PID" ]; then
    echo "Killing process $PID using port $PORT..."
    sudo kill -9 $PID
    echo "✅ Port $PORT is now free."
  else
    echo "ℹ️ No process found on port $PORT."
  fi

  echo
done

echo "All done!"

