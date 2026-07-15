# Demoted

<p align="center">
  <img src="src/main/resources/assets/demoted/icon.png" alt="Demoted icon" width="128" height="128">
</p>

A tiny client-side Fabric mod for Minecraft 26.1.2 that moves server resource packs to the lowest possible priority, directly above Minecraft's default resources.

This lets your enabled resource packs override server-provided textures while keeping the server pack active for resources that your packs do not replace.

## Features

- Automatically demotes accepted server resource packs.
- Places them directly above Minecraft's default pack.
- Preserves the order and priority of your enabled resource packs.
- No configuration, commands, menus, or required Fabric API dependency.
- Runs entirely on the client.

## Requirements

- Minecraft 26.1.2
- Fabric Loader 0.19.3 or newer
- Java 25

## Installation

1. Install Fabric Loader for Minecraft 26.1.2.
2. Download the Demoted jar.
3. Place it in your Minecraft `mods` directory.

## Build

Minecraft 26.1.2 requires Java 25.

```bash
JAVA_HOME=/path/to/java-25 ./gradlew build
```

The distributable jar is written to `build/libs/`.

## License

Source available for personal, non-commercial use only. See [LICENSE](LICENSE).
