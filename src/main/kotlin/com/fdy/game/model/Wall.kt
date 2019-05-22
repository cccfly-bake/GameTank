package com.fdy.game.model

import com.fdy.game.Config
import org.itheima.kotlin.game.core.Painter

/**
 * 砖墙
 */
class Wall(override val width: Int, override val height: Int) : View {
//    override val width: Int = Config.block
//    override val height: Int = Config.block
    override val x: Int = 100
    override val y: Int = 100

    override fun draw() {
        Painter.drawImage("img/wall.gif", x, y)
    }
//    //位置
//    var x: Int = 100
//    var y: Int = 100
//    //宽高
//    var width: Int = Config.block
//    var height: Int = Config.block
//    //显示  行为
//    fun draw() {
//        Painter.drawImage("img/wall.gif", x, y)
//    }
}