package ru.kpfu.consumer.repository

import org.springframework.stereotype.Repository
import ru.kpfu.consumer.model.Nomenclature
import ru.kpfu.consumer.repository.jpa.JpaNomenclatureRepository
import ru.kpfu.consumer.rest.MyDateFormat
import ru.kpfu.consumer.rest.NomenclatureConnector
import java.util.*

const val UPDATE_TIMEOUT = 10_000

@Repository
class NomenclatureRepository(
        val nomenclatureConnector: NomenclatureConnector,
        val jpaNomenclatureRepository: JpaNomenclatureRepository
) : MyRepository<Nomenclature> {

    var lastUpdateFromCentral = Date(0)

    override fun save(entity: Nomenclature): Nomenclature {
        return nomenclatureConnector.addNomenclature(entity)
    }

    override fun delete(entity: Nomenclature) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findAll(): List<Nomenclature> {
        if(lastUpdateFromCentral.time - System.currentTimeMillis() > UPDATE_TIMEOUT){
            forceUpdate()
        }
        return jpaNomenclatureRepository.findAll();
    }

    override fun forceUpdate() {
        nomenclatureConnector.getNomenclatures(lastUpdateFromCentral).forEach {
            jpaNomenclatureRepository.save(it)
        }
        lastUpdateFromCentral = Date()
    }


}