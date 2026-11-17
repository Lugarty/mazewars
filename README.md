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
- JUnit 5
- Mockito
- Eclipse project metadata

## Getting Started

This repository is currently set up as an Eclipse Java project. It does not include a Maven or Gradle build file.

1. Import the repository into Eclipse as an existing Java project.
2. Confirm that JUnit 5 is available on the project build path.
3. The Mockito dependency JARs are stored in the `Mockito/` directory and referenced by the project configuration.
4. Run `model.Main` for the demonstration.
5. Run the test classes under `src/test/` from the IDE.

## Project Structure

```text
src/
├── model/      # Creatures, abilities, items, and elemental types
├── service/    # Battle, elemental damage, and status-effect services
└── test/       # JUnit 5 tests
```

## Current Limitations

- No Maven or Gradle build has been added yet.
- Mockito JARs are committed to the repository.
- Battle history is not persisted.
- The project has no graphical interface.
- Item and inventory classes are not yet integrated into the main battle flow.

## Project Status and License

This is a personal learning project, not a commercial product. No license has been specified.

## Author

[Anísio Oliveira (@Lugarty)](https://github.com/Lugarty)