package com.example.gym4u_movile_app.util

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

class UtilFn {
    companion object {
        fun toUTF8String(text: String, fromCharset: Charset = StandardCharsets.ISO_8859_1): String {
            return String(text.toByteArray(fromCharset), StandardCharsets.UTF_8)
        }
        private fun showToast(context: Context, stringId: Int, time: Int) = Toast.makeText(context, stringId, time).show()
        private fun showToast(context: Context, charSequence: CharSequence, time: Int) = Toast.makeText(context, charSequence, time).show()

        fun showShortToast(context: Context, stringId: Int) { showToast(context, stringId, Toast.LENGTH_SHORT) }
        fun showShortToast(context: Context, charSequence: CharSequence) { showToast(context, charSequence, Toast.LENGTH_SHORT) }

        fun showLongToast(context: Context, stringId: Int) { showToast(context, stringId, Toast.LENGTH_LONG) }
        fun showLongToast(context: Context, charSequence: CharSequence) { showToast(context, charSequence, Toast.LENGTH_LONG) }
        fun textContainAnyCase(text: String, toContain: String): Boolean {
            return text
                .lowercase()
                .contains(toContain.lowercase())
        }

        fun apiIsHigherThat(apiCode: Int) = Build.VERSION.SDK_INT > apiCode
        fun fullScreenUi(): Int {
            if (apiIsHigherThat(Build.VERSION_CODES.Q))
                return WindowInsets.Type.statusBars() or WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            return View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }

        fun AppCompatActivity.fullScreenUi() {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
            if(apiIsHigherThat(Build.VERSION_CODES.Q))
                window.decorView.windowInsetsController?.hide(UtilFn.fullScreenUi())
            else window.decorView.systemUiVisibility = UtilFn.fullScreenUi()
        }

        fun AppCompatActivity.showShortToast(@StringRes stringId: Int) = showShortToast(this, stringId)
        fun AppCompatActivity.showShortToast(charSequence: CharSequence) = showShortToast(this, charSequence)
        fun AppCompatActivity.startActivityAndClean(cls: Class<*>) = startActivity(Intent(this, cls).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
        fun EditText.isEmpty() = text.isEmpty()
        fun EditText.textString() = text.toString()
        fun EditText.isNotEmpty() = text.isNotEmpty()
        fun EditText.areDifferent(editText: EditText) = textString() != editText.textString()
        fun Spinner.selectedString() = selectedItem.toString()
        fun EditText.areEqual(editText: EditText) = !areDifferent(editText)
        fun String.isEmail() = matches(Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}"))

    }
}