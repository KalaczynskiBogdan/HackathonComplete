package com.example.hackathon1.registration

import com.example.hackathon1.DataBase

class RegistrationPresenter {
    private val dataBase: DataBase = DataBase.getInstance()

    private var contract: RegistrationContract? = null

    fun attach(contract: RegistrationContract){
        this.contract = contract
    }
    fun detach(){
        contract = null
    }
    fun registerUser(login: String, password: String, secondPassword: String){
        if (login.lowercase() != login) {
            contract?.showLoginIsNotSucceed()
        } else if (password.length <= 6) {
            contract?.showPasswordIsNotSucceed()
        } else if (password != secondPassword) {
            contract?.showPasswordsAreNotSucceed()
        } else {
            contract?.showRegisterSucceed()
            dataBase.saveAdmin(login, password)
        }
    }
}