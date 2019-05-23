package com.fdy.game.model

import com.fdy.game.enum.Direction
import org.itheima.kotlin.game.core.Painter

/**
 * 子弹 create 函数返回两个值,两个值对应的是x,y
 * 闭包方式创建
 */
class Bullet(
    direction: Direction, create: (width: Int, height: Int) -> Pair<Int, Int>
) : View {
    override val x: Int
    override val y: Int
    override val width: Int
    override val height: Int
    private var imagePath: String = when (direction) {
        Direction.UP -> "img/shot_top.gif"
        Direction.DOWN -> "img/shot_bottom.gif"
        Direction.LEFT -> "img/shot_left.gif"
        Direction.RIGHT -> "img/shot_right.gif"
    }

    init {
        //先计算我们的宽度和高度
        val size = Painter.size(imagePath)
        width = size[0]
        height = size[1]
        val pair = create.invoke(width, height)
        x = pair.first
        y = pair.second
    }

    //給子弹一个方向,方向由坦克来决定
    override fun draw() {
        Painter.drawImage(imagePath, x, y)
    }
}