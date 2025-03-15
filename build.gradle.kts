plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "rango.com"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {

	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.fasterxml.uuid:java-uuid-generator:4.0.1")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.junit.platform:junit-platform-suite-api:1.9.2")

	//cucumber
	testImplementation("io.cucumber:cucumber-java:7.15.0")
	testImplementation("io.cucumber:cucumber-spring:7.15.0")
	testImplementation("io.cucumber:cucumber-junit:7.15.0")
	testImplementation("io.cucumber:cucumber-junit-platform-engine:7.15.0")

	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:mongodb")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	developmentOnly("org.springframework.boot:spring-boot-docker-compose")

	// test
	testImplementation("org.jetbrains.kotlin:kotlin-test")
	testImplementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")

	//kotlin
	implementation(kotlin("stdlib-jdk8"))

}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.register<Test>("cucumberTest") {
	useJUnitPlatform {
		includeEngines("cucumber")
	}
	testClassesDirs = sourceSets["test"].output.classesDirs
	classpath = sourceSets["test"].runtimeClasspath
	outputs.upToDateWhen { false }
}

tasks.build {
	dependsOn(tasks.named("cucumberTest"))
}
