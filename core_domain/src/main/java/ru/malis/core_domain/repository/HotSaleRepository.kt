package ru.malis.core_domain.repository

import ru.malis.core_domain.models.Category
import ru.malis.core_domain.models.HotSale

interface HotSaleRepository {

    suspend fun getHotSales(): List<HotSale>
}