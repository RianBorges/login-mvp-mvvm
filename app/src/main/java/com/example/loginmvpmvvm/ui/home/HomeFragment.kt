package com.example.loginmvpmvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.loginmvpmvvm.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel by viewModel<HomeViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.intent?.getStringExtra("id")?.let {
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
                binding.creatorId.text = "Identificador: " + user.creatorId
                binding.createdTime.text = "Criado Em: " + user.createdTime
                binding.modefiedTime.text = "Modificado Em: " + user.modifiedTime
                binding.birthdate.text = "Data De Nascimento: " + user.birthdate
                binding.phone.text = "Telefone: " + user.phone
                binding.name.text = "Nome: " + user.name
                binding.type.text = "Função: " + user.type
                binding.rowId.text = "Identificador: " + user.rowId
                binding.email.text = "Email: " + user.email
                binding.status.text = "Status: " + user.status
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(
            id: String = ""
        ): HomeFragment = HomeFragment().apply {
            arguments = bundleOf("id" to id)
        }
    }
}
