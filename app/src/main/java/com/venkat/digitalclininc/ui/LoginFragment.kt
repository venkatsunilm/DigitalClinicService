package com.venkat.digitalclininc.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.venkat.digitalclininc.R
import com.venkat.digitalclininc.databinding.LoginNewFragmentBinding
import com.venkat.digitalclininc.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var bindingContext: LoginNewFragmentBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bindingContext = LoginNewFragmentBinding.inflate(inflater, container, false)

        loginViewModel.spinner.observe(viewLifecycleOwner) {
            if (it) bindingContext.spinner.visibility = View.VISIBLE
            else bindingContext.spinner.visibility = View.GONE
        }

        return bindingContext.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingContext.lifecycleOwner = viewLifecycleOwner

        // TODO: Login screen partially implemented with mock data
        bindingContext.loginButton.setOnClickListener {
            loginViewModel.login().observe(viewLifecycleOwner) {
                navigateToHome()
            }
        }
    }

    private fun navigateToHome() {
        // few action animations while moving to other destinations
        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        findNavController().navigate(R.id.home_dest, null, options)
    }
}