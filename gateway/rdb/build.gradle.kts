import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

dependencies {
    implementation(project(":domain"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:${Versions.SPRING_BOOT}")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
