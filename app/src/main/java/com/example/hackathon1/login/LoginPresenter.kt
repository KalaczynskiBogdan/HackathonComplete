package com.example.hackathon1.login

import com.example.hackathon1.DataBase

class LoginPresenter {
    private val dataBase: DataBase = DataBase.getInstance()

    private var contract: LoginContract? = null

    fun attach(contract: LoginContract){
        this.contract = contract
    }
    fun detach(){
        contract = null
    }
    fun login(loginInput: String, passwordInput: String){
        val loginOfUser = dataBase.getLogin()
        val passwordOfUser = dataBase.getPassword()
        if (loginInput == loginOfUser && passwordInput == passwordOfUser){
            contract?.showLoginSucceed()
        }else{
            contract?.showLoginIsNotSucceed()
        }
    }

}