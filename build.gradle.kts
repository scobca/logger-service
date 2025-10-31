plugins {
    kotlin("jvm") version "2.2.0"
    kotlin("kapt") version "2.2.0"
    kotlin("plugin.jpa") version "2.2.0"
    kotlin("plugin.spring") version "2.2.0"
    kotlin("plugin.serialization") version "2.2.0"
    id("org.flywaydb.flyway") version "11.13.2"
    id("org.springframework.boot") version "3.5.0"
    id("io.spring.dependency-management") version "1.1.7"
    id("it.nicolasfarabegoli.conventional-commits") version "3.1.3"
}

group = "org.scobca"
version = "0.0.1-SNAPSHOT"
description = "logger-service"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Starters
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("io.projectreactor:reactor-test")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // Kotlin & Kotlinx
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")

    // Kotlinx serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Database & Migrations
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.flywaydb:flyway-database-postgresql")
    implementation("org.springframework:spring-jdbc")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("org.flywaydb:flyway-core")
    runtimeOnly("org.postgresql:r2dbc-postgresql")
    runtimeOnly("org.postgresql:postgresql")

    // Mapper
    implementation("org.mapstruct:mapstruct:1.5.3.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.3.Final")

    // Kafka Messaging
    implementation("org.springframework.kafka:spring-kafka")

    // Utilities
    implementation("one.stayfocused.spring:dotenv-spring-boot:1.0.0")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("org.aspectj:aspectjweaver")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Metrics
    implementation("io.micrometer:micrometer-tracing-bridge-brave")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")

    // Netty DNS Resolver с учетом платформы
    val nettyVersion = "4.1.122.Final"
    val osName = System.getProperty("os.name").lowercase()
    val osArch = System.getProperty("os.arch").lowercase()

    if (osName.contains("mac") && osArch == "aarch64") {
        runtimeOnly("io.netty:netty-resolver-dns-native-macos:$nettyVersion:osx-aarch_64")
    } else if (osName.contains("mac")) {
        runtimeOnly("io.netty:netty-resolver-dns-native-macos:$nettyVersion:osx-x86_64")
    }
}


buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.flywaydb:flyway-database-postgresql:11.13.2")
    }
}

flyway {
    url = ""
    user = ""
    password = ""
    locations = arrayOf("classpath:db/migration")
    schemas = arrayOf("public")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

conventionalCommits {
    warningIfNoGitRoot = true
    types += listOf("build", "chore", "docs", "feat", "fix", "refactor", "style", "test")
    scopes = emptyList()
    successMessage = "Сообщение коммита соответствует стандартам Conventional Commit."
    failureMessage = "Сообщение коммита не соответствует стандартам Conventional Commit."
}

