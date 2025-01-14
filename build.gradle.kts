plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.springframework/spring-context
    implementation("org.springframework:spring-context:6.2.1")
    testImplementation("org.springframework:spring-test:6.2.1")

    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
    testImplementation("org.mockito:mockito-core:5.14.2")
    implementation("com.opencsv:opencsv:5.9")
    implementation("org.springframework.boot:spring-boot-starter:3.4.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.4.0")
    implementation("org.springframework:spring-context:6.2.1")
}

tasks.test {
    useJUnitPlatform()
}