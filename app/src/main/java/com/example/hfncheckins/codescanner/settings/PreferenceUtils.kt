package com.example.codescanner.settings

import android.content.Context
import android.graphics.RectF
import android.preference.PreferenceManager
import androidx.annotation.StringRes
import com.example.codescanner.R
import com.example.codescanner.camera.CameraSizePair
import com.example.codescanner.camera.GraphicOverlay
import com.google.android.gms.common.images.Size
import com.google.mlkit.vision.barcode.common.Barcode

object PreferenceUtils {
    fun saveStringPreference(context: Context,  prefKeyId: String, value: String?) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(prefKeyId, value)
            .apply()
    }

    fun getUserSpecifiedPreviewSize(context: Context): CameraSizePair? {
        return try {
            val previewSizePrefKey = "rcpvs"
            val pictureSizePrefKey = "rcpts"
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            CameraSizePair(
                Size.parseSize(sharedPreferences.getString(previewSizePrefKey, null)!!),
                Size.parseSize(sharedPreferences.getString(pictureSizePrefKey, null)!!)
            )
        } catch (e: Exception) {
            null
        }
    }

    fun getBarcodeReticleBox(overlay: GraphicOverlay): RectF {
        val context = overlay.context
        val overlayWidth = overlay.width.toFloat()
        val overlayHeight = overlay.height.toFloat()
        val boxWidth =
            overlayWidth * getIntPref(context, R.string.pref_key_barcode_reticle_width, 80) / 100
        val boxHeight =
            overlayHeight * getIntPref(context, R.string.pref_key_barcode_reticle_height, 35) / 100
        val cx = overlayWidth / 2
        val cy = overlayHeight / 2
        return RectF(cx - boxWidth / 2, cy - boxHeight / 2, cx + boxWidth / 2, cy + boxHeight / 2)
    }

    private fun getIntPref(context: Context, @StringRes prefKeyId: Int, defaultValue: Int): Int {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val prefKey = context.getString(prefKeyId)
        return sharedPreferences.getInt(prefKey, defaultValue)
    }

    fun getProgressToMeetBarcodeSizeRequirement(overlay: GraphicOverlay, barcode: Barcode): Float {
        val context = overlay.context
        return if (getBooleanPref(context, R.string.pref_key_enable_barcode_size_check, false)) {
            val reticleBoxWidth = getBarcodeReticleBox(overlay).width()
            val barcodeWidth = overlay.translateX(barcode.boundingBox?.width()?.toFloat() ?: 0f)
            val requiredWidth =
                reticleBoxWidth * getIntPref(context, R.string.pref_key_minimum_barcode_width, 50) / 100
            (barcodeWidth / requiredWidth).coerceAtMost(1f)
        } else {
            1f
        }
    }

    private fun getBooleanPref(
        context: Context,
        @StringRes prefKeyId: Int,
        defaultValue: Boolean
    ): Boolean =
        PreferenceManager.getDefaultSharedPreferences(context)
            .getBoolean(context.getString(prefKeyId), defaultValue)

    fun shouldDelayLoadingBarcodeResult(context: Context): Boolean =
        getBooleanPref(context, R.string.pref_key_delay_loading_barcode_result, true)
}