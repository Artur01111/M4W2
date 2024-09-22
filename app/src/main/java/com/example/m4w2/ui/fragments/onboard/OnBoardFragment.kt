package com.example.m4w2.ui.fragments.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.m4w2.R
import com.example.m4w2.databinding.FragmentOnBoardBinding
import com.example.m4w2.ui.adapter.OnBoardViewPagerAdapter
import com.example.m4w2.utils.PreferenceHelper


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
        setupListener()
        setVisible()
        flag()
        binding.btnStart.setOnClickListener{
            PreferenceHelper.onShowed()
            findNavController().navigate(R.id.noteFragment)
        }
    }



    private fun initialize() {
        binding.viewPager.adapter = OnBoardViewPagerAdapter(this)
        PreferenceHelper.unit(requireContext())
    }

    private fun setupListener() = with(binding.viewPager) {
        binding.tvSend.setOnClickListener {
            if (currentItem < 3) {
                setCurrentItem(currentItem + 2, true)
            }
        }
        binding.btnStart.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardFragment_to_noteFragment)
        }
    }

    private fun setVisible() = with(binding) {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        tvSend.isVisible = true
                        btnStart.isVisible = false
                    }

                    1 -> {
                        tvSend.isVisible = true
                        btnStart.isVisible = false
                    }

                    2 -> {
                        tvSend.isVisible = false
                        btnStart.isVisible = true
                    }
                }
                super.onPageSelected(position)
            }
        })

    }
    private fun flag() {
        PreferenceHelper.isOnBoardShown = true
    }
}