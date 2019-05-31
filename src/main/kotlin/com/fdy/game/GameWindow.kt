package com.fdy.game

import com.fdy.game.business.*
import com.fdy.game.enum.Direction
import com.fdy.game.model.*
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import org.itheima.kotlin.game.core.Window
import java.io.File
import java.util.concurrent.CopyOnWriteArrayList

class GameWindow : Window(
    title = "坦克大战1.0",
    icon = "img/logo.jpg",
    width = Config.gameWidth,
    height = Config.gameHeight
) {
    //管理元素的集合
//    private var views = arrayListOf<View>()
    //线程安全的集合
    private var views = CopyOnWriteArrayList<View>()

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
        //打印界面中的元素
        println("${views.size}")
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
            KeyCode.ENTER -> {
                //发射子弹
                val short = tank.short()
                views.add(short)
            }
        }
    }

    override fun onRefresh() {
        //业务逻辑

        //判断运动的物体和阻塞的物体是否发生碰撞
        //1.找到运动的物体
//        val moves = views.filter { it is Movable }
//        //2.找到阻塞的物体
//        val blocks = views.filter { it is Blockable }
//        //3 遍历集合 找到是否发生碰撞
        //1.找到运动的物体

        views.filter { it is Movable }.forEach moveTag@{ move ->
            //        //2.找到阻塞的物体
            move as Movable
            var badDirection: Direction? = null
            var badBlockable: Blockable? = null
            views.filter { it is Blockable }.forEach blockTag@{ block ->
                //        //3 遍历集合 找到是否发生碰撞
                //move和block是否碰撞
                block as Blockable
                //获得碰撞的方向
                val direction = move.willCollsion(block)
                //碰撞的方向不为空执行let表达式
                direction?.let {
                    //移动的发生碰撞,跳出当前循环
                    badDirection = direction
                    badBlockable = block
                    return@blockTag
                }
            }

            //找到和move碰撞的block
            //找到会碰撞的方向
            //通知可以移动的物体会在哪个和哪个物体碰撞
            move.notifyCollsion(badDirection, badBlockable)
        }

        //检测自动移动能力的物体,让他们自己动起来
        views.filter {
            it is AutoMovable
        }.forEach {
            (it as AutoMovable).autoMove()
        }
        //检测是否销毁的物体
        views.filter {
            it is Destroyable
        }.forEach {
            if ((it as Destroyable).isDestroyable()) {
                views.remove(it)
            }
        }
        //检测具备攻击能力和被攻击能力的物体间是否发生碰撞
        //过滤出具备攻击能力的物体
        views.filter { it is Attackable }.forEach attackTag@ {attack->
            attack as Attackable
            //过滤出具备受攻击能力的物体
            views.filter { it is Sufferable }.forEach sufferTag@ {suffer->
                //判断是否发生碰撞
                suffer as Sufferable
                if (attack.isCollision(suffer)){
                    //产生碰撞,找到碰撞者
                    //通知我们对应的攻击者产生我们的碰撞
                    attack.notifyAttack(suffer)
                    //通知我们的被攻击者产生碰撞
                    suffer.notifySuffer(attack)
                    return@sufferTag
                }
            }
        }

    }

}

/**
 * 发射子弹
 */
fun Tank.short() {

}
