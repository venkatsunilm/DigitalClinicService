package com.beehealthy.digitalclininc.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.beehealthy.digitalclininc.R
import com.beehealthy.digitalclininc.databinding.LoginNewFragmentBinding
import com.beehealthy.digitalclininc.digitalcliniccanalytics.ProjectAnalytics
import com.beehealthy.digitalclininc.helper.ApiResponseHelper
import com.beehealthy.digitalclininc.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private var _binding: LoginNewFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        object {}.javaClass.enclosingMethod?.name?.let {
            this.activity?.let { it1 ->
                ProjectAnalytics.getInstance(it1.applicationContext)
                    .sendEvent(object {}.javaClass.enclosingClass.simpleName, it)
            }
        }

        _binding = LoginNewFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showProgressBar.observe(viewLifecycleOwner) {
            if (it) binding.progressView.progressbarText.visibility = View.VISIBLE
            else binding.progressView.progressbarText.visibility = View.GONE
        }

        // TODO: Yet to handle validation checks for login
        binding.loginButton.setOnClickListener {
            callLoginWhenAPiIsReady()
        }
    }

    private fun callLoginWhenAPiIsReady() {
        viewModel.login().observe(viewLifecycleOwner, Observer {
            // TODO: This if condition will be removed once the API is ready
            if (it.statusCode == 200) {
                context?.let { it1 ->
                    ApiResponseHelper.handleApiResponse(
                        it1.applicationContext,
                        it
                    ) { data: String ->
                        this.activity?.let { it2 ->
                            viewModel.onUserLoggedIn(data, it2.application)
                        }
                    }
                }
            } else {
                // Temporary condition to fetch data from login but it fails
                // and comes to this condition to navigate
                navigateToHome()
            }
        })
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