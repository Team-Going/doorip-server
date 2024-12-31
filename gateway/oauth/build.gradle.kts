import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":support:jwt"))

    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:${Versions.OPEN_FEIGN}")
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
