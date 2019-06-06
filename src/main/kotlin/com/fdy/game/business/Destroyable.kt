package com.fdy.game.business

import com.fdy.game.model.View

/**
 * 销毁的能力
 */
interface Destroyable : View {
    /**
     * 是否是否销毁了
     */
    fun isDestroyable(): Boolean

    /**
     * 死亡效果
     */
    fun showDestroy():Array<View>?
}