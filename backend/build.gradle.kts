import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	id("org.springframework.boot") version "2.7.1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("com.palantir.docker") version "0.33.0"
	id("com.palantir.docker-compose") version "0.33.0"
	id("org.flywaydb.flyway") version "8.5.13"
	kotlin("jvm")
	kotlin("plugin.spring") version "1.6.21"
	id("org.jetbrains.kotlin.plugin.jpa") version "1.5.21"

	application
}

group = "ch.levelup.kaesseli"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
	implementation(project(":shared"))
	// kotlin
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.google.firebase:firebase-admin:9.1.0")

	//test debug
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.testcontainers:junit-jupiter")
	//testImplementation("org.testcontainers:postgresql")

	//web
	implementation("org.springframework.boot:spring-boot-starter-web")

	// database
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.flywaydb:flyway-core")
	//runtimeOnly("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql")

	// swagger
	implementation("io.springfox:springfox-swagger2:3.0.0")
	implementation("io.springfox:springfox-bean-validators:3.0.0")
	implementation("io.springfox:springfox-swagger-ui:3.0.0")
	implementation("org.springdoc:springdoc-openapi-data-rest:1.6.9")
	implementation("org.springdoc:springdoc-openapi-ui:1.6.9")
	implementation("org.springdoc:springdoc-openapi-kotlin:1.6.9")

}
extra["testcontainersVersion"] = "1.17.3"

dependencyManagement {
	imports {
		mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
	environment("GOOGLE_APPLICATION_CREDENTIALS","secrets/kaesseli-firebase-adminsdk.json")
}

docker {
	val archiveBaseName = tasks.getByName<BootJar>("bootJar").archiveBaseName.get()
	name = "kaesseli-api"
	files("build/libs/$archiveBaseName-${project.version}.jar")
}

flyway {
	url = "jdbc:postgresql://kaesselidb.postgres.database.azure.com:5432/kaesseli"
	user =  "kaesseli@kaesselidb"
	password = "VsMWZo45cavna7WM!"
}

tasks.register("bootRunDev") {
	group = "application"
	description = "Runs the Spring Boot application with the dev profile"
	doFirst {
		tasks.bootRun.configure {
			systemProperty("spring.profiles.active", "dev")
		}
	}
	finalizedBy("bootRun")
}

application {
	// Define the main class for the application.
	mainClass.set("ch.levelup.kaesseli.backend.BackendApplicationKt")
}
