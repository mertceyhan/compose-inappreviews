package com.mertceyhan.compose.inappreviews

import android.app.Activity
import com.google.android.play.core.review.ReviewManager

/**
 * A default implementation of the [InAppReviewManager] interface that uses the Android In-App Reviews API
 * to launch the In-App Review flow.
 *
 * @property reviewManager An instance of [ReviewManager] that will be used to launch the In-App Review flow.
 */
internal class DefaultInAppReviewManager constructor(
    private val reviewManager: ReviewManager
) : InAppReviewManager {

    override fun launchReviewFlow(
        activity: Activity,
        onReviewRequestSuccess: () -> Unit,
        onReviewRequestFail: (exception: Exception?) -> Unit
    ) {
        val requestTask = reviewManager.requestReviewFlow()

        requestTask.addOnCompleteListener { requestTaskResult ->
            if (requestTaskResult.isSuccessful) {
                val reviewInfo = requestTaskResult.result
                val launchTask = reviewManager.launchReviewFlow(activity, reviewInfo)

                launchTask.addOnCompleteListener { launchTaskResult ->
                    if (launchTaskResult.isSuccessful) {
                        onReviewRequestSuccess()
                    } else {
                        onReviewRequestFail(launchTaskResult.exception)
                    }
                }
            } else {
                onReviewRequestFail(requestTaskResult.exception)
            }
        }
    }
}