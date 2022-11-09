package ru.malis.feature_main.api

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.malis.feature_main.R
import ru.malis.feature_main.databinding.FragmentMainBinding
import ru.malis.feature_main.internal.CategoryListAdapter


class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    private val categoryAdapter = CategoryListAdapter {
        //TODO: open category
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {

    }
}