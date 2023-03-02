package com.mertceyhan.sample

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.mertceyhan.compose.inappreviews.rememberInAppReviewManager

class SampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    // Get Activity
                    val activity = (LocalContext.current as Activity)

                    // Remember an InAppReviewManager
                    val inAppReviewManager = rememberInAppReviewManager()

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Button(onClick = {
                            inAppReviewManager.launchReviewFlow(activity = activity,
                                onReviewRequestSuccess = {
                                    // Handle successful review request
                                    // Note that Android's In-App Reviews API says;
                                    //  "The flow has finished. The API does not indicate whether the user
                                    //  reviewed or not, or even whether the review dialog was shown. Thus, no
                                    //  matter the result, we continue our app flow."
                                    // @see [Launch the in-app review flow](https://developer.android.com/guide/playcore/in-app-review/kotlin-java#launch-review-flow)
                                },
                                onReviewRequestFail = {
                                    // Handle review request failure
                                })
                        }) {
                            Text(text = "Launch Review Flow")
                        }
                    }
                }
            }
        }
    }
}