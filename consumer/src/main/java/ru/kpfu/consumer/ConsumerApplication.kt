package ru.kpfu.consumer

import javafx.scene.Scene
import javafx.stage.Stage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.SpringBootApplication
import ru.kpfu.consumer.control.ControllerConfiguration

@SpringBootApplication
class ConsumerApplication : AbstractFxApplicationSupport() {

    @Autowired
    @Qualifier("mainFrame")
    private lateinit var view: ControllerConfiguration.View

    override fun start(stage: Stage) {
        stage.scene = Scene(view.parent)
        stage.show()
    }
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            lauchApp(ConsumerApplication::class.java, args)
        }
    }
}


