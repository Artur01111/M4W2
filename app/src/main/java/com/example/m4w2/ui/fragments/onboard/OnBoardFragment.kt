package com.example.m4w2.ui.fragments.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.m4w2.databinding.FragmentOnBoardBinding
import com.example.m4w2.utils.PreferenceHelper
import com.example.m4w2.R
import com.example.m4w2.ui.adapter.OnBoardViewPagerAdapter


class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()

    }

    private fun initialize() {
        binding.viewPager2.adapter = OnBoardViewPagerAdapter(this)
    }

    private fun setupListeners() = with(binding.viewPager2) {
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2){
                    binding.tvSend.visibility = View.VISIBLE
                    binding.btnStart.visibility = View.INVISIBLE
                }else{
                    binding.btnStart.visibility = View.INVISIBLE
                    binding.tvSend.visibility = View.VISIBLE
                }
            }
        })
        binding.tvSend.setOnClickListener {
            if (currentItem < 3){
                setCurrentItem(currentItem + 2, true)
            }
        }
        binding.btnStart.setOnClickListener {
            val sharedPreferences = PreferenceHelper()
            sharedPreferences.unit(requireContext())
            sharedPreferences.isOnBoardShown = true
            findNavController().navigate(R.id.action_onBoardFragment_to_noteFragment)
        }
    }
}

