package com.example.hackathon1.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hackathon1.MainActivity
import com.example.hackathon.databinding.FragmentRegistrationMvpBinding

class RegistrationMvpFragment: Fragment(), RegistrationContract {
    private var _binding: FragmentRegistrationMvpBinding? = null
    private val binding get() = _binding!!
    private var presenter: RegistrationPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationMvpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = RegistrationPresenter()
        presenter?.attach(this)
        binding.btnEnter.setOnClickListener {
            var login = ""
            var password = ""
            var secondPassword = ""
            login = binding.etInputLogin.text.toString()
            password = binding.etInputPassword.text.toString()
            secondPassword = binding.etInputPasswordAgain.text.toString()
            presenter?.registerUser(login, password, secondPassword)
        }
    }

    override fun onDestroyView() {
        _binding = null
        presenter?.detach()
        presenter = null
        super.onDestroyView()
    }

    override fun showRegisterSucceed() {
        Toast.makeText(
            requireContext(),
            "Registration successful",
            Toast.LENGTH_SHORT
        ).show()
        (activity as MainActivity).navigateToListOfEmployeesFragment()
    }

    override fun showLoginIsNotSucceed() {
        Toast.makeText(
            requireContext(),
            "Your login must be in small letters",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showPasswordIsNotSucceed() {
        Toast.makeText(
            requireContext(),
            "Your password must be more than 6 letters",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showPasswordsAreNotSucceed() {
        Toast.makeText(
            requireContext(),
            "Your passwords are not the same",
            Toast.LENGTH_SHORT
        ).show()
    }
}