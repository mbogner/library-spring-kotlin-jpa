package dev.mbo.springkotlinjpa

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Transient
import java.io.Serializable

interface Identifiable<T : Serializable> : Serializable {

    @Transient
    @JsonIgnore
    fun getIdentifier(): T?

}