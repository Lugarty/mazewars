# MazeWars

![Java 21](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
![JUnit 5](https://img.shields.io/badge/JUnit-5-25A162?logo=junit5)
![Mockito](https://img.shields.io/badge/Mockito-5-blue)

> A Java turn-based battle simulator focused on domain modeling and automated tests.

## Overview

MazeWars models battles between creatures with elemental types, abilities, status effects, and turn order based on speed. The project separates domain entities from services that coordinate battle rules, elemental damage, status effects, and battle logging.

## Features

- Turn order based on creature speed
- Elemental damage calculations
- Temporary status effects and action blocking
- A battle logging interface that can be replaced with another implementation
- JUnit 5 tests and Mockito-based isolation of collaborators
- Item and inventory domain models; item use is not yet part of the main battle flow

## Tech Stack

- Java 21
- Apache Maven
- JUnit 5
- Mockito

## Getting Started

Requirements: JDK 21 and Maven 3.9 or later.

Run the test suite:

```bash
mvn test
```

Run the demonstration after compiling:

```bash
mvn package
java -cp target/classes model.Main
```

In an IDE, import the repository as an existing Maven project so dependencies are resolved from `pom.xml`.

## Project Structure

```text
pom.xml
src/
├── model/      # Creatures, abilities, items, and elemental types
├── service/    # Battle, elemental damage, and status-effect services
└── test/       # JUnit 5 tests
```

## Current Limitations

- Battle history is not persisted.
- The project has no graphical interface.

## Project Status and License

This is a personal learning project, not a commercial product. No license has been specified.

## Author

[Anísio Oliveira (@Lugarty)](https://github.com/Lugarty)
