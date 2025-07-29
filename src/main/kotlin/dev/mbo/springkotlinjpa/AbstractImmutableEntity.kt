package dev.mbo.springkotlinjpa

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import java.io.Serializable
import java.time.Instant

@MappedSuperclass
abstract class AbstractImmutableEntity<T : Serializable>(

    @field:Column(name = "created_at", nullable = false, updatable = false)
    open var createdAt: Instant = Instant.now()

) : AbstractEntity<T>() {

    override fun toStringAttributes(): Map<String, Any?> {
        return super.toStringAttributes()
            .plus("createdAt" to createdAt)
    }

}