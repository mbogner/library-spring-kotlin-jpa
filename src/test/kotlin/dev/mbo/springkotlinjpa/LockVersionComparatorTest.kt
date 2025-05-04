package dev.mbo.springkotlinjpa

import jakarta.persistence.OptimisticLockException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LockVersionComparatorTest {

    private class TestEntity(
        var id: Int? = null,
        lockVersion: Int = 0,
    ) : AbstractVersionedMutableEntity<Int>(
        lockVersion = lockVersion,
    ) {

        override fun getIdentifier(): Int? {
            return id
        }

        override fun setIdentifier(id: Int?) {
            this.id = id
        }

    }

    @Test
    fun compareSameVersion() {
        LockVersionComparator.compare(entity = TestEntity(lockVersion = 1), version = 1)
    }

    @Test
    fun compareDifferentVersions() {
        Assertions.assertThrows(OptimisticLockException::class.java) {
            LockVersionComparator.compare(entity = TestEntity(lockVersion = 1), version = 2)
        }
    }
}