package com.fdy.game

import com.fdy.game.model.*
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import org.itheima.kotlin.game.core.Window
import java.io.File

class GameWindow : Window(
    title = "坦克大战1.0",
    icon = "img/logo.jpg",
    width = Config.gameWidth,
    height = Config.gameHeight
) {
    //管理元素的集合
    private var views = arrayListOf<View>()

    override fun onCreate() {
        //地图
        //通过读文件的方式创建我们的地图
        val file = File(javaClass.getResource("/map/1.txt").path)
        //读文件
        val lines = file.readLines()
        //循环遍历
        var lineNum = 0
        lines.forEach { line ->
            //一行
            var columnNum = 0
            line.toCharArray().forEach { column ->
                when (column) {
                    '砖' -> views.add(Wall(columnNum * Config.block, lineNum * Config.block))
                    '水' -> views.add(Water(columnNum * Config.block, lineNum * Config.block))
                    '铁' -> views.add(Steel(columnNum * Config.block, lineNum * Config.block))
                    '草' -> views.add(Grass(columnNum * Config.block, lineNum * Config.block))
                }
                columnNum++
            }
            lineNum++
        }

        //添加我方坦克
        val tank = Tank(Config.block * 10, Config.block * 12)
        views.add(tank)
    }

    override fun onDisplay() {
        //绘制元素
        views.forEach {
            it.draw()
        }
    }

    override fun onKeyPressed(event: KeyEvent) {
        when(event.code){
//            KeyCode.L->
        }
    }

    override fun onRefresh() {
    }

}