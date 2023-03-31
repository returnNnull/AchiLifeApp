package com.bam.achilifeapp.data.app.repositories

import com.bam.achilifeapp.data.app.firebase.AwardSchemeDTO
import com.bam.achilifeapp.data.app.room.AwardProgressEntity
import com.bam.achilifeapp.data.app.room.AwardSchemeEntity

class AwardsSchemeRepository(
    remote: IRemoteDataSource<AwardSchemeDTO, String>,
    local: ILocalDataSource<AwardSchemeEntity, String>
) : Repository<AwardSchemeDTO, AwardSchemeEntity, AwardSchemeModel, String>(remote, local) {
    override fun AwardSchemeEntity.toModel(): AwardSchemeModel {
        return AwardSchemeModel(id, name, about, img, maxValue, categoriesId)
    }

    override fun AwardSchemeDTO.toEntity(): AwardSchemeEntity {
        val entity = AwardSchemeEntity(id, name, about, img, maxValue, categoriesId)
        entity.isSynchronized = true
        return entity
    }

    override fun AwardSchemeModel.toEntity(): AwardSchemeEntity {
        return AwardSchemeEntity(id, name, about, img, maxValue, categoriesId)
    }

    override fun AwardSchemeEntity.toDTO(): AwardSchemeDTO {
        return AwardSchemeDTO(id, name, about, img, maxValue, categoriesId)
    }


}