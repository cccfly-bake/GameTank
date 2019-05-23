package com.fdy.game.business

import com.fdy.game.enum.Direction


/**
 * 移动运动的能力
 */
interface Movable : com.fdy.game.model.View {
    /**
     * 可移动物体存在方向
     */
    val currentDirection: Direction
    /**
     * 可移动物体速度
     */
    val speed: Int

    /**
     * 判断移动的物体是否和阻塞物体发生碰撞
     * @return 要碰撞的方向 如果为null 说明没有碰撞
     */
    fun willCollsion(blockable: Blockable): Direction?

    /**
     *  通知我们的碰撞
     */
    fun notifyCollsion(direction: Direction?, blockable: Blockable?)
}