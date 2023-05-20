package com.lttrung.dormitory.ui.viewrooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lttrung.dormitory.databinding.FragmentFilterSortBinding
import com.lttrung.dormitory.domain.data.local.models.FilterSortModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterSortFragment(
    private val filterSort: FilterSortModel
) : BottomSheetDialogFragment() {
    private val binding: FragmentFilterSortBinding by lazy {
        FragmentFilterSortBinding.inflate(layoutInflater)
    }
    private val viewRoomsViewModel: ViewRoomsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val bedRange = filterSort.bedsRange

        binding.bedRange.setLabelFormatter { value -> "${value.toInt()} beds" }
        binding.bedRange.valueFrom = filterSort.minBeds.toFloat()
        binding.bedRange.valueTo = filterSort.maxBeds.toFloat()
        binding.bedRange.values = listOf(bedRange[0].toFloat(), bedRange[1].toFloat())
        binding.rbSortBy.check(filterSort.sortPosition)

        binding.buttonApply.setOnClickListener {
            val filterSort = FilterSortModel(
                binding.bedRange.valueFrom.toInt(),
                binding.bedRange.valueTo.toInt(),
                listOf(binding.bedRange.values[0].toInt(), binding.bedRange.values[1].toInt()),
                binding.rbSortBy.checkedRadioButtonId
            )
            viewRoomsViewModel.applyFilterSort(filterSort)
            dismiss()
        }
        return binding.root
    }

    companion object {
        const val TAG = "com.lttrung.dormitory.FilterSortFragment"
    }
}