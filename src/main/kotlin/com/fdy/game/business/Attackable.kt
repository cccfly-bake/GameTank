package com.fdy.game.business

import com.fdy.game.model.View

/**
 * 具备攻击的能力
 */
interface Attackable:View {
    //判断是否碰撞
    fun isCollision(sufferable: Sufferable):Boolean

    fun notifyAttack(sufferable: Sufferable)
}