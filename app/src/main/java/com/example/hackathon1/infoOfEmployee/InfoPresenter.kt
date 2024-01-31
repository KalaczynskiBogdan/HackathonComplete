package com.example.hackathon1.infoOfEmployee

import com.example.hackathon1.DataBase

class InfoPresenter {
    private val dataBase: DataBase = DataBase.getInstance()

    fun changeTrustLevel(id: Int, trustLevel: String){
        dataBase.changeTrustLevel(id, trustLevel)
    }
    fun update(){
        dataBase.getEmployees()
    }

}