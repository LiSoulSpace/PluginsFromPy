plugins {
    val kotlinVersion = "1.5.31"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.8.0-M1"
}

group = "com.github.soulspace"
version = "1.0.1"

repositories {
    mavenLocal()
    //maven("https://maven.aliyun.com/repository/public") // 阿里云国内代理仓库
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")
    implementation("org.mybatis:mybatis:3.5.7")
    implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:1.3.0")
    implementation("mysql:mysql-connector-java:8.0.25")
    implementation("org.slf4j:slf4j-api:1.7.32")
    implementation("log4j:log4j:1.2.17")
    implementation("org.apache.logging.log4j:log4j-core:2.14.1")
    implementation("org.apache.logging.log4j:log4j-api:2.14.1")
    implementation("com.github.houbb:opencc4j:1.7.1")
    testImplementation("org.slf4j:slf4j-log4j12:1.7.32")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}