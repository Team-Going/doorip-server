import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":domain"))
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
