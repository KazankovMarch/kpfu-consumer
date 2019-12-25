package ru.kpfu.consumer.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import ru.kpfu.consumer.model.Nomenclature

interface JpaNomenclatureRepository: JpaRepository<Nomenclature, Long> {
}