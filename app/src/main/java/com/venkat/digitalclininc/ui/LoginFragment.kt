package com.venkat.digitalclininc.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.venkat.digitalclininc.R
import com.venkat.digitalclininc.databinding.LoginNewFragmentBinding
import com.venkat.digitalclininc.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var bindingContext: LoginNewFragmentBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bindingContext = LoginNewFragmentBinding.inflate(inflater, container, false)
        return bindingContext.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showProgressBar.observe(viewLifecycleOwner) {
            if (it) bindingContext.progressView.progressbarText.visibility = View.VISIBLE
            else bindingContext.progressView.progressbarText.visibility = View.GONE
        }

        // TODO: Yet to handle validation checks for login
        bindingContext.loginButton.setOnClickListener {
            publishLogin()
        }
    }

    private fun publishLogin() {
        // TODO: expose the list of login Rx Observable and LiveData
        //  separately in the ViewModel and implement their bindings also separately as an exercise
        viewModel.login().observe(viewLifecycleOwner) { response ->
            response.data?.let { token ->
                this.activity?.let { fragActivity ->
                    viewModel.onUserLoggedIn(
                        token,
                        fragActivity.applicationContext
                    )
                }
            }
            lifecycleScope.launch {
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