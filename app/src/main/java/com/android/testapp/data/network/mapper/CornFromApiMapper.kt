package com.android.testapp.data.network.mapper

import com.android.testapp.base.mapper.Mapper
import com.android.testapp.data.network.model.CornApiModel
import com.android.testapp.domain.model.CornModel

class CornFromApiMapper : Mapper<CornApiModel, CornModel> {

    override suspend fun map(from: CornApiModel): CornModel {
        return CornModel(
            fieldID = from.fieldId,
            cultList = from.cultList,
            cornType = from.cornType,
            fieldNoDescr = from.fieldNoDescr,
            pol = from.pol,
            maxX = from.maxX,
            area = from.area,
            maxY = from.maxY,
            fieldNo = from.fieldNo,
            minX = from.minX,
            farmName = from.farmName,
            minY = from.minY,
            farmID = from.farmId
        )
    }
}