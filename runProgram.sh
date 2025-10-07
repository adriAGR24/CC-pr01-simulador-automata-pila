#!/bin/bash
# Minimal runner: always compile (with debug symbols) and forward all args to Java Main
set -euo pipefail

echo "Compiling Java sources (with debug symbols)..."
mkdir -p bin
javac -g -d bin $(find src -name "*.java")

echo -e "Running Main with args: $@\n"
exec java -cp bin src.automaton.Main "$@"