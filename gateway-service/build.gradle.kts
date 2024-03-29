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

extra["springCloudServicesVersion"] = "2.1.4.RELEASE"
extra["springCloudVersion"] = "Greenwich.SR1"

dependencies {
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-ribbon")
	compile("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
	implementation("org.springframework.cloud:spring-cloud-starter-config")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	compile(group="io.springfox", name= "springfox-swagger-ui", version= "2.9.2")
	compile(group= "io.springfox", name= "springfox-swagger2", version= "2.9.2")
	compile(group="com.google.code.gson", name= "gson", version= "2.8.4")
	implementation("org.springframework.boot:spring-boot-starter-hateoas")
	implementation("org.springframework.boot:spring-boot-starter-data-redis"){
		exclude(group="io.lettuce", module="lettuce-core")
	}
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	//implementation("io.pivotal.spring.cloud:spring-cloud-services-starter-circuit-breaker")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	//implementation("org.springframework.cloud:spring-cloud-bus")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix")
	//implementation("org.springframework.cloud:spring-cloud-starter-netflix-hystrix-dashboard")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-zuul")
	implementation("org.springframework.cloud:spring-cloud-starter-zipkin")
	implementation("org.springframework.data:spring-data-rest-hal-browser")
	implementation("redis.clients", "jedis")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
		exclude(group = "junit", module = "junit")
	}
}

dependencyManagement {
	imports {
		mavenBom("io.pivotal.spring.cloud:spring-cloud-services-dependencies:${property("springCloudServicesVersion")}")
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
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
