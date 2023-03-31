package com.bam.achilifeapp.data.app.repositories

import com.bam.achilifeapp.data.app.firebase.CategoriesSchemeDTO
import com.bam.achilifeapp.data.app.room.CategoriesSchemeEntity

class CategoriesSchemeRepository(
    remote: IRemoteDataSource<CategoriesSchemeDTO, String>,
    local: ILocalDataSource<CategoriesSchemeEntity, String>
) : Repository<CategoriesSchemeDTO, CategoriesSchemeEntity, CategoriesSchemeModel, String>(remote, local) {
    override fun CategoriesSchemeEntity.toModel(): CategoriesSchemeModel {
        return CategoriesSchemeModel(
            id, name, img
        )
    }

    override fun CategoriesSchemeDTO.toEntity(): CategoriesSchemeEntity {

        return CategoriesSchemeEntity(id, name, img)
    }

    override fun CategoriesSchemeModel.toEntity(): CategoriesSchemeEntity {
        val entity = CategoriesSchemeEntity(id, name, img)
        entity.isSynchronized = true
        return entity
    }

    override fun CategoriesSchemeEntity.toDTO(): CategoriesSchemeDTO {
        return CategoriesSchemeDTO(
            id, name, img
        )
    }


}