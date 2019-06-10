package com.fdy.game.model

import com.fdy.game.Config
import com.fdy.game.business.*
import com.fdy.game.enum.Direction
import org.itheima.kotlin.game.core.Painter
import java.util.*

/**
 * 敌方坦克
 *
 * 敌方坦克可以移动
 * 可以自动移动
 * 阻塞移动
 * 自动射击
 * 被打
 */
class Enemy(override var x: Int, override var y: Int) : Movable, AutoMovable,
    Blockable, AutoShot, Sufferable, Destroyable {


    override var blood: Int = 2
    override var currentDirection: Direction = Direction.DOWN
    override val speed: Int = 8
    override val width: Int = Config.block
    override val height: Int = Config.block
    //坦克不可以走的方向
    private var badDirection: Direction? = null

    override fun draw() {
        val direction = when (currentDirection) {
            Direction.UP -> "img/enemy_1_u.gif"
            Direction.DOWN -> "img/enemy_1_d.gif"
            Direction.LEFT -> "img/enemy_1_l.gif"
            Direction.RIGHT -> "img/enemy_1_r.gif"
        }
        Painter.drawImage(direction, x, y)
    }

//    override fun willCollsion(blockable: Blockable): Direction? {
//        return null
//    }

    override fun notifyCollsion(direction: Direction?, blockable: Blockable?) {
        //todo 接收到碰撞的通知
        this.badDirection = direction
    }

    override fun autoMove() {
        if (currentDirection == badDirection) {
            //要往错误方向走,不允许
            //改变自己的方向
            currentDirection = rdmDirection(badDirection)
            return
        }
        //坦克的坐标需要发生变化
        when (currentDirection) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }
        //越界判断
        if (x < 0) x = 0
        if (x > Config.gameWidth - width) x = Config.gameWidth - width
        if (y < 0) y = 0
        if (y > Config.gameHeight - height) y = Config.gameHeight - height
    }

    private fun rdmDirection(bad: Direction?): Direction {
        val i = Random().nextInt(4)
        val direction = when (i) {
            0 -> Direction.UP
            1 -> Direction.DOWN
            2 -> Direction.LEFT
            3 -> Direction.RIGHT
            else -> Direction.RIGHT
        }
        if (direction == bad) {
            return rdmDirection(bad)
        }
        return direction
    }

    override fun autoShot(): View? {
        return Bullet(this, currentDirection) { bulletWidth, bulletHeight ->
            val tankX = x
            val tankY = y
            val tankWidth = width
            val tankHeight = height

            //计算子弹真实的坐标
            //bulletx=tankx+(tankwidth-bulletwidth)/2
            //bulltey=tanky-bulletheight/2
            var bulletX = 0
            var bulletY = 0
            var bulletwidth = 0//不写死,由子弹自身决定
            var bulletHeight = 0//不写死,由子弹自身决定
            when (currentDirection) {
                Direction.UP -> {
                    bulletX = tankX + (tankWidth - bulletwidth) / 2
                    bulletY = tankY + bulletHeight / 2
                }
                Direction.DOWN -> {
                    bulletX = tankX + (tankWidth - bulletwidth) / 2
                    bulletY = tankY + tankHeight - bulletHeight / 2
                }
                Direction.LEFT -> {
                    bulletX = tankX + bulletwidth / 2
                    bulletY = tankY + (tankHeight - bulletHeight) / 2
                }
                Direction.RIGHT -> {
                    bulletX = tankX + tankWidth - bulletwidth / 2
                    bulletY = tankY + (tankHeight - bulletHeight) / 2
                }

            }
            Pair(bulletX, bulletY)
        }
    }

    override fun notifySuffer(attackable: Attackable): Array<View>? {
        if (attackable.owner is Enemy){
            //挨打,不掉血,不给反应
            return null
        }
        blood -= attackable.attackPower
        return arrayOf(Blast(x, y))
    }

    override fun isDestroyable(): Boolean = blood <= 0

    override fun showDestroy(): Array<View>? {
        return null
    }
}