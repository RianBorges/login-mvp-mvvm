package com.example.loginmvpmvvm.ui.access.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import com.example.loginmvpmvvm.databinding.FragmentLoginBinding
import com.example.loginmvpmvvm.ui.MainActivity
import com.example.loginmvpmvvm.ui.access.AccessFragment
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

        handleDisableError()
        handleLoginSuccess()
        handleLoginError()

        binding.btnEnter.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            handleSignIn()
        }
    }

    private fun handleSignIn() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        viewModel.signIn(email = email, password = password)
    }

    private fun handleLoginSuccess() {
        viewModel.signInSuccess.observe(viewLifecycleOwner) {
            it?.idUser?.let {
                val intent = Intent(requireContext(), HomeActivity::class.java).apply {
                    putExtra("id", it)
                }
                startActivity(intent)
            } ?: kotlin.run {
                Toast.makeText(requireContext(), "Email e Senha Inválido", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun handleDisableError() {
        viewModel.errorMsg.observe(viewLifecycleOwner) {
            binding.etEmail.doAfterTextChanged { binding.tilEmail.isErrorEnabled = false }
            binding.etPassword.doAfterTextChanged { binding.tilPassword.isErrorEnabled = false }
        }
    }

    private fun handleLoginError() {
        viewModel.signInError.observe(viewLifecycleOwner) {
            if (it) Toast.makeText(requireContext(), "Houve Um Erro", Toast.LENGTH_SHORT).show()
            binding.progressBar.visibility = View.GONE
        }
        viewModel.signInFail.observe(viewLifecycleOwner) {
            if (it) Toast.makeText(requireContext(), "Houve Uma Falha", Toast.LENGTH_SHORT).show()
            binding.progressBar.visibility = View.GONE
        }
        viewModel.notFoundError.observe(viewLifecycleOwner) {
            if (it) Toast.makeText(requireContext(), "Usuário Não Encontrado", Toast.LENGTH_SHORT)
                .show()
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        fillFields()
    }

    fun fillFields() {
        val email = (parentFragment as AccessFragment).mail
        val pass = (parentFragment as AccessFragment).pass
        binding.etEmail.setText(email)
        binding.etPassword.setText(pass)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}
