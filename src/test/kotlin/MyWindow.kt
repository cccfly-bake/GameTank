import javafx.application.Application
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.paint.Color
import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter
import org.itheima.kotlin.game.core.Window

/**
 * 窗体
 * 继承游戏引擎总的窗体
 */
class MyWindow:Window(){
    /**
     *创建窗体
     */
    override fun onCreate() {
       println("窗体创建")
    }

    /**
     * 窗体渲染之后的回调,不停的渲染,执行
     */
    override fun onDisplay() {
        println("onDisplay")
        //绘制图片
        Painter.drawImage("enemy2D.gif",0,0)
        //绘制颜色
        Painter.drawColor(Color.WHITE,20,20,100,100)
        //绘制文字
        Painter.drawText("你好",60,60)
    }

    override fun onKeyPressed(event: KeyEvent) {
        println("onKeyPressed,按键反应")
        when(event.code){
            KeyCode.ENTER->        println("点击了ENTER")
            KeyCode.L ->Composer.play("blast.wav")
        }
    }

    override fun onRefresh() {
        //耗時操作
    }


}

fun main() {
    //啓動游戲
    Application.launch(MyWindow::class.java)
}