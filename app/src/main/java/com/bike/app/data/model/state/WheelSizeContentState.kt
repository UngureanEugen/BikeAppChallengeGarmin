package com.bike.app.data.model.state

import com.bike.app.data.model.WheelSize

data class WheelSizeContentState(
    val isExpanded: Boolean = false,
    val wheelSize: WheelSize = WheelSize.Small
)
