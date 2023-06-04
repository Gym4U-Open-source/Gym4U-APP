package com.example.gym4u_movile_app.util

import android.content.Context
import android.widget.Toast
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.util.Locale

class UtilFn {
    companion object {
        fun toUTF8String(text: String, fromCharset: Charset = StandardCharsets.ISO_8859_1): String {
            return String(text.toByteArray(fromCharset), StandardCharsets.UTF_8)
        }
        private fun showToast(context: Context, stringId: Int, time: Int) {
            Toast.makeText(context, stringId, time).show()
        }

        fun showShortToast(context: Context, stringId: Int) { showToast(context, stringId, Toast.LENGTH_SHORT) }

        fun showLongToast(context: Context, stringId: Int) { showToast(context, stringId, Toast.LENGTH_LONG) }
        fun textContainAnyCase(text: String, toContain: String): Boolean {
            return text
                .lowercase()
                .contains(toContain.lowercase())
        }
    }
}