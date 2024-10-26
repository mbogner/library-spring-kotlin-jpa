package dev.mbo.springkotlinjpa.service

import dev.mbo.springkotlinjpa.AbstractEntity
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import java.io.Serializable

@Suppress("unused")
abstract class AbstractDomainService<ID : Serializable, ENTITY : AbstractEntity<ID>>(
    @Suppress("MemberVisibilityCanBePrivate")
    protected val repository: JpaRepository<ENTITY, ID>
) {

    @Transactional(Transactional.TxType.MANDATORY)
    protected open fun create(entity: ENTITY): ENTITY {
        return repository.save(entity)
    }

    @Transactional(Transactional.TxType.MANDATORY)
    protected open fun delete(entity: ENTITY) {
        return repository.delete(entity)
    }

    @Transactional(Transactional.TxType.MANDATORY)
    protected open fun deleteById(id: ID) {
        delete(repository.getReferenceById(id))
    }

    @Transactional(Transactional.TxType.MANDATORY)
    protected open fun findById(id: ID): ENTITY {
        return repository.findById(id).orElseThrow { EntityIdNotFoundException("entity with id $id not found", id) }
    }

    @Transactional(Transactional.TxType.MANDATORY)
    protected open fun update(id: ID, update: (existing: ENTITY) -> Unit): ENTITY {
        val existing = findById(id)
        update.invoke(existing)
        return existing
    }

    @Transactional(Transactional.TxType.MANDATORY)
    protected open fun findPage(pageRequest: PageRequest): Page<ENTITY> {
        return repository.findAll(pageRequest)
    }

    protected abstract fun handledType(): Class<ENTITY>

}