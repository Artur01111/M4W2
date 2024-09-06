package com.example.m4w2.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.m4w2.R
import com.example.m4w2.databinding.FragmentOnBoardBinding
import com.example.m4w2.ui.adapter.OnBoardPagerAdapter

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
    }

    private fun setupListeners() = with(binding.viewPager2){
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2){
                    binding.txtSkip.visibility = View.INVISIBLE
                    binding.btnStart.visibility = View.VISIBLE
                } else{
                    binding.txtSkip.visibility = View.VISIBLE
                    binding.btnStart.visibility = View.INVISIBLE
                }
            }
        })
        binding.txtSkip.setOnClickListener{
            if (currentItem < 3){
                setCurrentItem(currentItem + 2, true)
            }
        }
    }

    private fun initialize() {
        binding.viewPager2.adapter = OnBoardPagerAdapter(this)
    }
}