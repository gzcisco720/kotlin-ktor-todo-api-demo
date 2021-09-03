val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val ktorm_core: String by project
val mysql_connector: String by project
val flyway_core: String by project
val hikari_pool: String by project
val jetbrains_exposed: String by project

plugins {
    application
    kotlin("jvm") version "1.5.30"
}

group = "com.todo.ktor"
version = "0.0.1"
application {
    mainClass.set("com.todo.ktor.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-host-common:$ktor_version")
    implementation("io.ktor:ktor-auth:$ktor_version")
    implementation("io.ktor:ktor-auth-jwt:$ktor_version")
    implementation("io.ktor:ktor-gson:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("org.flywaydb:flyway-core:$flyway_core")
    implementation("mysql:mysql-connector-java:$mysql_connector")
    implementation("org.ktorm:ktorm-core:$ktorm_core")
    implementation("org.jetbrains.exposed:exposed:0.17.13")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("com.zaxxer:HikariCP:$hikari_pool")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
}