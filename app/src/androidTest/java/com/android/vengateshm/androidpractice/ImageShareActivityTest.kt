package com.android.vengateshm.androidpractice

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.vengateshm.androidpractice.testing.ImageShareActivity
import com.google.common.truth.Truth
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ImageShareActivityTest {
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

    @Test
    fun check_image() {
        ActivityScenario.launch(ImageShareActivity::class.java).use { scenario ->
            scenario.onActivity {
                val imgScreen = it.findViewById<ImageView>(R.id.img_screen)
                Truth.assertThat(
                    imgScreen.drawable != null && (imgScreen.drawable.constantState
                            == it.resources.getDrawable(R.drawable.saturn, it.theme)
                        .constantState)
                )
                    .isEqualTo(false)
            }
        }
    }

    @Test
    fun check_share_text_from_empty_text() {
        ActivityScenario.launch(ImageShareActivity::class.java).use { scenario ->
            scenario.onActivity { activity: ImageShareActivity ->
                val editText = activity.findViewById<EditText>(R.id.et_input)
                editText.setText("")
                Truth.assertThat(activity.getShareText()).isEqualTo(
                    activity.resources.getString(R.string.share_empty_text)
                )
            }
        }
    }

    @Test
    fun check_share_text_from_not_empty_text() {
        ActivityScenario.launch(ImageShareActivity::class.java).use { scenario ->
            scenario.onActivity { activity: ImageShareActivity ->
                val editText = activity.findViewById<EditText>(R.id.et_input)
                editText.setText("Test Text")
                Truth.assertThat(activity.getShareText()).isEqualTo("Test Text")
            }
        }
    }

    @Test
    fun check_share_image_uri() {
        ActivityScenario.launch(ImageShareActivity::class.java).use { scenario ->
            scenario.onActivity { activity: ImageShareActivity ->
                Truth.assertThat(
                    activity.getShareImageUri().toString()
                ).isEqualTo("android.resource://com.android.vengateshm.androidpractice/drawable/saturn")
            }
        }
    }

    @Test
    fun check_choose_intent() {
        ActivityScenario.launch(ImageShareActivity::class.java).use { scenario ->
            scenario.onActivity { activity: ImageShareActivity ->
                val checkIntent = activity.createChooserIntent()
                Truth.assertThat(checkIntent.action).isEqualTo(Intent.ACTION_CHOOSER)
            }
        }
    }

    @Test
    fun check_title_choose_intent() {
        ActivityScenario.launch(ImageShareActivity::class.java).use { scenario ->
            scenario.onActivity { activity: ImageShareActivity ->
                val checkIntent = activity.createChooserIntent()
                Truth.assertThat(
                    checkIntent.extras != null && checkIntent.extras!!.getString(Intent.EXTRA_TITLE) == activity.resources
                        .getString(R.string.title_chooser_text)
                ).isEqualTo(true)
            }
        }
    }

    @Test
    fun check_action_send_intent() {
        ActivityScenario.launch(ImageShareActivity::class.java).use { scenario ->
            scenario.onActivity { activity: ImageShareActivity ->
                val checkIntent = activity.createShareIntent()
                Truth.assertThat(
                    checkIntent.action != null && checkIntent.action == Intent.ACTION_SEND
                ).isEqualTo(true)
            }
        }
    }

    @Test
    fun check_type_send_intent() {
        ActivityScenario.launch(ImageShareActivity::class.java).use { scenario ->
            scenario.onActivity { activity: ImageShareActivity ->
                val checkIntent = activity.createShareIntent()
                Truth.assertThat(
                    checkIntent.type != null && checkIntent.type!!
                        .contains("image")
                ).isEqualTo(true)
            }
        }
    }
}