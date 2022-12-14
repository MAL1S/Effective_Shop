package ru.malis.core_domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.malis.core_domain.models.base.BaseIdClass

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true) override val id: Int = 0,
    val image: Int,
    val title: String,
    val requestName: String,
    var isSelected: Boolean = false
): BaseIdClass()
