package dev.mbo.springkotlinjpa

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import java.io.Serializable
import java.time.Instant

@MappedSuperclass
abstract class AbstractMutableEntity<T : Serializable>(

    @field:Column(name = "updated_at", nullable = false)
    open var updatedAt: Instant = Instant.now()

) : AbstractImmutableEntity<T>(
    createdAt = updatedAt,
) {

    override fun toStringAttributes(): Map<String, Any?> {
        return super.toStringAttributes()
            .plus("updatedAt" to updatedAt)
    }

}