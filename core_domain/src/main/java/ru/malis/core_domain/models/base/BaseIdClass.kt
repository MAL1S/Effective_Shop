package ru.malis.core_domain.models.base

import androidx.room.Ignore

abstract class BaseIdClass(
    @Transient open val id: Int? = null
)
