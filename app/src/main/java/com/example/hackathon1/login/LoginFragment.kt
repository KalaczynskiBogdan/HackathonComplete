package com.example.hackathon1.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hackathon.databinding.FragmentLoginBinding
import com.example.hackathon1.MainActivity

class LoginFragment : Fragment(), LoginContract{
    private var presenter: LoginPresenter? = null
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = LoginPresenter()
        presenter?.attach(this)
        binding.btnEnter.setOnClickListener {
            val login = binding.etInputLogin.text.toString()
            val password = binding.etInputPassword.text.toString()
            presenter?.login(login, password)
        }
    }

    override fun onDestroyView() {
        presenter?.detach()
        presenter = null
        _binding = null
        super.onDestroyView()
    }

    override fun showLoginSucceed() {
        Toast.makeText(
            requireContext(),
            "Login successful!",
            Toast.LENGTH_SHORT
        ).show()
        (activity as MainActivity).navigateToListOfEmployeesFragment()
    }

    override fun showLoginIsNotSucceed() {
        Toast.makeText(
            requireContext(),
            "Login is not successful!, try again",
            Toast.LENGTH_SHORT
        ).show()
    }
}