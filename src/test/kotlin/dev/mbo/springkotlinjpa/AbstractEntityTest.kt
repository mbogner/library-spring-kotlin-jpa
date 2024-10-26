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

open class AbstractEntityTest @Autowired constructor(
    private val entityManager: EntityManager,
) : AbstractSpringBootTest() {

    @Transactional
    @Rollback
    @Test
    open fun test() {
        val entity = BaseEntity()
        entityManager.persist(entity)
        assertThat(entity.id).isNotNull
    }

    @Test
    fun testIdentifier() {
        val entity = BaseEntity(id = 1)
        assertThat(entity.id).isEqualTo(1)
        entity.setIdentifier(null)
        assertThat(entity.id).isNull()
    }

    @Entity
    class BaseEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,
    ) : AbstractEntity<Int>() {
        override fun getIdentifier(): Int? {
            return id
        }

        override fun setIdentifier(id: Int?) {
            this.id = id
        }
    }

}