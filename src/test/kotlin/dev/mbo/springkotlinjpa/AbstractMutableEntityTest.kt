package dev.mbo.springkotlinjpa

import jakarta.persistence.Entity
import jakarta.persistence.EntityManager
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

open class AbstractMutableEntityTest @Autowired constructor(
    private val entityManager: EntityManager,
) : AbstractSpringBootTest() {

    @Transactional
    @Rollback
    @Test
    open fun test() {
        val entity = BaseMutableEntity()
        entityManager.persist(entity)
        assertThat(entity.id).isNotNull
    }

    @Entity
    class BaseMutableEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,
    ) : AbstractMutableEntity<Int>() {
        override fun getIdentifier(): Int? {
            return id
        }

        override fun setIdentifier(id: Int?) {
            this.id = id
        }
    }

}