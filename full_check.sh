#!/bin/bash
set -e
./gradlew ktlintFormat
./gradlew lint
./gradlew ktlintCheck
./gradlew detekt
./gradlew build
./gradlew test
./gradlew connectedAndroidTest