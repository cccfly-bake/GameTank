package com.fdy.game.business

import com.fdy.game.Config
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
    fun willCollsion(blockable: Blockable): Direction? {
        //将要碰撞时做判断
        //坦克的坐标需要发生变化
        var x: Int = this.x
        var y: Int = this.y
        when (currentDirection) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }
        ///todo 监测碰撞
        //阻挡物在运动物上放 不发生碰撞
//        val collistion: Boolean = when {
//            blockable.y + blockable.height <= y -> //阻挡物再运动物上方
//                false
//            y + height <= blockable.y -> //阻挡物在运动无下方
//                false
//            blockable.x + blockable.width <= x -> //阻挡物在运动物的左方
//                false
//            else -> x + width > blockable.x
//        }


        //和边界检测
        if (x < 0) return Direction.LEFT
        if (x > Config.gameWidth - width) return Direction.RIGHT
        if (y < 0) return Direction.UP
        if (y > Config.gameHeight - height) return Direction.DOWN


        return if (checkCollision(
                blockable.x, blockable.y, blockable.width, blockable.height,
                x, y, width, height
            )
        ) currentDirection else null
    }

    /**
     *  通知我们的碰撞
     */
    fun notifyCollsion(direction: Direction?, blockable: Blockable?)
}