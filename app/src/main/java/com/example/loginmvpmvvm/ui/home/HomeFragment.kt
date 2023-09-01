package com.example.loginmvpmvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.loginmvpmvvm.R
import com.example.loginmvpmvvm.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel by viewModel<HomeViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.intent?.getStringExtra(ID)?.let {
            viewModel.getUser(it)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("id")?.let {
            viewModel.getUser(it)
        }
        viewModel.userData.observe(viewLifecycleOwner){
            it?.let { user ->
                binding.creatorId.text = getString(R.string.txt_identificator, user.creatorId)
                binding.createdTime.text = getString(R.string.txt_created_time, user.createdTime)
                binding.modefiedTime.text = getString(R.string.txt_modified_time, user.modifiedTime)
                binding.birthdate.text = getString(R.string.txt_birthdate, user.birthdate)
                binding.phone.text = getString(R.string.txt_phone, user.phone)
                binding.name.text = getString(R.string.txt_name, user.name)
                binding.type.text = getString(R.string.txt_type, user.name)
                binding.rowId.text = getString(R.string.txt_row_identificator, user.rowId)
                binding.email.text = getString(R.string.txt_email, user.email)
                binding.status.text = getString(R.string.txt_status, user.status)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        private const val ID = "id"
        fun newInstance(
            id: String = ""
        ):HomeFragment = HomeFragment().apply {
            arguments = bundleOf(ID to id)
        }
    }
}