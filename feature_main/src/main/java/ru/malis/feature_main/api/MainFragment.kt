package ru.malis.feature_main.api

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.TouchDelegate
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.Lazy
import ru.malis.core_base.BaseDiffUtilCallback
import ru.malis.core_domain.models.BestSeller
import ru.malis.core_domain.models.Category
import ru.malis.core_domain.models.HotSale
import ru.malis.feature_main.R
import ru.malis.feature_main.databinding.FragmentMainBinding
import ru.malis.feature_main.internal.MainComponentViewModel
import ru.malis.feature_main.internal.MainViewModel
import ru.malis.feature_main.internal.MainViewModelFactory
import ru.malis.feature_main.internal.adapter.bestseller.BestSellerListAdapter
import ru.malis.feature_main.internal.adapter.bestseller.OnBestSellerClickListener
import ru.malis.feature_main.internal.adapter.category.CategoryListAdapter
import ru.malis.feature_main.internal.adapter.decorator.AllSpaceItemDecorator
import ru.malis.feature_main.internal.adapter.decorator.CategorySpaceItemDecorator
import ru.malis.core_base.CenterSmoothScroller
import ru.malis.feature_main.internal.adapter.decorator.HotSaleSpaceItemDecorator
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

    private val hotSaleAdapter = HotSaleListAdapter(0) {

    }

    private val bestSellerAdapter = BestSellerListAdapter(object : OnBestSellerClickListener {
        override fun onItemClicked(bestSeller: BestSeller) {
            mainViewModel.navigateToProductDetails(this@MainFragment)
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

    override fun onResume() {
        super.onResume()

        binding.mainRcvHotSales.layoutManager?.scrollToPosition(Int.MAX_VALUE / 2)

        val smoothScroller = CenterSmoothScroller(requireContext())
        smoothScroller.targetPosition = Int.MAX_VALUE / 2
        binding.mainRcvHotSales.layoutManager?.startSmoothScroll(
            smoothScroller
        )
    }

    private fun init() {
        initCategories()
        initProduct()
        initFilters()
        initNavigation()
    }

    private fun initNavigation() {
        binding.bottomNav.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.cart -> binding.mainBottomNav.visibility = View.GONE
//            }
            false
        }
    }

    private fun initCategories() {
        binding.mainRcvCategories.apply {
            //adapter = categoryAdapter
            addItemDecoration(CategorySpaceItemDecorator())
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
            binding.mainRcvHotSales.initialize(hotSaleAdapter)
            addItemDecoration(HotSaleSpaceItemDecorator())
        }

        lifecycleScope.launchWhenStarted {
            mainViewModel.hotSalesSharedFlow.collect { hotSales ->
                val displayMetrics = DisplayMetrics()
                (requireContext() as Activity).windowManager.defaultDisplay.getMetrics(
                    displayMetrics
                )
                hotSaleAdapter.deviceWidth = displayMetrics.widthPixels

                hotSaleAdapter.hotSales = hotSales.toMutableList()
                binding.mainRcvHotSales.initialize(hotSaleAdapter)
                binding.mainRcvHotSales.adapter?.notifyDataSetChanged()
                binding.mainRcvHotSales.layoutManager?.scrollToPosition(Int.MAX_VALUE / 2)

                val smoothScroller = CenterSmoothScroller(requireContext())
                smoothScroller.targetPosition = Int.MAX_VALUE / 2
                binding.mainRcvHotSales.layoutManager?.startSmoothScroll(
                    smoothScroller
                )
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
        val diffUtilCallback =
            BaseDiffUtilCallback(categoryAdapter.categories, newCategories)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        categoryAdapter.categories = newCategories.toMutableList()
        diffResult.dispatchUpdatesTo(categoryAdapter)
    }

    private fun triggerHotSalesDiffUtil(newHotSales: List<HotSale>) {
        val diffUtilCallback =
            BaseDiffUtilCallback(hotSaleAdapter.hotSales, newHotSales)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        hotSaleAdapter.hotSales = newHotSales.toMutableList()
        diffResult.dispatchUpdatesTo(hotSaleAdapter)
        binding.mainRcvHotSales.layoutManager?.scrollToPosition(Int.MAX_VALUE / 2)
    }

    private fun triggerBestSellersDiffUtil(newBestSellers: List<BestSeller>) {
        val diffUtilCallback =
            BaseDiffUtilCallback(hotSaleAdapter.hotSales, newBestSellers)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        bestSellerAdapter.bestSellers = newBestSellers.toMutableList()
        diffResult.dispatchUpdatesTo(bestSellerAdapter)
    }

    private fun initFilters() {
        val parent = binding.mainBtnFilter.parent as View
        parent.post {
            val rect = Rect()
            binding.mainBtnFilter.getHitRect(rect)
            rect.top -= 40
            rect.left -= 40
            rect.bottom += 40
            rect.right += 40
            parent.touchDelegate = TouchDelegate(rect, binding.mainBtnFilter)
        }

        binding.mainBtnFilter.setOnClickListener {
            val filterBottomSheetDialogFragment = FilterBottomSheetDialogFragment(
                brands = listOf("Samsung", "Iphone", "OnePlus"),
                prices = listOf("$300 - $500", "$500 - $700", "$700 - $1000"),
                sizes = listOf("4.5 to 5.5 inches", "5.5 to 6.5 inches", "6.5 to 7.5 inches"),
            )
            filterBottomSheetDialogFragment.dialog?.setCanceledOnTouchOutside(true)
            filterBottomSheetDialogFragment.show(parentFragmentManager, "bottom_sheet_filter")
        }
    }
}