package com.bam.achilifeapp.data.app.repositories

import com.bam.achilifeapp.data.app.firebase.AwardProgressDTO
import com.bam.achilifeapp.data.app.room.AwardProgressEntity

class AwardsProgressRepository(
    remote: IRemoteDataSource<AwardProgressDTO, String>,
    local: ILocalDataSource<AwardProgressEntity, String>
) : Repository<AwardProgressDTO, AwardProgressEntity, AwardProgressModel, String>(remote, local) {

    override fun AwardProgressEntity.toModel(): AwardProgressModel {

        return AwardProgressModel(id, value, awardId, categoryId)
    }

    override fun AwardProgressDTO.toEntity(): AwardProgressEntity {
        val entity = AwardProgressEntity(id, value, awardId, categoryId)
        entity.isSynchronized = true
        return entity
    }

    override fun AwardProgressModel.toEntity(): AwardProgressEntity {
        return AwardProgressEntity(id, value, awardId, categoryId)
    }

    override fun AwardProgressEntity.toDTO(): AwardProgressDTO {
        return AwardProgressDTO(id, value, awardId, categoryId)
    }
}