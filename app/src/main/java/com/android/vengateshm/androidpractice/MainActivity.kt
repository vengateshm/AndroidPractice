package com.android.vengateshm.androidpractice

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.lifecycle.lifecycleScope
import com.android.vengateshm.androidpractice.di.SampleDiActivity
import com.android.vengateshm.androidpractice.dialog_fragments.Languages
import com.android.vengateshm.androidpractice.dialog_fragments.ListDialogFragment
import com.android.vengateshm.androidpractice.dialog_fragments.Technologies
import com.android.vengateshm.androidpractice.navigation_component.bottom_navigation.BottomNavActivity
import com.android.vengateshm.androidpractice.payment_integration.razorPay.payment_link_webview.RazorPayPaymentActivity
import dev.vengateshm.stock_exchange.StockActivity
import kotlinx.coroutines.delay
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    private var show: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition(condition = {
                show
            })
        }
        lifecycleScope.launchWhenCreated {
            delay(1000)
            show = false
        }
        setContentView(R.layout.activity_main)

//        launchActivity<ObservableTypesActivity>()
//        launchActivity<WebViewReactJsActivity>()
//        launchActivity(this, ObservableTypesActivity::class)
//        launchActivity<OneTimeEventActivity>()
//        launchActivity<ConstraintLayoutActivity>()
//        showListDialogs()
//        openPayment()
//        launchActivity<PreferenceDatastoreActivity>()
//        launchActivity<RetrofitActivity>()
//        launchActivity<MavericksSampleActivity>()
//        launchActivity<FortNightCalendarActivity>()
//        launchActivity<SlidingNavDrawerActivity>()
//        launchActivity<FileConversionActivity>()
//        launchActivity<DemoActivity>()
//        launchActivity<UiComponentsActivity>()
//        launchActivity<BottomNavActivity>()
//        launchActivity<SampleDiActivity>()
        launchActivity<StockActivity>()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun setHtmlText() {
        val text: String = getString(R.string.welcome_messages, "Drake", 27)
        val styledText: Spanned = Html.fromHtml(text, FROM_HTML_MODE_LEGACY)
        findViewById<TextView>(R.id.tvText).text = styledText
    }

    private fun openPayment() {
//        launchActivity<OrderActivity>()
        launchActivity<RazorPayPaymentActivity>()
    }

    private fun showListDialogs() {
        val techList = listOf(Technologies("AI"), Technologies("BlockChain"), Technologies("IOT"))
        val langList = listOf(Languages("Kotlin"), Languages("Scala"), Languages("Go"))

        val techDialog = ListDialogFragment(techList) {

        }
        val langDialog = ListDialogFragment(langList) {

        }
        techDialog.show(supportFragmentManager, "TECH LIST DIALOG")
//        langDialog.show(supportFragmentManager, "LANG LIST DIALOG")
    }
}

fun <T : AppCompatActivity> launchActivity(ctx: Context, cls: KClass<T>) {
    ctx.startActivity(Intent(ctx, cls.java))
}

inline fun <reified T> Context.launchActivity() = this.startActivity(Intent(this, T::class.java))