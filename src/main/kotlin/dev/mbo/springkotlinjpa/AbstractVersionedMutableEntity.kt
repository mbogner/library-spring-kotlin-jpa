package dev.mbo.springkotlinjpa

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Version
import java.io.Serializable

@MappedSuperclass
abstract class AbstractVersionedMutableEntity<T : Serializable>(

    @field:Version
    @field:Column(name = "lock_version", nullable = false)
    var lockVersion: Int = 0

) : AbstractMutableEntity<T>() {

    override fun toStringAttributes(): Map<String, Any?> {
        return super.toStringAttributes()
            .plus("lockVersion" to lockVersion)
    }

}