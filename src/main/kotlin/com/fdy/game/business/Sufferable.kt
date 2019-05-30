package com.fdy.game.business

import com.fdy.game.model.View

/**
 * 遭受攻击的接口
 */
interface Sufferable:View {
    fun notifySuffer(attackable: Attackable)
}