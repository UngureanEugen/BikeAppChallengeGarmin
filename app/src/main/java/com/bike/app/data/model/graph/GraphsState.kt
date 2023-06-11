package com.bike.app.data.model.graph

data class GraphsState(
    val selectedChart: GraphType = GraphType.LINE,
    val showChartPicker: Boolean = false,
    val chartData: List<GraphData> = GraphsDataFactory.makeChartData()
)