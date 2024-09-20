package com.example.m4w2.ui.fragments.onboard

import android.annotation.SuppressLint
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.m4w2.R
import com.example.m4w2.R.color.orange
import com.example.m4w2.databinding.FragmentOnBoardBinding
import com.example.m4w2.databinding.FragmentOnBoardPagingBinding

class OnBoardPagingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPagingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() = with(binding) {
        when(requireArguments().getInt(ARG_ONBOARD_POSITION)){
            0 -> {
                Glide.with(imgTitle).load(R.drawable.giphy1).into(imgTitle)
                txtTitle.text = "Удобство"
                desc.text = "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно."
                view1.setBackgroundResource(R.drawable.a)
                view2.setBackgroundResource(R.drawable.b)
                view3.setBackgroundResource(R.drawable.b)
            }
            1 -> {
                Glide.with(imgTitle).load(R.drawable.giphy1).into(imgTitle)
                view1.setBackgroundResource(R.drawable.b)
                view2.setBackgroundResource(R.drawable.a)
                view3.setBackgroundResource(R.drawable.b)
                txtTitle.text = "Организация"
                desc.text = "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время."
            }
            2 -> {
                Glide.with(imgTitle).load(R.drawable.giphy1).into(imgTitle)
                view1.setBackgroundResource(R.drawable.b)
                view2.setBackgroundResource(R.drawable.b)
                view3.setBackgroundResource(R.drawable.a)
                txtTitle.text = "Синхронизация"
                desc.text = "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте."
            }
        }
    }

    companion object{
        const val ARG_ONBOARD_POSITION = "onBoard"
    }
}