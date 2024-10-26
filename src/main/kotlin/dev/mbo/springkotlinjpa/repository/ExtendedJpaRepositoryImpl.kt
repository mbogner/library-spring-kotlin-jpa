package dev.mbo.springkotlinjpa.repository

import dev.mbo.springkotlinjpa.AbstractEntity
import jakarta.persistence.EntityManager
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import java.io.Serializable

class ExtendedJpaRepositoryImpl<ID : Serializable, ENTITY : AbstractEntity<ID>>(
    domainClass: JpaMetamodelEntityInformation<ENTITY, ID>,
    private val em: EntityManager
) : SimpleJpaRepository<ENTITY, ID>(domainClass, em),
    ExtendedJpaRepository<ID, ENTITY> {

    override fun clear() {
        em.clear()
    }

    override fun getEntityManager(): EntityManager {
        return em
    }

    override fun refresh(entity: ENTITY) {
        em.refresh(entity)
    }

}
