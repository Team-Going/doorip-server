plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
include(
    "presentation:api",
    "core",
    "domain",
    "gateway",
    "support:yaml"
)
include("gateway:rdb")
findProject(":gateway:rdb")?.name = "rdb"
