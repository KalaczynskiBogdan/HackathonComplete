package com.example.hackathon1.listOfEmployees

import com.example.hackathon1.Employee


interface ListOfEmployeesContract {
    fun showList(data: ArrayList<Employee>)
}