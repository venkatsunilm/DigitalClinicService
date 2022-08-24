package com.beehealthy.digitalclininc.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.beehealthy.digitalclinic.apiservice.utils.SportsSample
import com.beehealthy.digitalclininc.R
import com.beehealthy.digitalclininc.databinding.WelcomeConstrFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WelcomeFragment : Fragment() {

    // Initializing lazily when needed but for sure
    private lateinit var bindingContext: WelcomeConstrFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        SportsSample()
        // Inflate the fragment using the auto generated Binding class
        bindingContext = WelcomeConstrFragmentBinding.inflate(inflater, container, false)
        return bindingContext.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            findNavController().navigate(R.id.home_dest, null, options)
            // TODO: The alternative way to call the destination using action testing, under implementation
            // Navigation.createNavigateOnClickListener(R.id.action_welcome_dest_to_home_dest, null)
        }

    }
}