val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "1.6.10"
                id("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
}

group = "com.revolna"
version = "0.0.1"
application {
    mainClass.set("com.revolna.ApplicationKt")
}
tasks.create("stage") {
    dependsOn("installDist")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    // https://mvnrepository.com/artifact/io.ktor/ktor-gson
    //implementation("io.ktor:ktor-gson:1.6.7")

    //// https://mvnrepository.com/artifact/io.ktor/ktor-gson
    //    implementation group: 'io.ktor', name: 'ktor-gson', version: ktor_version
    implementation(group= "io.ktor", name= "ktor-gson", version= ktor_version)

    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation( group= "org.postgresql", name= "postgresql", version= "42.3.1")
    //implementation("io.ktor:ktor-gson:2.0.0-beta-1")
    // database
    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    //implementation("org.postgresql:postgresql:42.3.1")
//    implementation ("org.ktorm:ktorm-core:3.4.1")
//    // https://mvnrepository.com/artifact/org.ktorm/ktorm-support-postgresql
//    implementation("org.ktorm:ktorm-support-postgresql:3.4.1")

    implementation("mysql:mysql-connector-java:8.0.11")
    implementation("org.ktorm:ktorm-core:3.2.0")
    implementation("org.ktorm:ktorm-support-postgresql:3.4.1")

}