package com.fdy.game.business

import com.fdy.game.model.View

/**
 * 具备攻击的能力
 */
interface Attackable:View {
    /**
     * 攻击力
     */
    val attackPower:Int
    //判断是否碰撞
    fun isCollision(sufferable: Sufferable):Boolean

    fun notifyAttack(sufferable: Sufferable)
}