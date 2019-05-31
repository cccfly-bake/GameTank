package com.fdy.game.model

/**
 * 显示的视图规范,
 */
interface View {
    //可以定义属性,让实现类去实现
    //宽高
    val width:Int
    val height:Int
    //位置
    val x:Int
    val y:Int
    //显示
    fun draw()

    fun checkCollision(x1:Int,y1:Int,w1:Int,h1:Int,
                       x2:Int,y2:Int,w2:Int,h2:Int):Boolean{
//        两个物体的x,y,w,h的比较

        ///todo 监测碰撞
        //阻挡物在运动物上放 不发生碰撞
       return when {
           y2 + h2 <= y1 -> //阻挡物再运动物上方
                false
            y1 + h1 <= y2 -> //阻挡物在运动无下方
                false
            x2+ w2 <= x1 -> //阻挡物在运动物的左方
                false
            else -> x1 + w1 > x2
        }
    }
}