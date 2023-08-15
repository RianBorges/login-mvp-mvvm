package com.example.loginmvpmvvm.ui.access.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.loginmvpmvvm.databinding.FragmentLoginBinding
import com.example.loginmvpmvvm.ui.MainActivity
import com.example.loginmvpmvvm.ui.home.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel by viewModel<LoginViewModel>()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.errorMsg.observe(viewLifecycleOwner){
            binding.etEmail.doAfterTextChanged { binding.tilEmail.isErrorEnabled = false }
            binding.etPassword.doAfterTextChanged { binding.tilPassword.isErrorEnabled = false }
        }

        viewModel.signInSuccess.observe(viewLifecycleOwner){
            it?.idUser?.let {
                val intent = Intent(requireContext(), HomeActivity::class.java).apply {
                    putExtra("id", it)
                }
                startActivity(intent)
            }?: kotlin.run {
                Toast.makeText(requireContext(), "Email e Senha Inválido", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnEnter.setOnClickListener{
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            viewModel.signIn(email = email, password = password)
        }
    }

    override fun onResume() {
        super.onResume()
        fillFields()
    }

    fun fillFields(){
        binding.etEmail.setText((activity as MainActivity).mail)
        binding.etPassword.setText((activity as MainActivity).pass)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
