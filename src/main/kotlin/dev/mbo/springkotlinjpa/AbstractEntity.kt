package dev.mbo.springkotlinjpa

import jakarta.persistence.MappedSuperclass
import org.springframework.data.util.ProxyUtils
import java.io.Serializable

@MappedSuperclass
abstract class AbstractEntity<T : Serializable> : Identifiable<T> {

    /**
     * id only check for jpa entity
     */
    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (this === other) return true
        if (javaClass != ProxyUtils.getUserClass(other)) return false
        if (other !is AbstractEntity<*>) return false
        val id = getIdentifier()
        return null != id && id == other.getIdentifier()
    }

    /**
     * @see <a href="https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier">external link</a>
     * for further infos why this is a static value.
     */
    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toString(): String {
        return "${javaClass.simpleName}[${toStringAttributes()}]"
    }

    open fun toStringAttributes(): Map<String, Any?> {
        return mapOf("id" to getIdentifier())
    }

}