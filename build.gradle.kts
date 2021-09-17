plugins {
    val kotlinVersion = "1.5.30"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.7.1-dev-1"
}

group = "com.github.soulspace"
version = "0.2.2"

repositories {
    mavenLocal()
    //maven("https://maven.aliyun.com/repository/public") // 阿里云国内代理仓库
    mavenCentral()
}
