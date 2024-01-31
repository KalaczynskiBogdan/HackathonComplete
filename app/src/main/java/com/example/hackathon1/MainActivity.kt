package com.example.hackathon1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.hackathon1.login.LoginFragment
import com.example.hackathon.R
import com.example.hackathon1.addEmployee.AddEmployeeFragment
import com.example.hackathon1.listOfEmployees.ListOfEmployeesFragment
import com.example.hackathon1.registration.RegistrationMvpFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToRegistrationFragment()
    }
    private fun navigateToRegistrationFragment() {
        val fragment = RegistrationMvpFragment()
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
    fun navigateToListOfEmployeesFragment() {
        val fragment = ListOfEmployeesFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
    fun navigateToAddFragment(){
        val fragment = AddEmployeeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
    fun navigateToInfoFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
    fun navigateToLoginFragment(){
        val fragment = LoginFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}