plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	kotlin("plugin.jpa") version "1.9.25"
	kotlin("kapt") version "1.9.25"
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
	idea
}

group = "test.system"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		this.languageVersion
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

	// Swagger REST DOC
	implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

	// Kotlin Logging
	implementation("io.github.oshai:kotlin-logging-jvm:5.1.1")

	// QueryDSL
	implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
	kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
	kapt("jakarta.annotation:jakarta.annotation-api")
	kapt("jakarta.persistence:jakarta.persistence-api")

	// JWT
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
	implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")

	kapt("org.springframework.boot:spring-boot-configuration-processor")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	runtimeOnly("com.mysql:mysql-connector-j")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
		jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
	}
}

val generated = file("src/main/generated")

tasks.withType<JavaCompile> {
	options.generatedSourceOutputDirectory.set(generated)
}

sourceSets {
	main {
		kotlin {
			srcDirs += generated
		}
	}
}

tasks.named("clean") {
	doLast {
		generated.deleteRecursively()
	}
}

kapt {
	generateStubs = true
}

tasks.withType<Test> {
	useJUnitPlatform()
}
