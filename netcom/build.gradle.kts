plugins {
    id("buildSrc")
}

dependencies {
    implementation(project(":data"))
    implementation("org.glassfish.tyrus:tyrus-container-grizzly-client:2.0.2")
}