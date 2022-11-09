package ru.malis.feature_main.api

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.Lazy
import androidx.fragment.app.viewModels
import ru.malis.feature_main.R
import ru.malis.feature_main.databinding.FragmentMainBinding
import ru.malis.feature_main.internal.CategoryListAdapter
import ru.malis.feature_main.internal.MainComponentViewModel
import ru.malis.feature_main.internal.MainViewModel
import ru.malis.feature_main.internal.MainViewModelFactory
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

    }
}