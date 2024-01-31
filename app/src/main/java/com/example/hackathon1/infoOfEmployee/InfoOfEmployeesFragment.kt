package com.example.hackathon1.infoOfEmployee

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.hackathon1.MainActivity
import com.example.hackathon.databinding.FragmentInfoOfEmployeesBinding

class InfoOfEmployeesFragment : Fragment() {
    private var name: String? = null
    private var workRole: String? = null
    private var trustLevel: String? = null
    private var photo: Int? = null
    private var id: Int? = null
    private var presenter: InfoPresenter? = null
    private var _binding: FragmentInfoOfEmployeesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoOfEmployeesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = InfoPresenter()
        showInfo()
        binding.btnBack.setOnClickListener {
            (activity as MainActivity).navigateToListOfEmployeesFragment()
        }
        binding.btnChangeTrustLevel.setOnClickListener {
            binding.tvTrustLevel.visibility = View.INVISIBLE
            binding.spinnerTrustLevel.visibility = View.VISIBLE
            binding.btnSaveNewTrustLevel.visibility = View.VISIBLE
            saveTrustLevel()
        }
    }

    override fun onDestroyView() {
        _binding = null
        presenter = null
        super.onDestroyView()
    }

    @SuppressLint("ResourceAsColor")
    private fun showInfo() {
        arguments?.let {
            id = it.getInt(EMPLOYEE_ID)
            name = it.getString(EMPLOYEE_NAME)
            workRole = it.getString(EMPLOYEE_WORK_ROLE)
            trustLevel = it.getString(EMPLOYEE_TRUST_LEVEL)
            photo = it.getInt(EMPLOYEE_PHOTO)
        }
        binding.tvName.text = name
        binding.tvWorkRole.text = workRole
        binding.tvTrustLevel.text = trustLevel
        setColor()
        photo?.let { binding.ivPhoto.setImageResource(it) }
    }

    private fun saveTrustLevel() {
        binding.btnSaveNewTrustLevel.setOnClickListener {
            val selectedTrustLevel = binding.spinnerTrustLevel.selectedItem.toString()
            binding.tvTrustLevel.text = selectedTrustLevel
            id?.let { it1 -> presenter?.changeTrustLevel(it1, selectedTrustLevel) }
            setColor()
            binding.tvTrustLevel.visibility = View.VISIBLE
            binding.spinnerTrustLevel.visibility = View.INVISIBLE
            binding.btnSaveNewTrustLevel.visibility = View.INVISIBLE
        }
    }

    private fun setColor() {
        when (binding.tvTrustLevel.text) {
            "high" -> binding.tvTrustLevel.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.holo_green_light
                )
            )

            "mid" -> binding.tvTrustLevel.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.holo_orange_light
                )
            )

            else -> binding.tvTrustLevel.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.holo_red_light
                )
            )
        }
    }

    companion object {
        private const val EMPLOYEE_ID = "id"
        private const val EMPLOYEE_NAME = "name"
        private const val EMPLOYEE_WORK_ROLE = "workRole"
        private const val EMPLOYEE_TRUST_LEVEL = "trustLevel"
        private const val EMPLOYEE_PHOTO = "photo"

        fun newInstance(id: Int, name: String, workRole: String, trustLevel: String, photo: Int) =
            InfoOfEmployeesFragment().apply {
                arguments = Bundle().apply {
                    putInt(EMPLOYEE_ID, id)
                    putString(EMPLOYEE_NAME, name)
                    putString(EMPLOYEE_WORK_ROLE, workRole)
                    putString(EMPLOYEE_TRUST_LEVEL, trustLevel)
                    putInt(EMPLOYEE_PHOTO, photo)
                }
            }
    }
}