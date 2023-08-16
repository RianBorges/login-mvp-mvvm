package com.example.loginmvpmvvm.ui.access.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.loginmvpmvvm.common.extensions.setMask
import com.example.loginmvpmvvm.databinding.FragmentSignupBinding
import com.example.loginmvpmvvm.ui.MainActivity
import com.example.loginmvvm.common.date.DateUtil.toDateAmerica
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {

    private val viewModel by viewModel<SignUpViewModel>()
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private var type = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getTypeFromUser()
        handleMasks()
        handleDisableError()
        handleSignUpSuccess()
        handleSignUpError()
        handleSignUpFail()
        handleSignUpExists()

        binding.btnCadastrar.setOnClickListener {
            binding.progressBar.setVisibility(View.VISIBLE)
            handleSignUp()
        }
    }

    private fun handleSignUp() {
        val name = binding.etName.text.toString()
        val birthdate = binding.etBirthdate.text.toString()
        val email = binding.etMail.text.toString()
        val phone = binding.etPhone.text.toString()
        val password = binding.etPass.text.toString()

        viewModel.signUp(
            name = name, birthdate = birthdate.toDateAmerica(), email = email, phone = phone,
            type = type, status = "ACTIVE", password = password
        )
    }

    private fun handleSignUpError() {
        viewModel.signUpError.observe(viewLifecycleOwner) {
            if (it) Toast.makeText(requireContext(), "Houve Um Erro", Toast.LENGTH_SHORT).show()
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun handleSignUpExists() {
        viewModel.signUpExists.observe(viewLifecycleOwner) {
            if (it) Toast.makeText(requireContext(), "Email Já Registrado", Toast.LENGTH_SHORT)
                .show()
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun handleSignUpFail() {
        viewModel.signUpFail.observe(viewLifecycleOwner) {
            if (it) Toast.makeText(requireContext(), "Houve Uma Falha", Toast.LENGTH_SHORT).show()
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun handleSignUpSuccess() {
        viewModel.signUpSuccess.observe(viewLifecycleOwner) {
            it?.let {
                binding.progressBar.visibility = View.GONE
                (activity as MainActivity).openSignIn(
                    binding.etMail.text.toString(),
                    binding.etPass.text.toString()
                )
            }
        }
    }

    private fun handleDisableError() {
        viewModel.errorMsg.observe(viewLifecycleOwner) {
            binding.etMail.doAfterTextChanged { binding.tilMail.isErrorEnabled = false }
            binding.etPass.doAfterTextChanged { binding.tilPass.isErrorEnabled = false }
            binding.etConfirmPass.doAfterTextChanged { binding.tilPass.isErrorEnabled = false }
        }
    }

    private fun handleMasks() = with(binding){
        etPhone.setMask("(##) #####-####")
        etBirthdate.setMask("##/##/####")
    }

    private fun getTypeFromUser() {
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radio = group.findViewById(checkedId) as RadioButton
            if (radio.isChecked) { type = radio.text.toString() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}