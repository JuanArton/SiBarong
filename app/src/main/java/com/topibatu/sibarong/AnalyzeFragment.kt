package com.topibatu.sibarong

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.topibatu.sibarong.databinding.FragmentAnalyzeBinding

class AnalyzeFragment : Fragment() {

    private lateinit var binding: FragmentAnalyzeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnalyzeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        BottomSheetBehavior.from(binding.botsheet).apply{
            peekHeight = 260
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }
}