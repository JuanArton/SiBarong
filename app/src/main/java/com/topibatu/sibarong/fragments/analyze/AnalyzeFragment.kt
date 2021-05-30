package com.topibatu.sibarong.fragments.analyze

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.topibatu.sibarong.R
import com.topibatu.sibarong.api.DataParcel
import com.topibatu.sibarong.database.entity.HistoryEntity
import com.topibatu.sibarong.databinding.FragmentAnalyzeBinding
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class AnalyzeFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentAnalyzeBinding
    private val analyzeViewModel: AnalyzeViewModel by viewModel()
    private var bottomSheetState by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnalyzeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        binding.analyzeLoadBar.visibility = View.INVISIBLE
        setBottomSheet(BottomSheetBehavior.STATE_COLLAPSED)

        binding.analyzebutton.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        if (v.id == R.id.analyzebutton){
            val newsText = binding.newsText.text.toString()
            binding.analyzeLoadBar.visibility = View.VISIBLE
            binding.analyzebutton.visibility = View.INVISIBLE
            analyzeViewModel.analyzeNews(newsText).observe(viewLifecycleOwner, { data ->
                binding.analyzeLoadBar.visibility = View.INVISIBLE
                binding.analyzebutton.visibility = View.VISIBLE

                binding.descriptionText.text = data.news

                setBottomSheet(BottomSheetBehavior.STATE_EXPANDED)
                val dataHistory = HistoryEntity(
                    data.news
                )
                analyzeViewModel.insertText(dataHistory)
            })
        }
    }

    private fun setBottomSheet(state: Int){
        BottomSheetBehavior.from(binding.botsheet).apply{
            peekHeight = 300
            bottomSheetState = this.state
            this.state = state
        }
    }
}