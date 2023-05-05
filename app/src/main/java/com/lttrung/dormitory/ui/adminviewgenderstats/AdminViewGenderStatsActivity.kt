package com.lttrung.dormitory.ui.adminviewgenderstats

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.enums.Align
import com.anychart.enums.LegendLayout
import com.google.android.material.snackbar.Snackbar
import com.lttrung.dormitory.databinding.ActivityAdminViewGenderStatsBinding
import com.lttrung.dormitory.domain.data.network.models.GenderStats
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AdminViewGenderStatsActivity : AppCompatActivity() {
    private val binding: ActivityAdminViewGenderStatsBinding by lazy {
        ActivityAdminViewGenderStatsBinding.inflate(layoutInflater)
    }
    private val viewModel: AdminViewGenderStatsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupObserver()
        viewModel.getGenderStats()
    }

    private fun setupObserver() {
        viewModel.getGenderStatsLiveData.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    val genderStats = resource.data
                    drawChart(genderStats)
                }
                is Resource.Error -> {
                    Snackbar.make(this, binding.root, resource.message, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun drawChart(genderStats: GenderStats) {
        val pie = AnyChart.pie()

//        pie.setOnClickListener(object :
//            ListenersInterface.OnClickListener(arrayOf<String>("x", "value")) {
//            override fun onClick(event: Event) {
//                Toast.makeText(
//                    this@AdminViewGenderStatsActivity,
//                    event.data["x"] + ":" + event.data["value"],
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        })

        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("Nam", genderStats.maleCount))
        data.add(ValueDataEntry("Nữ", genderStats.totalCount - genderStats.maleCount))

        pie.data(data)

        pie.title("Thống kê giới tính")

        pie.labels().position("outside")

        pie.legend().title().enabled(true)
        pie.legend().title()
            .text("Retail channels")
            .padding(0.0, 0.0, 10.0, 0.0)

        pie.legend()
            .position("center-bottom")
            .itemsLayout(LegendLayout.HORIZONTAL)
            .align(Align.CENTER)

        binding.chart.setChart(pie)
    }
}