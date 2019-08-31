import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.1.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
	kotlin("jvm") version "1.2.71"
	kotlin("plugin.spring") version "1.2.71"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "Greenwich.SR1"

dependencies {
	compile(group = "javax.xml.bind", name = "jaxb-api", version = "2.3.0")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
	implementation("org.springframework.cloud:spring-cloud-starter-zipkin")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-amqp")
	compile(group="io.springfox", name= "springfox-swagger-ui", version= "2.9.2")
	compile(group= "io.springfox", name= "springfox-swagger2", version= "2.9.2")
	implementation("org.springframework.boot:spring-boot-starter-hateoas")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.cloud:spring-cloud-starter-config")
	implementation("org.springframework.cloud:spring-cloud-stream-binder-rabbit")
	implementation("org.springframework.cloud:spring-cloud-stream-reactive")
	implementation("org.springframework.data:spring-data-rest-hal-browser")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.cloud:spring-cloud-stream-test-support")
	runtimeOnly("com.h2database:h2")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
