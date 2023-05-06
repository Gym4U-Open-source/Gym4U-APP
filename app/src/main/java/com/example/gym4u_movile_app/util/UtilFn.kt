package com.example.gym4u_movile_app.util

import java.nio.charset.Charset

class UtilFn {
    companion object {
        fun toUTF8String(text: String, fromCharset: Charset = Charsets.ISO_8859_1): String {
            return String(text.toByteArray(fromCharset), Charsets.UTF_8)
        }
    }
}