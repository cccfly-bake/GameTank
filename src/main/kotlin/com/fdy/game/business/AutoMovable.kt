package com.fdy.game.business

import com.fdy.game.enum.Direction
import com.fdy.game.model.View

interface AutoMovable:View {

    /**
     * 方向
     */
    val currentDirection: Direction
    /**
     * 速度
     */
    val speed:Int

    /**
     * 自动移动
     */
    fun autoMove()
}