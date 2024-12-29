import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("plugin.spring")
}

dependencies {
    api(project(":domain"))
    implementation(project(":support:jwt"))
    implementation(project(":gateway:rdb"))
    implementation(project(":gateway:oauth"))

    implementation("org.springframework.boot:spring-boot-starter:${Versions.SPRING_BOOT}")
    compileOnly("org.springframework.boot:spring-boot-starter-jdbc:${Versions.SPRING_BOOT}")
}

tasks {
    withType<Jar> { enabled = true }
    withType<BootJar> { enabled = false }
}
