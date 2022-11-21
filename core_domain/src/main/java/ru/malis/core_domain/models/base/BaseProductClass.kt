package ru.malis.core_domain.models.base

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


abstract class BaseProductClass(
    @Transient override val id: Int = 0,
    @Transient open val isFavorites: Boolean? = null,
    @Transient open val title: String? = null,
    @Transient open val price: Int? = null,
    @Transient open val pictureUrl: String? = null,
): BaseIdClass(), Parcelable
