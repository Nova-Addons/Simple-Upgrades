import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "xyz.xenondevs"
version = "1.1-RC.1"

val mojangMapped = project.hasProperty("mojang-mapped")

plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.nova)
    alias(libs.plugins.stringremapper)
    alias(libs.plugins.specialsource)
    `maven-publish`
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.xenondevs.xyz/releases")
    
    // include xenondevs-nms repository if requested
    if (project.hasProperty("xenondevsNms")) {
        maven("https://repo.papermc.io/repository/maven-public/") // authlib, brigadier, etc.
        maven {
            name = "xenondevsNms"
            url = uri("https://repo.xenondevs.xyz/nms/")
            credentials(PasswordCredentials::class)
        }
    }
}

dependencies {
    implementation(libs.nova)
}

addon {
    id.set("simple_upgrades")
    name.set("Simple Upgrades")
    version.set(project.version.toString())
    novaVersion.set(libs.versions.nova)
    main.set("xyz.xenondevs.simpleupgrades.SimpleUpgrades")
    authors.add("StudioCode")
}

spigotRemap {
    spigotVersion.set(libs.versions.spigot.get().substringBefore('-'))
    sourceJarTask.set(tasks.jar)
}

remapStrings {
    remapGoal.set(if (mojangMapped) "mojang" else "spigot")
    spigotVersion.set(libs.versions.spigot.get())
}

tasks {
    register<Copy>("addonJar") {
        group = "build"
        dependsOn("addon", if (mojangMapped) "jar" else "remapObfToSpigot")
        
        from(File(File(project.buildDir, "libs"), "${project.name}-${project.version}.jar"))
        into(
            (project.findProperty("outDir") as? String)?.let(::File)
                ?: System.getProperty("outDir")?.let(::File)
                ?: project.buildDir
        )
        rename { it.replace(project.name, addon.get().addonName.get().replace(" ", "-")) }
    }
    
    register<Jar>("sources") {
        dependsOn(JavaPlugin.CLASSES_TASK_NAME)
        from("src/main/kotlin")
        archiveClassifier.set("sources")
    }
    
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
}

publishing {
    repositories {
        maven {
            credentials {
                name = "xenondevs"
                url = uri { "https://repo.xenondevs.xyz/releases/" }
                credentials(PasswordCredentials::class)
            }
        }
    }
    
    publications {
        create<MavenPublication>("SimpleUpgrades") {
            from(components.getByName("kotlin"))
            artifact(tasks.getByName("sources"))
        }
    }
}