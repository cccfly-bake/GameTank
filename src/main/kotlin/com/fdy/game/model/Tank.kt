package com.fdy.game.model

import com.fdy.game.Config
import com.fdy.game.enum.Direction
import org.itheima.kotlin.game.core.Painter


class Tank(override var x: Int, override var y: Int) : View {
    override val width: Int = Config.block
    override val height: Int = Config.block
    //方向
    var currentDirection: Direction = Direction.UP

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
        Painter.drawImage(direction,x,y)
    }

    /**
     * 移动
     * @param direction 方向
     */
    fun move(direction: Direction){
        this.currentDirection=direction
        //坦克的坐标需要发生变化
        when(direction){
//            Direction.UP-> y-=
//            Direction.DOWN->
//            Direction.LEFT->
//            Direction.RIGHT->
        }
    }
}