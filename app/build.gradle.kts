plugins {
    id("buildSrc")
    id("application")
}

application {
    mainModule.set("client.app.app.main")
    mainClass.set("com.example.app.App")
}

dependencies {
    implementation(project(":ui"))
    implementation(project(":data"))
    implementation(project(":netcom"))
}