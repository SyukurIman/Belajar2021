package com.example.belajar2021.SaveOpen.SaveData

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class FileModel (var filename: String? = null,
                      var data: String? = null,
                      var name: String? = null,
                      var email: String? = null,
                      var age: Int = 0,
                      var phoneNumber: String? = null,
                      var isLove: Boolean = false,
                      var id: Int = 0,
                      var title: String? = null,
                      var description: String? = null,
                      var date: String? = null
): Parcelable