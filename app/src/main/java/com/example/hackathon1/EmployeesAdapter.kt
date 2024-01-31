package com.example.hackathon1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon.R

class EmployeesAdapter : RecyclerView.Adapter<EmployeesAdapter.EmployeesViewHolder>() {
    private var listOfEmployee: ArrayList<Employee> = ArrayList()
    private lateinit var onEmployeeClickListener: OnEmployeeClickListener

    fun setOnEmployeeClickListener(onEmployeeClickListener: OnEmployeeClickListener) {
        this.onEmployeeClickListener = onEmployeeClickListener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListOfEmployee(listOfEmployee: ArrayList<Employee>) {
        this.listOfEmployee = listOfEmployee
        notifyDataSetChanged()
    }

    fun getEmployees(): ArrayList<Employee> {
        val newListOfEmployee = listOfEmployee.toMutableList()
        return ArrayList(newListOfEmployee)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.employee_item,
            parent,
            false
        )
        return EmployeesViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: EmployeesViewHolder, position: Int) {
        val employee = listOfEmployee[position]
        val text = "${employee.name}  -  ${employee.workRole}, trust level: ${employee.trustLevel}"
        viewHolder.textViewEmployees.text = text
        viewHolder.image.setImageResource(employee.photo)
        val color1: Int =
            ContextCompat.getColor(viewHolder.itemView.context, android.R.color.holo_green_light)
        val color2: Int =
            ContextCompat.getColor(viewHolder.itemView.context, android.R.color.holo_orange_light)
        val color3: Int =
            ContextCompat.getColor(viewHolder.itemView.context, android.R.color.holo_red_light)

        when (employee.trustLevel) {
            "high" -> ColorText.setColor(
                viewHolder.textViewEmployees,
                text,
                employee.trustLevel,
                color1
            )

            "mid" -> ColorText.setColor(
                viewHolder.textViewEmployees,
                text,
                employee.trustLevel,
                color2
            )

            else -> ColorText.setColor(
                viewHolder.textViewEmployees,
                text,
                employee.trustLevel,
                color3
            )
        }

        viewHolder.itemView.setOnClickListener {
            onEmployeeClickListener.onEmployeeClick(position, employee)
        }
    }

    override fun getItemCount(): Int {
        return listOfEmployee.size
    }

    class EmployeesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val textViewEmployees: TextView = itemView.findViewById(R.id.tvEmployee)
    }

    interface OnEmployeeClickListener {
        fun onEmployeeClick(position: Int, employee: Employee)
    }
}