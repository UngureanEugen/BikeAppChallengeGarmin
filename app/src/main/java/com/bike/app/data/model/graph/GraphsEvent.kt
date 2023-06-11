package com.bike.app.data.model.graph

sealed class GraphsEvent {

    object ShowPicker : GraphsEvent()

    object DismissPicker : GraphsEvent()

    class SetChartType(val chart: GraphType) : GraphsEvent()
}