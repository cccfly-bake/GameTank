package com.fdy.game

import javafx.scene.input.KeyEvent
import org.itheima.kotlin.game.core.Window

class GameWindow : Window(
    title = "坦克大战1.0",
    icon = "img/logo.jpg",
    width = Config.gameWidth,
    height = Config.gameHeight
) {

    override fun onCreate() {
        //地图
    }

    override fun onDisplay() {

    }

    override fun onKeyPressed(event: KeyEvent) {
    }

    override fun onRefresh() {
    }

}