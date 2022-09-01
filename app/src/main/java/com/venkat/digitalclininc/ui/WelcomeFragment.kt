package com.venkat.digitalclininc.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.venkat.digitalclininc.R
import com.venkat.digitalclininc.databinding.WelcomeConstrFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WelcomeFragment : Fragment() {

    private lateinit var bindingContext: WelcomeConstrFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bindingContext = WelcomeConstrFragmentBinding.inflate(inflater, container, false)
        return bindingContext.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        bindingContext.secureSignInButton.setOnClickListener {
            findNavController().navigate(R.id.login_dest, null, options)
            // TODO: The alternative way to call the destination using action testing, under implementation
            // Navigation.createNavigateOnClickListener(R.id.action_welcome_dest_to_home_dest, null)
        }
    }
}