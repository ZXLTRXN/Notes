package com.example.notes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TestData(val id: Long) : Parcelable
