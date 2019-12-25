package ru.kpfu.consumer.control.edit

import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane
import org.springframework.stereotype.Controller
import ru.kpfu.consumer.model.Nomenclature

@Controller
class NomenclatureEditFrameController : AbstractEditFrameController<Nomenclature>() {

    private lateinit var audienceMaxCountField: TextField
    private lateinit var addressField: TextField
    private lateinit var idField: TextField

    private lateinit var nomenclature: Nomenclature

    override fun fieldsToEntity(): Nomenclature {
//        nomenclature.address = addressField.text
//        nomenclature.audienceMaxCount = Integer.parseInt(audienceMaxCountField.text)
        return nomenclature
    }

    override fun createInterfaceFields(gridPane: GridPane, nullableNomenclature: Nomenclature?) {
        this.nomenclature = nullableNomenclature ?: Nomenclature()
//        idField = TextField("${nomenclature.id}").apply { isEditable = false }
//        addressField = TextField(nomenclature.address)
//        audienceMaxCountField = TextField("${nomenclature.audienceMaxCount?:0}")
//        gridPane.addRow(1, Label("id"), idField)
//        gridPane.addRow(2, Label("address"), addressField)
//        gridPane.addRow(3, Label("audience max count"), audienceMaxCountField)
    }
}