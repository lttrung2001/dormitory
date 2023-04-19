package com.lttrung.dormitory.ui.main.viewprofile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lttrung.dormitory.domain.data.network.models.StudentProfile
import com.lttrung.dormitory.databinding.FragmentViewProfileBinding
import com.lttrung.dormitory.ui.changepassword.ChangePasswordActivity
import com.lttrung.dormitory.ui.login.LoginActivity
import com.lttrung.dormitory.utils.ExtensionFunctionHelper.format
import com.lttrung.dormitory.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewProfileFragment : Fragment() {
    private val binding: FragmentViewProfileBinding by lazy {
        FragmentViewProfileBinding.inflate(layoutInflater)
    }
    private val viewProfileViewModel: ViewProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewProfileViewModel.profileLiveData.value ?: let {
            viewProfileViewModel.getProfile()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        setupObserver()
    }

    private fun setupListener() {
        binding.buttonChangePassword.setOnClickListener {
            startActivity(Intent(requireContext(), ChangePasswordActivity::class.java))
        }
        binding.buttonLogout.setOnClickListener {
            viewProfileViewModel.logout()
            startActivity(Intent(requireContext(), LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
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

    private fun bindData(userProfile: StudentProfile) {
        binding.let {
            it.name.text = userProfile.fullName
            it.username.text = userProfile.username.uppercase()

            it.studentId.text = userProfile.identityCardId
            it.fullName.text = userProfile.fullName
            it.gender.text = if (userProfile.isMale) {
                "Male"
            } else {
                "Female"
            }
            it.dob.text = userProfile.dob.format()
            it.email.text = userProfile.email
            it.phoneNumber.text = userProfile.phoneNumber
        }
    }
}