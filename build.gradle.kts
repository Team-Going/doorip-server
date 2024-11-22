import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version Versions.SPRING_BOOT
    id("io.spring.dependency-management") version Versions.SPRING_DEPENDENCY_MANAGEMENT

    kotlin("jvm") version Versions.KOTLIN
    kotlin("kapt") version Versions.KOTLIN
    id("org.jlleitschuh.gradle.ktlint") version Versions.KTLINT

    kotlin("plugin.spring") version Versions.KOTLIN apply false
    kotlin("plugin.jpa") version Versions.KOTLIN apply false
}

repositories {
    mavenCentral()
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}

kotlin {
    jvmToolchain(Versions.JDK)
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.JACKSON}")
        implementation("org.springframework.boot:spring-boot-starter-test:${Versions.SPRING_BOOT}")

        testImplementation(kotlin("test"))
    }

    tasks.test {
        useJUnitPlatform()
    }
}
