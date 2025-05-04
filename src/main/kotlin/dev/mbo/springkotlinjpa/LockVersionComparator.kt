package dev.mbo.springkotlinjpa

import jakarta.persistence.OptimisticLockException

object LockVersionComparator {

    fun compare(entity: AbstractVersionedMutableEntity<*>, version: Int) {
        if (entity.lockVersion != version) {
            throw OptimisticLockException("entity ${entity.getIdentifier()} has lockVersion ${entity.lockVersion} which is not equal to $version")
        }
    }

}