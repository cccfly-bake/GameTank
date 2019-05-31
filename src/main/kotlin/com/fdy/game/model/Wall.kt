package com.fdy.game.model

import com.fdy.game.Config
import com.fdy.game.business.Attackable
import com.fdy.game.business.Blockable
import com.fdy.game.business.Destroyable
import com.fdy.game.business.Sufferable
import org.itheima.kotlin.game.core.Painter

/**
 * 砖墙 具备阻塞能力
 */
class Wall(override val x: Int, override val y: Int) : Blockable, Sufferable, Destroyable {
    override var blood: Int = 3
    override val width: Int = Config.block
    override val height: Int = Config.block
//    override val x: Int = 100
//    override val y: Int = 100

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

    override fun notifySuffer(attackable: Attackable) {
        println("砖墙接受到挨打了")
        //砖墙需要掉血
        blood -= attackable.attackPower
    }

    override fun isDestroyable(): Boolean = blood <= 0

}