#!/bin/bash
# Small runner with debugging support
set -euo pipefail

# Usage:
# ./run.sh [--debug|--debug=suspend] <input-file>
# or set DEBUG=1 or DEBUG=suspend in env.

DEBUG_ARG=""
INPUT_FILE=""

for arg in "$@"; do
	case "$arg" in
		--debug)
			DEBUG_ARG="n"
			;;
		--debug=suspend)
			DEBUG_ARG="y"
			;;
		--help|-h)
			echo "Usage: $0 [--debug|--debug=suspend] <input-file>"
			exit 0
			;;
		*)
			INPUT_FILE="$arg"
			;;
	esac
done

# Allow DEBUG env var override: DEBUG=1 or DEBUG=suspend
if [ -n "${DEBUG:-}" ]; then
	if [ "${DEBUG}" = "suspend" ] || [ "${DEBUG}" = "y" ]; then
		DEBUG_ARG="y"
	else
		DEBUG_ARG="n"
	fi
fi

if [ -z "$INPUT_FILE" ]; then
	echo "ERROR: No input file provided."
	echo "Usage: $0 [--debug|--debug=suspend] <input-file>"
	exit 2
fi

echo "Compiling Java sources (with debug symbols)..."
mkdir -p bin
javac -g -d bin $(find src -name "*.java")

JAVA_OPTS=""
if [ "$DEBUG_ARG" = "y" ]; then
	# suspend=y will wait for debugger; suspend=n will not
	JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005"
elif [ "$DEBUG_ARG" = "n" ]; then
	JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
fi

echo "Running Main (debug=${DEBUG_ARG:-none})..."
exec java $JAVA_OPTS -cp bin src.automaton.Main "$INPUT_FILE"