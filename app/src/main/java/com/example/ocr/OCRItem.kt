package com.example.ocr

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OCRItem (
    val full_text: String,
    val image_preview: IntArray,
    val audio: String
) : Parcelable

