package com.lttrung.dormitory.ui.adminviewstudentstats

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.enums.Anchor
import com.anychart.enums.HoverMode
import com.anychart.enums.Position
import com.anychart.enums.TooltipPositionMode
import com.google.android.material.snackbar.Snackbar
import com.lttrung.dormitory.databinding.ActivityAdminViewStudentStatsBinding
import com.lttrung.dormitory.domain.data.network.models.StudentStat
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminViewStudentStatsActivity : AppCompatActivity() {
    private val binding: ActivityAdminViewStudentStatsBinding by lazy {
        ActivityAdminViewStudentStatsBinding.inflate(layoutInflater)
    }
    private val viewModel: AdminViewStudentStatsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupObserver()
        viewModel.getStudentStats()
    }

    private fun setupObserver() {
        viewModel.getStudentStatsLiveData.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    val studentStats = resource.data
                    drawChart(studentStats)
                }
                is Resource.Error -> {
                    Snackbar.make(this, binding.root, resource.message, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun drawChart(studentStats: List<StudentStat>) {
        val cartesian = AnyChart.column()
        val data: MutableList<DataEntry> = ArrayList()
        var max = 0
        for (studentStat in studentStats) {
            if (studentStat.studentCount > max) {
                max = studentStat.studentCount
            }
            data.add(ValueDataEntry("Học kỳ ${studentStat.term}", studentStat.studentCount))
        }

        val column = cartesian.column(data)

        column.tooltip()
            .titleFormat("{%X}")
            .position(Position.CENTER_BOTTOM)
            .anchor(Anchor.CENTER_BOTTOM)
            .offsetX(0)
            .offsetY(5)
            .format("{%Value} SV")

        cartesian.animation(true)
        cartesian.title("Thống kê sinh viên theo học kỳ trong năm")
        
        cartesian.yScale().minimumGap(1).maximum(max)

        cartesian.yAxis(0).labels().format("{%Value}")

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)
        cartesian.interactivity().hoverMode(HoverMode.BY_X)

        cartesian.xAxis(0).title("Học kỳ")
        cartesian.yAxis(0).title("Số lượng sinh viên")

        binding.columnChart.setChart(cartesian)
    }
}