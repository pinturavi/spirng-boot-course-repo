import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.1.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
	kotlin("jvm") version "1.2.71"
	kotlin("plugin.spring") version "1.2.71"
	kotlin("plugin.jpa") version "1.2.71"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

val developmentOnly by configurations.creating
configurations {
	runtimeClasspath {
		extendsFrom(developmentOnly)
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/snapshot") }
	maven { url = uri("https://repo.spring.io/milestone") }
}

extra["springCloudVersion"] = "Greenwich.SR1"
extra["springCloudServicesVersion"] = "2.1.4.RELEASE"

dependencies {
	implementation("org.springframework.cloud:spring-cloud-starter-zipkin")
	implementation("org.springframework.cloud:spring-cloud-starter-stream-rabbit")
	implementation("org.springframework.boot:spring-boot-starter-amqp")
	compile("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
	compile(group="io.springfox", name= "springfox-swagger-ui", version= "2.9.2")
	compile(group= "io.springfox", name= "springfox-swagger2", version= "2.9.2")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-hateoas")
	//implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.cloud:spring-cloud-netflix-eureka-client")
	//implementation("io.pivotal.spring.cloud:spring-cloud-services-starter-service-registry")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.cloud:spring-cloud-starter-config")
	implementation("org.springframework.data:spring-data-rest-hal-browser")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		exclude(group = "junit", module = "junit")
	}
	testImplementation("org.junit:junit5-engine:5.0.0-ALPHA")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
		mavenBom("io.pivotal.spring.cloud:spring-cloud-services-dependencies:${property("springCloudServicesVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
