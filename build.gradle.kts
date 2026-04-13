plugins {
    java
    id("com.gradleup.shadow") version "9.4.1"
}

group = "com.artillexstudios"
version = "1.10.1"
description = "AxAFKZone"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}

repositories {
    mavenCentral()
    maven("https://repo.artillex-studios.com/releases/") {
        name = "Artillex-Studios"
    }
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") {
        name = "spigotmc-repo"
    }
    maven("https://jitpack.io") {
        name = "jitpack.io"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/") {
        name = "placeholderapi"
    }
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.20.2-R0.1-SNAPSHOT")

    implementation("com.artillexstudios.axapi:axapi:2.1.0-DEV-19:all")
    implementation("dev.triumphteam:triumph-gui:3.1.13") {
        exclude(group = "*", module = "*")
    }

    compileOnly("net.kyori:adventure-api:4.26.1")
    compileOnly("me.clip:placeholderapi:2.12.2")
    compileOnly("org.apache.commons:commons-math3:3.6.1")
}

tasks.shadowJar {
    archiveClassifier.set("")

    relocate("dev.dejvokep.boostedyaml", "com.artillexstudios.axafkzone.libs.boostedyaml")
    relocate("com.artillexstudios.axapi", "com.artillexstudios.axafkzone.libs.axapi")
    relocate("dev.triumphteam.gui", "com.artillexstudios.axafkzone.libs.gui")
    relocate("revxrsal.commands", "com.artillexstudios.axafkzone.libs.lamp")
    relocate("org.apache.commons.math3", "com.artillexstudios.axafkzone.libs.axapi.libs.math3")
}

tasks.processResources {
    filesMatching("plugin.yml") {
        expand("version" to project.version)
    }
}

tasks.build {
    dependsOn(tasks.shadowJar)
}