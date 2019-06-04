package com.fdy.game.model

import com.fdy.game.Config
import com.fdy.game.business.Attackable
import com.fdy.game.business.Blockable
import com.fdy.game.business.Sufferable
import org.itheima.kotlin.game.core.Painter

/**
 * 大本营
 * 具备阻挡功能
 * 具备挨打功能
 */
class Camp(override var x: Int, override var y: Int) : Blockable, Sufferable {
    override var blood: Int = 12
    override var width: Int = Config.block * 2
    override var height: Int = Config.block + 32

    override fun draw() {
        //判断当前血量低于6时, 改为砖墙
        //血量低于3个时   墙没有了
        if (blood <= 3) {
            width = Config.block
            height = Config.block
            x = (Config.gameWidth - Config.block) / 2
            y = Config.gameHeight - Config.block
            Painter.drawImage("img/camp.gif", x + 32, y + 32)
        } else if (blood <= 6) {
            //绘制外围的砖块
            Painter.drawImage("img/wall_small.gif", x, y)
            Painter.drawImage("img/wall_small.gif", x + 32, y)
            Painter.drawImage("img/wall_small.gif", x + 64, y)
            Painter.drawImage("img/wall_small.gif", x + 96, y)

            Painter.drawImage("img/wall_small.gif", x, y + 32)
            Painter.drawImage("img/wall_small.gif", x, y + 64)

            Painter.drawImage("img/wall_small.gif", x + 96, y + 32)
            Painter.drawImage("img/wall_small.gif", x + 96, y + 64)

            Painter.drawImage("img/camp.gif", x + 32, y + 32)
        } else {
            //绘制外围的砖块
            Painter.drawImage("img/steel_small.gif", x, y)
            Painter.drawImage("img/steel_small.gif", x + 32, y)
            Painter.drawImage("img/steel_small.gif", x + 64, y)
            Painter.drawImage("img/steel_small.gif", x + 96, y)

            Painter.drawImage("img/steel_small.gif", x, y + 32)
            Painter.drawImage("img/steel_small.gif", x, y + 64)

            Painter.drawImage("img/steel_small.gif", x + 96, y + 32)
            Painter.drawImage("img/steel_small.gif", x + 96, y + 64)

            Painter.drawImage("img/camp.gif", x + 32, y + 32)
        }
    }

    override fun notifySuffer(attackable: Attackable): Array<View>? {
        blood -= attackable.attackPower
        if (blood == 3 || blood == 6) {
            return arrayOf(Blast(x, y))
        } else {
            return null
        }
    }
}