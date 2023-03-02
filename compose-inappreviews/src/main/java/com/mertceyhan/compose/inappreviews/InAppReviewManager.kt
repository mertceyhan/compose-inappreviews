package com.mertceyhan.compose.inappreviews

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.android.play.core.review.ReviewManagerFactory

/**
 * An interface that defines a contract for launching the In-App Review flow for an app.
 */
@Stable
interface InAppReviewManager {
    /**
     * Launches the In-App Review flow for the given [activity].
     *
     * @param activity The activity from which the In-App Review flow is launched.
     * @param onReviewRequestSuccess A function that will be called if the request to launch the In-App
     * Review flow is successful. If no implementation is provided for this parameter, no action is taken.
     * @param onReviewRequestFail A function that will be called if the request to launch the In-App
     * Review flow fails. If no implementation is provided for this parameter, the exception will be logged
     * and a toast message will be shown to the user.
     */
    fun launchReviewFlow(
        activity: Activity,
        onReviewRequestSuccess: () -> Unit = {},
        onReviewRequestFail: (exception: Exception?) -> Unit = {},
    )
}

/**
 * Returns an instance of [InAppReviewManager] that can be used to launch the In-App Review flow.
 *
 * @return An instance of [InAppReviewManager].
 */
@Composable
fun rememberInAppReviewManager(): InAppReviewManager {
    // Get the application context using the LocalContext.current
    val applicationContext = LocalContext.current.applicationContext

    // Use the remember{} function to cache the InAppReviewManager instance and prevent
    // unnecessary recreation on recomposition
    return remember {
        // Create a new DefaultInAppReviewManager instance using the ReviewManagerFactory.create() API
        // with the retrieved application context
        DefaultInAppReviewManager(
            reviewManager = ReviewManagerFactory.create(applicationContext)
        )
    }
}
