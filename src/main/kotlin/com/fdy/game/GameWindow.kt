package com.fdy.game

import com.fdy.game.enum.Direction
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
    //晚点创建 我方坦克
    private lateinit var tank: Tank

    override fun onCreate() {
        //地图
        //通过读文件的方式创建我们的地图
        val file = File(javaClass.getResource("/map/1.txt").path)
        //读文件
        val lines = file.readLines()
        //循环遍历行
        var lineNum = 0
        lines.forEach { line ->
            //一行
            var columnNum = 0
            //循环遍历列
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
        tank = Tank(Config.block * 10, Config.block * 12)
        views.add(tank)
    }

    override fun onDisplay() {
        //绘制元素
        views.forEach {
            it.draw()
        }
    }

    override fun onKeyPressed(event: KeyEvent) {
        //根据案件wsad移动坦克   更改xy去移动坦克
        when (event.code) {
            KeyCode.W -> {
                tank.move(Direction.UP)
            }
            KeyCode.S -> {
                tank.move(Direction.DOWN)
            }
            KeyCode.A -> {
                tank.move(Direction.LEFT)
            }
            KeyCode.D -> {
                tank.move(Direction.RIGHT)
            }
        }
    }

    override fun onRefresh() {
    }

}