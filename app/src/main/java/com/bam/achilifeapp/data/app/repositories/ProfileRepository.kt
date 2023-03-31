package com.bam.achilifeapp.data.app.repositories

import com.bam.achilifeapp.data.app.firebase.ProfileDTO
import com.bam.achilifeapp.data.app.room.ProfileEntity

class ProfileRepository(
    remote: IRemoteDataSource<ProfileDTO, String>,
    local: ILocalDataSource<ProfileEntity, String>
) : Repository<ProfileDTO, ProfileEntity, ProfileModel, String>(remote, local) {

    override fun ProfileEntity.toModel(): ProfileModel {
        return ProfileModel(id, name,img, motivation, bestCategoryId, awardsBest, awardsProgress)
    }

    override fun ProfileDTO.toEntity(): ProfileEntity {
        val entity = ProfileEntity(id, name,img, motivation, bestCategoryId, awardsBest, awardsProgress)
        entity.isSynchronized = true
        return entity
    }

    override fun ProfileModel.toEntity(): ProfileEntity {
        return ProfileEntity(id, name,img, motivation, bestCategoryId, awardsBest, awardsProgress)
    }

    override fun ProfileEntity.toDTO(): ProfileDTO {
        return ProfileDTO(id, name,img, motivation, bestCategoryId, awardsBest, awardsProgress)
    }
}