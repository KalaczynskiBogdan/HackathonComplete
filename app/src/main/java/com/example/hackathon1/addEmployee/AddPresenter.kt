package com.example.hackathon1.addEmployee

import com.example.hackathon1.DataBase
import com.example.hackathon1.Employee

class AddPresenter {
    private val dataBase: DataBase = DataBase.getInstance()

    private var contract: AddContract? = null

    fun attach(contract: AddContract){
        this.contract = contract
    }
    fun detach(){
        contract = null
    }

    fun addEmployee(employee: Employee){
        dataBase.add(employee)
    }
    fun getEmployee(){
        val employees = dataBase.getEmployees()
        val size = employees.size
        contract?.showEmployeesSize(size)
    }
}