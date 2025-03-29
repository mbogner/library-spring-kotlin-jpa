package dev.mbo.springkotlinjpa.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class StringListConverter : AttributeConverter<List<String>, String> {

    override fun convertToDatabaseColumn(obj: List<String>?): String? {
        if (obj.isNullOrEmpty()) return null
        return obj.joinToString(",")
    }

    override fun convertToEntityAttribute(dbData: String?): List<String> {
        return dbData
            ?.split(",")
            ?.map { it.trim() }
            ?.filter { it.isNotEmpty() }
            ?: emptyList()
    }

}