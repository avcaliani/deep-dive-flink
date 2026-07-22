#!/bin/bash -e

JAR_FILE="lard-lad-exchange-1.0.0-uber.jar"
CHECKPOINT_PATH="data/flink/checkpoint/"

bold() {
  printf "\e[1m%s\e[m" "$1"
}

# Jar file will be at "build/libs/lard-lad-exchange-*-uber.jar"
build_jar() {
  ./gradlew uberJar
}

# Flink can create the path automatically, so this function is not required but!
# Just to make it easier for you to know where the checkpoints are being created,
# I'm keeping it here.
function create_checkpoint_path() {
  mkdir -p "./$CHECKPOINT_PATH"
}

printf "+------------------------------------------+\n"
printf "| 🍩 %s                     |\n" "$(bold "Lard Lad Exchange")"
printf "| Kafka UI        ➜ http://localhost:8080  |\n"
printf "| Flink Dashboard ➜ http://localhost:8081  |\n"
printf "+------------------------------------------+\n\n"

printf "📦 %s\n" "$(bold "Building jar file...")"
build_jar
create_checkpoint_path

printf "\n🚀 %s\n" "$(bold "Starting application...")"
# About Checkpoints, you can start from a previous Job checkpoint
# by passing the following arguments 👇
#  >>> -s "<checkpoint-path>/<job-id>/chk-<n>"
#  >>> -s "/data/flink/checkpoint/f0ed1839c082c5466537788ca4481034/chk-2"
docker compose exec flink-dev /opt/flink/bin/flink run "$JAR_FILE" "$@"

exit 0

