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
import kotlinx.coroutines.flow.collect
import ru.malis.core_domain.models.Category
import ru.malis.feature_main.R
import ru.malis.feature_main.databinding.FragmentMainBinding
import ru.malis.feature_main.internal.CategoryListAdapter
import ru.malis.feature_main.internal.MainComponentViewModel
import ru.malis.feature_main.internal.MainViewModel
import ru.malis.feature_main.internal.MainViewModelFactory
import ru.malis.feature_main.internal.adapter.category.CategoryDiffUtilCallback
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
    }

    private fun initCategories() {
        lifecycleScope.launchWhenStarted {
            mainViewModel.categoriesSharedFlow.collect { categories ->
                triggerCategoriesDiffUtil(categories)
            }
        }
        mainViewModel.getCategories()
    }

    private fun triggerCategoriesDiffUtil(newCategories: List<Category>) {
        val diffUtilCallback = CategoryDiffUtilCallback(categoryAdapter.categories, newCategories)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        newCategories[0].isSelected = true
        categoryAdapter.categories = newCategories.toMutableList()
        diffResult.dispatchUpdatesTo(categoryAdapter)
    }
}