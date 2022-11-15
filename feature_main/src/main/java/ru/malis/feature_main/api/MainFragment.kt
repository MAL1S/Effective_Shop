package ru.malis.feature_main.api

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.Lazy
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import ru.malis.core_domain.models.BestSeller
import ru.malis.core_domain.models.Category
import ru.malis.core_domain.models.HotSale
import ru.malis.feature_main.R
import ru.malis.feature_main.databinding.FragmentMainBinding
import ru.malis.feature_main.internal.adapter.category.CategoryListAdapter
import ru.malis.feature_main.internal.MainComponentViewModel
import ru.malis.feature_main.internal.MainViewModel
import ru.malis.feature_main.internal.MainViewModelFactory
import ru.malis.feature_main.internal.adapter.base.BaseDiffUtilCallback
import ru.malis.feature_main.internal.adapter.bestseller.BestSellerListAdapter
import ru.malis.feature_main.internal.adapter.bestseller.OnBestSellerClickListener
import ru.malis.feature_main.internal.adapter.decorator.AllSpaceItemDecorator
import ru.malis.feature_main.internal.adapter.decorator.HorizontalSpaceItemDecorator
import ru.malis.feature_main.internal.adapter.hotsales.HotSaleListAdapter
import javax.inject.Inject


class MainFragment : Fragment(R.layout.fragment_main) {

    @Inject
    internal lateinit var mainViewModelFactory: Lazy<MainViewModelFactory>

    private val binding by viewBinding(FragmentMainBinding::bind)
    private val mainViewModel: MainViewModel by viewModels {
        mainViewModelFactory.get()
    }
    private val componentViewModel: MainComponentViewModel by viewModels()

    private val categoryAdapter = CategoryListAdapter { categories, currentCategory ->
        val newCategories = categories.map {
            if (it.id == currentCategory.id) {
                it.copy(isSelected = true)
            } else {
                it.copy(isSelected = false)
            }
        }
        triggerCategoriesDiffUtil(newCategories)
    }

    private val hotSaleAdapter = HotSaleListAdapter {
        //TODO: open hot sale product
    }

    private val bestSellerAdapter = BestSellerListAdapter(object : OnBestSellerClickListener {
        override fun onItemClicked(bestSeller: BestSeller) {
            //TODO: best seller item
        }

        override fun onFavoriteClicked(
            bestSellers: List<BestSeller>,
            checkedBestSeller: BestSeller
        ) {
            //TODO: replace with 'updateBestSellerItem' api call through MainViewModel and get info from single-source-of-truth
            val newBestSellers = bestSellers.map {
                if (it.id == checkedBestSeller.id) {
                    it.copy(isFavorites = !it.isFavorites)
                } else it
            }
            triggerBestSellersDiffUtil(newBestSellers)
        }
    })

    override fun onAttach(context: Context) {
        super.onAttach(context)

        componentViewModel.mainComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        initCategories()
        initProduct()
    }

    private fun initCategories() {
        binding.mainRcvCategories.apply {
            adapter = categoryAdapter
            addItemDecoration(HorizontalSpaceItemDecorator())
        }

        lifecycleScope.launchWhenStarted {
            mainViewModel.categoriesSharedFlow.collect { categories ->
                val list = listOf<Category>(
                    Category(
                        id = 0,
                        image = ru.malis.core_style.R.drawable.ic_phone,
                        title = "Phones",
                        requestName = "",
                        isSelected = true
                    ),
                    Category(
                        id = 1,
                        image = ru.malis.core_style.R.drawable.ic_computer,
                        title = "Computer",
                        requestName = ""
                    ),
                    Category(
                        id = 2,
                        image = ru.malis.core_style.R.drawable.ic_health,
                        title = "Health",
                        requestName = ""
                    ),
                    Category(
                        id = 3,
                        image = ru.malis.core_style.R.drawable.ic_books,
                        title = " Books",
                        requestName = ""
                    ),
                    Category(
                        id = 5,
                        image = ru.malis.core_style.R.drawable.ic_tools,
                        title = "Tools",
                        requestName = ""
                    ),
                )
                triggerCategoriesDiffUtil(list)
            }
        }
        mainViewModel.getCategories()
    }

    private fun initHotSales() {
        binding.mainRcvHotSales.apply {
            adapter = hotSaleAdapter
            addItemDecoration(HorizontalSpaceItemDecorator())
        }

        lifecycleScope.launchWhenStarted {
            mainViewModel.hotSalesSharedFlow.collect { hotSales ->
                triggerHotSalesDiffUtil(hotSales)
            }
        }
    }

    private fun initBestSellers() {
        binding.mainRcvBestSeller.apply {
            adapter = bestSellerAdapter
            itemAnimator = null
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(AllSpaceItemDecorator())
        }

        lifecycleScope.launchWhenStarted {
            mainViewModel.bestSellerSharedFlow.collect { bestSellers ->
                triggerBestSellersDiffUtil(bestSellers)
            }
        }
    }

    private fun initProduct() {
        initHotSales()
        initBestSellers()

        mainViewModel.getProduct(
            Category(
                id = 0,
                image = 0,
                title = "",
                requestName = "",
                isSelected = false
            )
        )
    }

    private fun triggerCategoriesDiffUtil(newCategories: List<Category>) {
        val diffUtilCallback = BaseDiffUtilCallback(categoryAdapter.categories, newCategories)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        categoryAdapter.categories = newCategories.toMutableList()
        diffResult.dispatchUpdatesTo(categoryAdapter)
    }

    private fun triggerHotSalesDiffUtil(newHotSales: List<HotSale>) {
        val diffUtilCallback = BaseDiffUtilCallback(hotSaleAdapter.hotSales, newHotSales)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        hotSaleAdapter.hotSales = newHotSales.toMutableList()
        diffResult.dispatchUpdatesTo(hotSaleAdapter)
    }

    private fun triggerBestSellersDiffUtil(newBestSellers: List<BestSeller>) {
        val diffUtilCallback = BaseDiffUtilCallback(hotSaleAdapter.hotSales, newBestSellers)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        bestSellerAdapter.bestSellers = newBestSellers.toMutableList()
        diffResult.dispatchUpdatesTo(bestSellerAdapter)
    }
}