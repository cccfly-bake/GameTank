package com.fdy.game.model

import com.fdy.game.Config
import org.itheima.kotlin.game.core.Painter

/**
 * 铁墙
 */
class Steel(override val x: Int, override val y: Int) :View {
    //位置
//    override var x: Int = 200
//    override var y: Int = 200
    //宽高
    override var width: Int = Config.block
    override var height: Int = Config.block
    //显示  行为
    override fun draw() {
        Painter.drawImage("img/steel.gif", x, y)
    }
}