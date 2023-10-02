plugins {
    id("java")
}

group = "at.ac.tgm.mhanes"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.json:json:20230618")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("commons-validator:commons-validator:1.7")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}