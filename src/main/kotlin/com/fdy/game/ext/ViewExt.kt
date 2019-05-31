package com.fdy.game.ext

import com.fdy.game.model.View

/**
 * view的扩展函数
 */
fun View.checkCollision(view: View):Boolean{
    return checkCollision(x,y,width,height,view.x,view.y,view.width,view.height)
}