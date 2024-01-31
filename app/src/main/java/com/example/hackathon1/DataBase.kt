package com.example.hackathon1

import com.example.hackathon.R

class DataBase {
    private var login = ""
    private var password = ""
    private val listOfEmployees: ArrayList<Employee> = ArrayList()

    companion object {
        private var instance: DataBase? = null
        fun getInstance(): DataBase {
            if (instance == null) {
                instance = DataBase()
            }
            return instance as DataBase
        }
    }

    fun getLogin(): String{
        return login
    }
    fun getPassword(): String{
        return password
    }

    fun saveAdmin(login: String, password: String) {
        this.login = login
        this.password = password
    }

    fun makeListOfEmployees(): ArrayList<Employee> {
        listOfEmployees.add(Employee(1, "Dima", "Director", "high", R.drawable.ic_man1))
        listOfEmployees.add(Employee(2, "Roy", "Manager", "high", R.drawable.ic_man1))
        listOfEmployees.add(Employee(3, "Lena", "Admin", "mid", R.drawable.ic_woman1))
        listOfEmployees.add(Employee(4, "Maria", "Chef", "mid", R.drawable.ic_woman1))
        listOfEmployees.add(Employee(5, "Artem", "Chef", "low", R.drawable.ic_man1))
        listOfEmployees.add(Employee(6, "Olga", "Cashier", "low", R.drawable.ic_woman1))
        listOfEmployees.add(Employee(7, "Vita", "Cleaner", "low", R.drawable.ic_woman1))
        listOfEmployees.add(Employee(8, "Max", "Security", "mid", R.drawable.ic_man1))
        return listOfEmployees
    }

    fun getEmployees():ArrayList<Employee> {
        return ArrayList(listOfEmployees)
    }

    fun add(employee: Employee) {
        listOfEmployees.add(employee)
    }

    fun remove(id: Int) {
        val iterator = listOfEmployees.iterator()
        while (iterator.hasNext()) {
            val employee = iterator.next()
            if (employee.id == id) {
                iterator.remove()
            }
        }
    }

    fun changeTrustLevel(id: Int, trustLevel: String){
        listOfEmployees.firstOrNull{ id == it.id }?.let {
            it.trustLevel = trustLevel
        }
    }
}