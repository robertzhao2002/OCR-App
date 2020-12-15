package com.example.ocr

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OCRItem(
    val full_text: String,
    val image_byte: ByteArray? = null,
    val audio: String
) : Parcelable

