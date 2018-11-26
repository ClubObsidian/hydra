# Hydra

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/aabf5d1aeb934c6ab091eaf86e502a6f)](https://www.codacy.com/app/virustotalop/hydra?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ClubObsidian/hydra&amp;utm_campaign=Badge_Grade)
[![Known Vulnerabilities](https://snyk.io/test/github/ClubObsidian/hydra/badge.svg?targetFile=build.gradle)](https://snyk.io/test/github/ClubObsidian/hydra?targetFile=build.gradle)

_Currently in development_

An application server built for event-driven programming.
The application server loads plugins in jar form, and has 
an event-bus. Hydra is being built with extensibility in mind.

## Development

### Eclipse

1. Git clone the project
2. Generate eclipse files with `gradlew eclipse`
3. Import project
4. Right click project -> Add gradle nature

### Intellij

1. Git clone the project
2. Generate intellij files with `gradlew idea`
3. Import project

### Building

`gradlew shadowJar`