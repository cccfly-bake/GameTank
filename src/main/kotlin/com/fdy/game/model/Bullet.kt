package com.fdy.game.model

import com.fdy.game.Config
import com.fdy.game.business.Attackable
import com.fdy.game.business.AutoMovable
import com.fdy.game.business.Destroyable
import com.fdy.game.business.Sufferable
import com.fdy.game.enum.Direction
import com.fdy.game.ext.checkCollision
import org.itheima.kotlin.game.core.Painter

/**
 * 子弹 create 函数返回两个值,两个值对应的是x,y
 * 闭包方式创建
 */
class Bullet(
    override val owner: View,
    override val currentDirection: Direction, create: (width: Int, height: Int) -> Pair<Int, Int>

) : AutoMovable, Destroyable, Attackable {
    override fun showDestroy(): Array<View>? {
        return null
    }

    override val attackPower: Int=1
    override val speed: Int = 8
    override var x: Int = 0
    override var y: Int = 0
    override val width: Int
    override val height: Int
    private var isDestroy: Boolean = false
    private var imagePath: String = when (currentDirection) {
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

    override fun autoMove() {
        //根据自己的方向,来改变自己的xy
        when (currentDirection) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }
    }

    override fun isDestroyable(): Boolean {
        if (isDestroy) {
            return true
        }
        //子弹在脱离了屏幕后 需要被销毁
        if (x < -width) {
            return true
        }
        if (x > Config.gameWidth) {
            return true
        }
        if (y < -height) {
            return true
        }
        if (y > Config.gameHeight) {
            return true
        }
        return false
    }

    override fun isCollision(sufferable: Sufferable): Boolean {
        return checkCollision(sufferable)
    }

    override fun notifyAttack(sufferable: Sufferable) {
        println("子弹接受到我们的碰撞")
        isDestroy = true
        //子弹打到墙上后,需要被销毁掉
    }
}