package com.surpassli.common.imageloader

import java.io.Closeable
import java.io.IOException

class CloseUtils {
    private constructor()

    companion object {
        fun closeQuietly(closable: Closeable?) {
            if (closable != null) {
                try {
                    closable.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}