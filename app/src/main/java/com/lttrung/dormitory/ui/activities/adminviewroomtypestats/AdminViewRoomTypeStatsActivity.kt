package com.lttrung.dormitory.ui.activities.adminviewroomtypestats

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.core.cartesian.series.Line
import com.anychart.data.Mapping
import com.anychart.enums.Anchor
import com.anychart.enums.Orientation
import com.anychart.enums.Position
import com.anychart.enums.ScaleStackMode
import com.anychart.scales.Linear
import com.google.android.material.snackbar.Snackbar
import com.lttrung.dormitory.databinding.ActivityAdminViewRoomTypeStatsBinding
import com.lttrung.dormitory.domain.data.network.models.RoomTypeStat
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminViewRoomTypeStatsActivity : AppCompatActivity() {
    private val binding: ActivityAdminViewRoomTypeStatsBinding by lazy {
        ActivityAdminViewRoomTypeStatsBinding.inflate(layoutInflater)
    }
    private val viewModel: AdminViewRoomTypeStatsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupObserver()
        viewModel.getRoomTypeStats()
    }

    private fun setupObserver() {
        viewModel.getRoomTypeStatsLiveData.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    val roomTypeStats = resource.data
                    drawChart(roomTypeStats)
                }
                is Resource.Error -> {
                    Snackbar.make(this, binding.root, resource.message, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun drawChart(roomTypeStats: List<RoomTypeStat>) {
        val cartesian = AnyChart.cartesian()

        cartesian.animation(true)

        cartesian.title("Thống kê % sinh viên và giá phòng theo loại phòng")

        cartesian.yScale().stackMode(ScaleStackMode.VALUE)

        val scalesLinear = Linear.instantiate()
        scalesLinear.minimum(0.0)
        scalesLinear.maximum(100.0)
        scalesLinear.ticks("{ interval: 20 }")

        val extraYAxis = cartesian.yAxis(1.0)
        extraYAxis.orientation(Orientation.RIGHT)
        extraYAxis.labels()
            .padding(0.0, 0.0, 0.0, 5.0)
            .format("{%Value} vnđ")

        val data: MutableList<DataEntry> = ArrayList()
        val sum = roomTypeStats.stream().mapToInt {
            it.studentCount
        }.sum()
        for (roomTypeStat in roomTypeStats) {
            data.add(
                CustomDataEntry(
                    roomTypeStat.type,
                    (roomTypeStat.studentCount.toDouble() / sum.toDouble()) * 100,
                    roomTypeStat.price
                )
            )
        }

        val set = com.anychart.data.Set.instantiate()
        set.data(data)
        val lineData: Mapping = set.mapAs("{ x: 'x', value: 'value' }")
        val column1Data: Mapping = set.mapAs("{ x: 'x', value: 'value2' }")

        val column = cartesian.column(column1Data)
        column.tooltip()
            .titleFormat("Loại: {%X}")
            .position(Position.CENTER_BOTTOM)
            .anchor(Anchor.CENTER_BOTTOM)
            .offsetX(0)
            .offsetY(5)
            .format("Giá: {%Value} vnđ")

        cartesian.crosshair(true)

        val line: Line = cartesian.line(lineData)
        line.yScale(scalesLinear)

        binding.chart.setChart(cartesian)
    }

    private class CustomDataEntry(
        x: String?,
        value: Number?,
        value2: Number?
    ) :
        ValueDataEntry(x, value) {
        init {
            setValue("value2", value2)
        }
    }
}