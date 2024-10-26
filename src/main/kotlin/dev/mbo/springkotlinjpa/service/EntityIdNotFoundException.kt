package dev.mbo.springkotlinjpa.service

import jakarta.persistence.EntityNotFoundException

@Suppress("unused")
class EntityIdNotFoundException(
    message: String, val id: Any?
) : EntityNotFoundException(message)