package com.example.hackathon1.listOfEmployees

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon1.Employee
import com.example.hackathon1.EmployeesAdapter
import com.example.hackathon1.infoOfEmployee.InfoOfEmployeesFragment
import com.example.hackathon1.MainActivity
import com.example.hackathon.databinding.FragmentListOfEmployeesBinding

class ListOfEmployeesFragment : Fragment(), ListOfEmployeesContract {
    private var employeesAdapter: EmployeesAdapter? = null

    private var presenter: ListOfEmployeesPresenter? = null

    private var _binding: FragmentListOfEmployeesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                (activity as MainActivity).navigateToLoginFragment()
            }
        }
        requireActivity().
            onBackPressedDispatcher.addCallback(callback)
        _binding = FragmentListOfEmployeesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = ListOfEmployeesPresenter()
        presenter?.attach(this)
        presenter?.requestEmployees()
        employeesAdapter = EmployeesAdapter()

        employeesAdapter?.setOnEmployeeClickListener(object :
            EmployeesAdapter.OnEmployeeClickListener {
            override fun onEmployeeClick(position: Int, employee: Employee) {
                val fragment = InfoOfEmployeesFragment.newInstance(
                    employee.id,
                    employee.name,
                    employee.workRole,
                    employee.trustLevel,
                    employee.photo
                )
                (activity as MainActivity).navigateToInfoFragment(fragment)
            }
        })
        binding.rvEmployees.adapter = employeesAdapter
        swipe()
        binding.buttonAddNote.setOnClickListener {
            (activity as MainActivity).navigateToAddFragment()
        }
    }

    override fun onResume() {
        super.onResume()
        showUpdatedEmployees()
    }

    override fun onDestroyView() {
        _binding = null
        presenter?.detach()
        presenter = null
        employeesAdapter = null
        super.onDestroyView()
    }

    private fun swipe() {
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val employee: Employee = employeesAdapter!!.getEmployees()[position]
                presenter?.remove(employee.id)
                showUpdatedEmployees()
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.rvEmployees)
    }

    private fun showUpdatedEmployees() {
        presenter?.updateListOfEmployees()
    }

    override fun showList(data: ArrayList<Employee>) {
        employeesAdapter?.setListOfEmployee(data)
    }
}