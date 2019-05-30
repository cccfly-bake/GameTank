package com.fdy.game.model

import com.fdy.game.Config
import com.fdy.game.business.Blockable
import com.fdy.game.business.Movable
import com.fdy.game.enum.Direction
import org.itheima.kotlin.game.core.Painter

/**
 * 坦克  具备移动能力
 */
class Tank(override var x: Int, override var y: Int) : Movable {


    override val width: Int = Config.block
    override val height: Int = Config.block
    //方向
    override var currentDirection: Direction = Direction.UP
    //速度
    override val speed: Int = 8
    //坦克不可以走的方向
    private var badDirection: Direction? = null


    override fun draw() {
        //根据坦克的方向进行绘制,,特有属性方向
        //方式一
//        when(currentDirection){
//            Direction.UP->Painter.drawImage("img/tank_u.gif",x,y)
//            Direction.DOWN->Painter.drawImage("img/tank_d.gif",x,y)
//            Direction.LEFT->Painter.drawImage("img/tank_l.gif",x,y)
//            Direction.RIGHT->Painter.drawImage("img/tank_r.gif",x,y)
//        }
        //根据坦克的方向进行绘制,,特有属性方向
        //方式二
        val direction = when (currentDirection) {
            Direction.UP -> "img/tank_u.gif"
            Direction.DOWN -> "img/tank_d.gif"
            Direction.LEFT -> "img/tank_l.gif"
            Direction.RIGHT -> "img/tank_r.gif"
        }
        Painter.drawImage(direction, x, y)
    }

    /**
     * 移动
     * @param direction 方向
     */
    fun move(direction: Direction) {
        //判断是否要往碰撞的方向走,发生碰撞,则不走了
        if (direction == badDirection) {
            return
        }
        if (this.currentDirection != direction) {
            this.currentDirection = direction
            return
        }
        //坦克的坐标需要发生变化
        when (direction) {
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

    override fun notifyCollsion(direction: Direction?, blockable: Blockable?) {
        //todo 接收到碰撞的通知
        this.badDirection = direction
    }

    override fun willCollsion(blockable: Blockable): Direction? {
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
        val collistion: Boolean = when {
            blockable.y + blockable.height <= y -> //阻挡物再运动物上方
                false
            y + height <= blockable.y -> //阻挡物在运动无下方
                false
            blockable.x + blockable.width <= x -> //阻挡物在运动物的左方
                false
            else -> x + width > blockable.x
        }
        return if (collistion) currentDirection else null
    }

    fun short(): Bullet {
        return Bullet(currentDirection) { bulletWidth, bulletHeight ->
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

}