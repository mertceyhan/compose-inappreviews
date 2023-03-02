## In-App Reviews for Jetpack Compose

This library provides an interface and a default implementation to make it easy to use Android's
In-App Reviews API in a Jetpack Compose project.

### How it Works

The `InAppReviewManager` interface defines a method `launchReviewFlow` that can be used to launch
the
In-App Review flow. The `DefaultInAppReviewManager` class provides a default implementation of the
interface that uses the Android's In-App Reviews API to launch the review flow.

### Example Usage

To use this library in your Jetpack Compose project, first add it as a dependency:

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

```groovy
dependencies {
    implementation 'com.github.mertceyhan:compose-inappreviews:1.0.0'
}
```

Then, in your Composable function, call `rememberInAppReviewManager` to retrieve an instance of the
`InAppReviewManager`. You can then call the `launchReviewFlow` method on this instance to launch the
In-App Review flow.

```kotlin
@Composable
fun MyScreen() {
    // Get Activity
    val activity = (LocalContext.current as Activity)

    // Remember an InAppReviewManager
    val inAppReviewManager = rememberInAppReviewManager()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = {
            inAppReviewManager.launchReviewFlow(activity = activity, onReviewRequestSuccess = {
                // Handle successful review request
                // Note that Android's In-App Reviews API says;
                //  "The flow has finished. The API does not indicate whether the user
                //  reviewed or not, or even whether the review dialog was shown. Thus, no
                //  matter the result, we continue our app flow."
                // @see [Launch the in-app review flow](https://developer.android.com/guide/playcore/in-app-review/kotlin-java#launch-review-flow)
            }, onReviewRequestFail = {
                // Handle review request failure
            })
        }) {
            Text(text = "Launch Review Flow")
        }
    }
}
```

### How to Test

To test the In-App Review flow, you can follow the instructions in the official Android
documentation [here](https://developer.android.com/guide/playcore/in-app-review/test).

### Contributing

Contributions are welcome! If you would like to contribute to this library, please submit a pull
request.