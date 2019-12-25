package ru.kpfu.consumer.control

interface CrudController {
    
    fun onAddAction(): Unit?

    fun onDeleteAction(): Unit?

    fun onEditAction(): Unit?

    fun onFilterAction() 

    fun onLoadAction()
    
    fun onRefreshAction()
    
    fun onSaveAction() 

}