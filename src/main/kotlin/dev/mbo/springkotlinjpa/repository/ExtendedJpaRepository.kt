package dev.mbo.springkotlinjpa.repository

import dev.mbo.springkotlinjpa.Identifiable
import jakarta.persistence.EntityManager
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.NoRepositoryBean
import java.io.Serializable

@NoRepositoryBean
interface ExtendedJpaRepository<ID : Serializable, T : Identifiable<ID>> : JpaRepository<T, ID>,
    JpaSpecificationExecutor<T> {
    fun getEntityManager(): EntityManager
    fun clear()
    fun refresh(entity: T)
}
