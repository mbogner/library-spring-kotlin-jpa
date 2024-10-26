package dev.mbo.springkotlinjpa.service

import jakarta.persistence.EntityNotFoundException

class EntityIdNotFoundException(
    message: String, val id: Any
) : EntityNotFoundException(message)