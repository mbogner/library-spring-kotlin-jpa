package dev.mbo.springkotlinjpa

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PreUpdate
import jakarta.persistence.Version
import java.io.Serializable
import java.time.Instant

@MappedSuperclass
abstract class AbstractMutableEntity<T : Serializable>(

    @field:Column(name = "updated_at", insertable = false)
    var updatedAt: Instant? = null,

    @field:Version
    @field:Column(name = "lock_version", nullable = false)
    var lockVersion: Int? = null,

    createdAt: Instant? = null
) : AbstractImmutableEntity<T>(
    createdAt = createdAt
) {

    @PreUpdate
    protected fun preUpdate() {
        this.updatedAt = Instant.now()
    }

    override fun toStringAttributes(): Map<String, Any?> {
        return super.toStringAttributes()
            .plus("updatedAt" to updatedAt)
            .plus("lockVersion" to lockVersion)
    }

}