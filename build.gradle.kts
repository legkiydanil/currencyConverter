plugins {
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.legkiyapps"
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
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation ("org.json:json:20231013")
	implementation ("org.apache.httpcomponents:httpclient:4.5.14")
	implementation ("org.freemarker:freemarker:2.3.32")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	compileOnly ("org.projectlombok:lombok:1.18.34")
	annotationProcessor ("org.projectlombok:lombok:1.18.34")
	testCompileOnly ("org.projectlombok:lombok:1.18.34")
	testAnnotationProcessor ("org.projectlombok:lombok:1.18.34")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
