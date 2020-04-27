package com.android.testapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CornModel(val fieldID: Int = 0,
                     val cultList: String = "",
                     val cornType: String = "",
                     val fieldNoDescr: String = "",
                     val pol: String = "",
                     val maxX: Double = 0.0,
                     val area: Double = 0.0,
                     val maxY: Double = 0.0,
                     val fieldNo: String = "",
                     val minX: Double = 0.0,
                     val farmName: String = "",
                     val minY: Double = 0.0,
                     val farmID: Int = 0) : Parcelable