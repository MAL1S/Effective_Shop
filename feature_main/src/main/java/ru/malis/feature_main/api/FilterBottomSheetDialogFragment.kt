package ru.malis.feature_main.api

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import ru.malis.feature_main.R
import ru.malis.feature_main.databinding.FragmentBottomSheetFilterBinding

class FilterBottomSheetDialogFragment(
    private val brands: List<String>,
    private val prices: List<String>,
    private val sizes: List<String>,
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

        init()
    }

    private fun init() {
        initDropDown(
            binding.filterDropdownBrand,
            binding.filterDropdownBrandContainer,
            brands
        )
        initDropDown(
            binding.filterDropdownPrice,
            binding.filterDropdownPriceContainer,
            prices
        )
        initDropDown(
            binding.filterDropdownSize,
            binding.filterDropdownSizeContainer,
            sizes
        )

        initDoneButton()
        initBackButton()
    }

    private fun initDoneButton() {
        binding.filterBtnDone.setOnClickListener {
            //TODO: do applying filters logic
            dialog?.dismiss()
        }
    }

    private fun initBackButton() {
        binding.filterBtnCancel.setOnClickListener {
            dialog?.dismiss()
        }
    }

    private fun initDropDown(
        dropDown: AutoCompleteTextView,
        dropDownContainer: TextInputLayout,
        items: List<String>
    ) {
        val adapter = ArrayAdapter(
            requireContext(), R.layout.item_drop_down, items
        )
        dropDown.apply {
            if (items.isNotEmpty()) {
                setText(items[0])
            }
            setAdapter(adapter)
            setOnItemClickListener { parent, view, position, id ->
                dropDownContainer.isActivated = false
            }

            setOnClickListener {
                dropDownContainer.isActivated = !dropDownContainer.isActivated
            }
        }
    }
}