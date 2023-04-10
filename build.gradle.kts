import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "xyz.xenondevs"
version = "1.0-SNAPSHOT"

val mojangMapped = project.hasProperty("mojang-mapped") || System.getProperty("mojang-mapped") != null

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("jvm") version "1.8.20"
    id("xyz.xenondevs.specialsource-gradle-plugin") version "1.0.0"
    id("xyz.xenondevs.string-remapper-gradle-plugin") version "1.0"
    id("xyz.xenondevs.nova.nova-gradle-plugin") version libs.versions.nova
    `maven-publish`
}

repositories {
    mavenCentral()
    maven("https://repo.xenondevs.xyz/releases")
    mavenLocal { content { includeGroup("org.spigotmc") } }
}

dependencies {
    implementation(libs.nova)
    implementation(variantOf(libs.spigot) { classifier("remapped-mojang") })
}

addon {
    id.set("simple_upgrades")
    name.set("Simple Upgrades")
    version.set(project.version.toString())
    novaVersion.set(libs.versions.nova)
    main.set("xyz.xenondevs.simpleupgrades.SimpleUpgrades")
    authors.add("StudioCode")
    // spigotResourceId.set(12345) TODO: Set your spigot resource id
}

spigotRemap {
    spigotVersion.set(libs.versions.spigot.get().substringBefore('-'))
    sourceJarTask.set(tasks.jar)
    spigotJarClassifier.set("")
}

remapStrings {
    remapGoal.set(if (mojangMapped) "mojang" else "spigot")
    spigotVersion.set(libs.versions.spigot.get())
    classes.set(listOf(
        // Put your classes to string-remap here
    ))
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
        rename { it.replace(project.name, addon.get().addonName.get()) }
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