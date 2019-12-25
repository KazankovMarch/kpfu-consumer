package ru.kpfu.consumer.control

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.kpfu.consumer.control.edit.AbstractEditFrameController
import ru.kpfu.consumer.model.*
import ru.kpfu.consumer.model.Nomenclature
import ru.kpfu.consumer.repository.MyRepository
import java.io.IOException

private const val ABSTRACT_TAB_FRAME = "view.fxml/AbstractTab.fxml"

@Configuration
class ControllerConfiguration {


    @Bean("nomenclatureTab")
    fun getNomenclatureTab(): View = loadView(ABSTRACT_TAB_FRAME)
    @Bean
    fun getNomenclatureTabController(
            @Autowired jpaRepository: MyRepository<Nomenclature>,
            @Autowired abstractEditFrameController: AbstractEditFrameController<Nomenclature>
    ): AbstractEntityTabController<Nomenclature> {
        val controller = getNomenclatureTab().controller as AbstractEntityTabController<Nomenclature>
        return controller.apply {
            this.entityType = Nomenclature::class.java
            this.name = "Nomenclature"
            this.repository = jpaRepository
            this.abstractEditFrameController = abstractEditFrameController
        }
    }

    @Bean("selectTab")
    fun getSelectTab(): View = loadView("view.fxml/SelectTab.fxml")
    @Bean
    fun getSelectTabController(): SelectTabController {
        val controller = getSelectTab().controller as SelectTabController
        return controller
    }



    @Bean("mainFrame")
    @Throws(IOException::class)
    fun getMainFrame(): View = loadView("view.fxml/MainFrame.fxml")

    /**
     * Именно благодаря этому методу мы добавили контроллер в контекст спринга,
     * и заставили его произвести все необходимые инъекции.
     */
    @Bean
    @Throws(IOException::class)
    fun getMainController()
            = getMainFrame().controller as MainFrameController

    /**
     * Самый обыкновенный способ использовать FXML загрузчик.
     * Как раз-таки на этом этапе будет создан объект-контроллер,
     * произведены все FXML инъекции и вызван метод инициализации контроллера.
     */
    @Throws(IOException::class)
    protected fun loadView(url: String): View {
        javaClass.classLoader.getResourceAsStream(url).use {
            val loader = FXMLLoader()
            loader.load<Any>(it)
            return View(loader.getRoot<Parent>(), loader.getController<Any>())
        }
    }

    /**
     * Класс - оболочка: контроллер мы обязаны указать в качестве бина,
     * а view - представление, нам предстоит использовать в точке входа.
     */
    data class View(val parent: Parent, val controller: Any)
}

