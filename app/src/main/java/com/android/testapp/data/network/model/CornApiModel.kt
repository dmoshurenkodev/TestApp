package com.android.testapp.data.network.model

import com.google.gson.annotations.SerializedName

data class CornApiModel(
    @SerializedName("field_ID")
    val fieldId: Int = 0,
    @SerializedName("CultList")
    val cultList: String = "",
    @SerializedName("CornType")
    val cornType: String = "",
    @SerializedName("FieldNoDescr")
    val fieldNoDescr: String = "",
    val pol: String = "",
    @SerializedName("max_X")
    val maxX: Double = 0.0,
    @SerializedName("Area")
    val area: Double = 0.0,
    @SerializedName("max_Y")
    val maxY: Double = 0.0,
    @SerializedName("FieldNo")
    val fieldNo: String = "",
    @SerializedName("min_X")
    val minX: Double = 0.0,
    @SerializedName("FarmName")
    val farmName: String = "",
    @SerializedName("min_Y")
    val minY: Double = 0.0,
    @SerializedName("farm_ID")
    val farmId: Int = 0
)