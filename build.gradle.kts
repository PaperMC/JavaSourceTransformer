plugins {
    `java-library`
    `maven-publish`
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

tasks.jar {
    manifest {
        attributes("Implementation-Version" to project.version)
    }
}

publishing {
    repositories {
        val url = if (project.version.toString().endsWith("-SNAPSHOT")) {
            "https://repo.papermc.io/repository/maven-snapshots/"
        } else {
            "https://repo.papermc.io/repository/maven-releases/"
        }
        maven(url) {
            credentials(PasswordCredentials::class)
            name = "paper"
        }
    }

    publications.create<MavenPublication>("maven") {
        artifactId = "jst"
        from(components["java"])
    }
}

tasks.register("printVersion") {
    doFirst {
        println(version)
    }
}
