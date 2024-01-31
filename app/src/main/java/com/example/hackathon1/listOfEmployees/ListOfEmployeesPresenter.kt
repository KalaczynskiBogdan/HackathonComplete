package com.example.hackathon1.listOfEmployees

import com.example.hackathon1.DataBase
import com.example.hackathon1.Employee

class ListOfEmployeesPresenter {
    private val dataBase: DataBase = DataBase.getInstance()

    private var contract: ListOfEmployeesContract? = null

    fun attach(contract: ListOfEmployeesContract){
        this.contract = contract
    }
    fun detach(){
        contract = null
    }

    fun requestEmployees() {
        val listOfEmployees: ArrayList<Employee> = dataBase.getEmployees()
        if (listOfEmployees.isEmpty()){
            val upListOfEmployees = dataBase.makeListOfEmployees()
            contract?.showList(upListOfEmployees)
        }else{
            contract?.showList(listOfEmployees)
        }
    }
    fun updateListOfEmployees(){
        val updatedList = dataBase.getEmployees()
        contract?.showList(updatedList)
    }
    fun remove(id: Int){
        dataBase.remove(id)
    }
}