plugins {
    id("com.android.application")
}

android {
    namespace = "com.gapharma"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.gapharma"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


    }
    

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }


    //buildToolsVersion = "30.0.3"
    buildFeatures {
        viewBinding = true
    }
}

configurations {
    all {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk7")
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk8")
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation ("com.intuit.sdp:sdp-android:1.1.0")
    implementation("androidx.test.ext:junit:1.1.5")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.github.barteksc:android-pdf-viewer:3.2.0-beta.1")
    implementation ("com.github.chrisbanes:PhotoView:2.0.0")
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation ("io.reactivex.rxjava3:rxjava:3.1.8")



    //TEST
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    testImplementation ("org.robolectric:robolectric:4.11.1")
    testImplementation ("androidx.test:core:1.5.0")
    testImplementation ("androidx.room:room-testing:2.6.1")

    //ROOM
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")

    //Debug
    debugImplementation ("com.squareup.leakcanary:leakcanary-android:2.13")




}
