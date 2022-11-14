package ru.malis.feature_main.api

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.Lazy
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import ru.malis.core_domain.models.BestSeller
import ru.malis.core_domain.models.Category
import ru.malis.core_domain.models.HotSale
import ru.malis.feature_main.R
import ru.malis.feature_main.databinding.FragmentMainBinding
import ru.malis.feature_main.internal.CategoryListAdapter
import ru.malis.feature_main.internal.MainComponentViewModel
import ru.malis.feature_main.internal.MainViewModel
import ru.malis.feature_main.internal.MainViewModelFactory
import ru.malis.feature_main.internal.adapter.base.BaseDiffUtilCallback
import ru.malis.feature_main.internal.adapter.bestseller.BestSellerListAdapter
import ru.malis.feature_main.internal.adapter.bestseller.OnBestSellerClickListener
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

    private val categoryAdapter = CategoryListAdapter {
        //TODO: open category
    }

    private val hotSaleAdapter = HotSaleListAdapter {
        //TODO: open hot sale product
    }

    private val bestSellerAdapter = BestSellerListAdapter(object: OnBestSellerClickListener {
        override fun onItemClicked(bestSeller: BestSeller) {
            //TODO: best seller item
        }

        override fun onFavoriteClicked(bestSeller: BestSeller) {
            //TODO: like/dislike best seller item
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
        initHotSales()
        initBestSellers()
    }

    private fun initCategories() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.categoriesSharedFlow.collect { categories ->
                triggerCategoriesDiffUtil(categories)
            }
        }
        mainViewModel.getCategories()
    }

    private fun initHotSales() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.hotSalesSharedFlow.collect { hotSales ->
                triggerHotSalesDiffUtil(hotSales)
            }
        }
        mainViewModel.getCategories()
    }

    private fun initBestSellers() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.bestSellerSharedFlow.collect { bestSellers ->
                triggerBestSellersDiffUtil(bestSellers)
            }
        }
        mainViewModel.getCategories()
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