package com.topibatu.sibarong.fragments.analyze

import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.topibatu.sibarong.MainActivity
import com.topibatu.sibarong.R
import com.topibatu.sibarong.api.DataParcel
import com.topibatu.sibarong.database.entity.HistoryEntity
import com.topibatu.sibarong.databinding.FragmentAnalyzeBinding
import org.koin.android.viewmodel.ext.android.viewModel
import java.math.RoundingMode
import java.text.DecimalFormat
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
        binding.getsnippet.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.analyzebutton) {
            setBottomSheet(BottomSheetBehavior.STATE_COLLAPSED)
            val newsText = binding.newsText.text.toString()
            binding.analyzeLoadBar.visibility = View.VISIBLE
            binding.analyzebutton.visibility = View.INVISIBLE
            analyzeViewModel.analyzeNews(newsText).observe(viewLifecycleOwner, { data ->
                binding.analyzeLoadBar.visibility = View.INVISIBLE
                binding.analyzebutton.visibility = View.VISIBLE

                binding.percentage.text = try{
                    val parsedString = parseString(data)
                    analyzeViewModel.insertText(HistoryEntity(
                        newsText,
                        parsedString
                    ))
                    StringBuilder().append(parsedString).append("%")
                } catch (e: Exception){
                    "Error"
                }
                setBottomSheet(BottomSheetBehavior.STATE_EXPANDED)
            })
        }
        if (v.id == R.id.getsnippet){
            val clipboard = activity?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            binding.newsText.setText(clipboard.primaryClip?.getItemAt(0)?.text)
        }
    }

    private fun parseString(data: DataParcel): String{
        val numb = data.news.filter { it.isDigit() || it =='.'}.toDouble()
        val formatNumber = DecimalFormat("#.##")
        formatNumber.roundingMode = RoundingMode.UP
        return formatNumber.format(numb)
    }

    private fun setBottomSheet(state: Int){
        BottomSheetBehavior.from(binding.botsheet).apply{
            peekHeight = 300
            bottomSheetState = this.state
            this.state = state
        }
    }

    override fun onResume() {
        super.onResume()
        val stat = (activity as MainActivity).applyToFragment(binding)
        if(stat){
            setBottomSheet(BottomSheetBehavior.STATE_EXPANDED)
        }
    }
}