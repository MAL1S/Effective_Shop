package ru.malis.feature_main.api

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import ru.malis.feature_main.R
import ru.malis.feature_main.databinding.FragmentBottomSheetFilterBinding

class FilterBottomSheetDialogFragment(
    private val data: List<String>
) : BottomSheetDialogFragment() {

    private val binding by viewBinding(FragmentBottomSheetFilterBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, ru.malis.core_style.R.style.DialogTheme_Transparent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(
            R.layout.fragment_bottom_sheet_filter, container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDropDown()
    }

    private fun initDropDown() {
        initBrandDropDown()
    }

    private fun initBrandDropDown() {
        val adapter = ArrayAdapter(
            requireContext(), R.layout.item_drop_down, data
        )
        binding.filterDropdownBrand.apply {
            setText(data[0])
            setAdapter(adapter)
            setOnItemClickListener { parent, view, position, id ->
                //binding.filterDropdownBrandContainer.hint = brands[position]
            }

            setOnFocusChangeListener { v, hasFocus ->
                binding.filterDropdownBrandContainer.isActivated = true
            }
        }
    }
}