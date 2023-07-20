package com.android.vengateshm.androidpractice

import android.widget.Button
import androidx.test.core.app.ActivityScenario
import com.android.vengateshm.androidpractice.testing.ImageShareActivity
import com.google.common.truth.Truth
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ImageShareActivityTest {
    //PosixFilePermissions Issue
    @Test
    fun check_button_text() {
        ActivityScenario.launch(ImageShareActivity::class.java).use { scenario ->
            scenario.onActivity {
                val btnShare = it.findViewById<Button>(R.id.btn_share)
                Truth.assertThat(btnShare.text)
                    .isEqualTo(it.resources.getText(R.string.send_btn_text))
            }
        }
    }
}