package com.example.hackathon1.addEmployee

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hackathon1.Employee
import com.example.hackathon1.MainActivity
import com.example.hackathon.R
import com.example.hackathon.databinding.FragmentAddEmployeeBinding

class AddEmployeeFragment : Fragment(), AddContract {
    private var presenter: AddPresenter? = null
    private var _binding: FragmentAddEmployeeBinding? = null
    private val binding get() = _binding!!
    private var sizeOfEmployees: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddEmployeeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = AddPresenter()
        presenter?.attach(this)
        presenter?.getEmployee()
        binding.btnSave.setOnClickListener {
            saveEmployee()
        }
    }

    override fun onDestroyView() {
        _binding = null
        presenter?.detach()
        presenter = null
        super.onDestroyView()
    }

    private fun saveEmployee() {
        val id = sizeOfEmployees
        val name = binding.etInputName.text.toString().trim()
        val workRole = binding.etInputWorkRole.text.toString().trim()
        val trustLevel = getTrustLevel()
        val sex = binding.spinnerSex.selectedItem.toString()
        val photo: Int = if (sex == "man") {
            R.drawable.ic_man1
        } else {
            R.drawable.ic_woman1
        }
        val employee = Employee(id ,name, workRole, trustLevel, photo)
        presenter?.addEmployee(employee)
        (activity as MainActivity).navigateToListOfEmployeesFragment()
    }

    private fun getTrustLevel(): String {
        val trustLevel = if (binding.rbLow.isChecked) {
            "low"
        } else if (binding.rbMid.isChecked) {
            "mid"
        } else "high"
        return trustLevel
    }

    override fun showEmployeesSize(size: Int) {
        sizeOfEmployees = size + 1
    }
}