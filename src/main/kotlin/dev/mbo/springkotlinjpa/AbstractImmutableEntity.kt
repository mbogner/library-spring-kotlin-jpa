package dev.mbo.springkotlinjpa

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import java.io.Serializable
import java.time.Instant

@MappedSuperclass
abstract class AbstractImmutableEntity<T : Serializable>(

    @field:Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: Instant? = null

) : AbstractEntity<T>() {

    @PrePersist
    protected fun prePersist() {
        this.createdAt = Instant.now()
    }

    override fun toStringAttributes(): Map<String, Any?> {
        return super.toStringAttributes()
            .plus("createdAt" to createdAt)
    }

}