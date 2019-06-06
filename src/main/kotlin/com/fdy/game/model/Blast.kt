package com.fdy.game.model

import com.fdy.game.Config
import com.fdy.game.business.Destroyable
import org.itheima.kotlin.game.core.Painter


/**
 * 爆炸物
 */
class Blast(override val x: Int, override val y: Int) : Destroyable {
    override fun showDestroy(): Array<View>? {
        return null
    }


    override val width: Int = Config.block
    override val height: Int = Config.block
    private var imagePaths = arrayListOf<String>()
    private var index: Int = 0

    init {
        (1..32).forEach {
            imagePaths.add("img/blast_$it.png")
        }
    }

    override fun draw() {
        val i = index % imagePaths.size
        Painter.drawImage(imagePaths[i], x, y)
        index++
    }

    override fun isDestroyable(): Boolean {
        return  index>=imagePaths.size
    }
}