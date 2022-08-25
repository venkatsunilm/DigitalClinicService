package com.beehealthy.digitalclininc.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.beehealthy.digitalclininc.R
import com.beehealthy.digitalclininc.constants.ApplicationConstants
import com.beehealthy.digitalclininc.databinding.WelcomeConstrFragmentBinding
import com.beehealthy.digitalclininc.digitalcliniccanalytics.ProjectAnalytics
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WelcomeFragment : Fragment() {

    // Initializing lazily when needed but for sure
    private lateinit var bindingContext: WelcomeConstrFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // sending events to google analytics
        object {}.javaClass.enclosingMethod?.name?.let {
            ProjectAnalytics.getInstance(ApplicationConstants.applicationContext)
                .sendEvent(object {}.javaClass.enclosingClass.simpleName, it)
        }

//        SportsSample()
        // Inflate the fragment using the auto generated Binding class
        bindingContext = WelcomeConstrFragmentBinding.inflate(inflater, container, false)
        return bindingContext.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // sending events to google analytics
        object {}.javaClass.enclosingMethod?.name?.let {
            ProjectAnalytics.getInstance(ApplicationConstants.applicationContext)
                .sendEvent(object {}.javaClass.enclosingClass.simpleName, it)
        }

        // few action animations while moving to other destinations
        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        // on click to move to next destination
        bindingContext.secureSignInButton.setOnClickListener {

            // Sending  screen and button view name to analytics to listen on click
            ProjectAnalytics.getInstance(ApplicationConstants.applicationContext)
                .sendEvent(
                    object {}.javaClass.enclosingClass.simpleName,
                    bindingContext.secureSignInButton.text.toString()
                )

            findNavController().navigate(R.id.home_dest, null, options)
            // TODO: The alternative way to call the destination using action testing, under implementation
            // Navigation.createNavigateOnClickListener(R.id.action_welcome_dest_to_home_dest, null)
        }

    }
}