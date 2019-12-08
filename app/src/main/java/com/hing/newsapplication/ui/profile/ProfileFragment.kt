package com.hing.newsapplication.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.hing.newsapplication.R
import com.hing.newsapplication.databinding.ProfileFragmentBinding
import com.hing.newsapplication.utils.setVisible
import com.hing.newsapplication.utils.showToast
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.profile_fragment.*
import javax.inject.Inject

class ProfileFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: ProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProfileViewModel::class.java)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        initData()
        setAction()
    }

    private fun initData() {
        with(viewModel) {
            username.observe(viewLifecycleOwner, Observer {
                tv_username.text = username.value

                ln_register.setVisible(username.value.isNullOrEmpty())
                ln_profile.setVisible(username.value?.isNotEmpty() == true)

                if(username.value?.isNotEmpty() == true){
                    getUserKeyword()
                }
            })

            keyword.observe(viewLifecycleOwner, Observer {
                keyword_spinner.setSelection(resources.getStringArray(R.array.news_list_keywords).indexOf(keyword.value))
            })

            isLoading.observe(viewLifecycleOwner, Observer {
            })

            errorMessage.observe(viewLifecycleOwner, Observer {
                main_contain.showToast(it)
            })

            getUsername()
        }
    }

    private fun setAction() {
        btn_register.setOnClickListener {
            viewModel.registerUsername(edt_username.text.toString())
        }

        btn_save.setOnClickListener {
            viewModel.updateKeyword(keyword_spinner.selectedItem as String)
        }
    }
}
