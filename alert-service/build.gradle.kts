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
	maven { url = uri("https://repo.spring.io/snapshot") }
	maven { url = uri("https://repo.spring.io/milestone") }
}

extra["springCloudVersion"] = "Greenwich.SR1"
extra["springCloudServicesVersion"] = "2.1.4.RELEASE"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.cloud:spring-cloud-starter-config")
	compile(group="io.springfox", name= "springfox-swagger-ui", version= "2.9.2")
	compile(group= "io.springfox", name= "springfox-swagger2", version= "2.9.2")
	implementation("org.springframework.cloud:spring-cloud-starter-zipkin")
	implementation("org.springframework.boot:spring-boot-starter-amqp")
	implementation("org.springframework.cloud:spring-cloud-starter-stream-rabbit")
	implementation("org.springframework.boot:spring-boot-starter-hateoas")
	//implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.data:spring-data-rest-hal-browser")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
	//implementation("io.pivotal.spring.cloud:spring-cloud-services-starter-service-registry")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
		mavenBom("io.pivotal.spring.cloud:spring-cloud-services-dependencies:${property("springCloudServicesVersion")}")
	}
}


tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
