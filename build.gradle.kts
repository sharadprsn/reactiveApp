plugins {
    application
    id("org.beryx.runtime") version "1.6.0"
}

group = "com.crazybyte.reactive"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_15
    targetCompatibility = JavaVersion.VERSION_15
}

application {
    mainClass.set("com.crazybyte.reactive.app.Application")
}

repositories {
    maven { setUrl("https://repo.spring.io/milestone") }
    mavenCentral()
}

dependencies {
    implementation("io.projectreactor.netty:reactor-netty-core:1.0.2")
    implementation("io.projectreactor.netty:reactor-netty-http:1.0.2")
    implementation("org.projectlombok:lombok:1.18.16")
    annotationProcessor("org.projectlombok:lombok:1.18.16")
    implementation("ch.qos.logback:logback-classic:1.2.3")

    testImplementation("org.projectlombok:lombok:1.18.16")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.16")
    testImplementation("junit", "junit", "4.12")
}

/**tasks {
    register("fatJar", Jar::class.java) {
        archiveClassifier.set("all")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        manifest {
            attributes("Main-Class" to "com.crazybyte.reactive.app.Application")
        }
        from(configurations.runtimeClasspath.get()
                .onEach { println("add from dependencies: ${it.name}") }
                .map { if (it.isDirectory) it else zipTree(it) })
        val sourcesMain = sourceSets.main.get()
        sourcesMain.allSource.forEach { println("add from sources: ${it.name}") }
        from(sourcesMain.output)
    }
}*/

runtime {
    imageZip.set(project.file("${project.buildDir}/image-zip/reactiveLib-image.zip"))
    options.set(listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages"))
    modules.set(listOf("java.logging","java.naming", "java.xml"))
}
