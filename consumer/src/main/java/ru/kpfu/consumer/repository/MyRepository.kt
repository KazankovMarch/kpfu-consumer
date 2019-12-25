package ru.kpfu.consumer.repository

interface MyRepository<E> {

    fun save(entity: E): E

    fun delete(entity: E)

    fun findAll(): List<E>

    fun forceUpdate()
}