package com.lttrung.dormitory.ui.main.viewprofile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lttrung.dormitory.database.data.network.models.UserProfile
import com.lttrung.dormitory.databinding.FragmentViewProfileBinding
import com.lttrung.dormitory.ui.changepassword.ChangePasswordActivity
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewProfileFragment : Fragment() {
    private var binding: FragmentViewProfileBinding? = null
    private val viewProfileViewModel: ViewProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewProfileViewModel.getProfile()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentViewProfileBinding.inflate(
            inflater,
            container,
            false
        )
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        setupObserver()
    }

    private fun setupListener() {
        binding!!.buttonChangePassword.setOnClickListener {
            startActivity(Intent(requireContext(), ChangePasswordActivity::class.java))
        }
    }

    private fun setupObserver() {
        viewProfileViewModel.profileLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    val userProfile = resource.data
                    userProfile?.let { bindData(it) }
                }
                is Resource.Success -> {
                    val userProfile = resource.data
                    bindData(userProfile)
                }
                is Resource.Error -> {

                }
            }
        }
    }

    private fun bindData(userProfile: UserProfile) {
        binding?.let {
            it.studentId.text = userProfile.identityCardId
            it.fullName.text = userProfile.fullName
            it.gender.text = if (userProfile.isMale) {
                "Male"
            } else {
                "Female"
            }
            it.dob.text = userProfile.dob.toString()
            it.email.text = userProfile.email
            it.phoneNumber.text = userProfile.phoneNumber
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}