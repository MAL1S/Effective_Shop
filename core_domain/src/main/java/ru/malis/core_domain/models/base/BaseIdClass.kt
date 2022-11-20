package ru.malis.core_domain.models.base

abstract class BaseIdClass(
    @Transient open val id: Int? = null
)
