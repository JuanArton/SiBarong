package com.topibatu.sibarong.fragments.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.topibatu.sibarong.MainActivity
import com.topibatu.sibarong.R
import com.topibatu.sibarong.adapter.RecyclerViewAdapter
import com.topibatu.sibarong.database.entity.HistoryEntity
import com.topibatu.sibarong.databinding.FragmentHistoryBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment(){
    private lateinit var binding: FragmentHistoryBinding
    private val viewModel: HistoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        getContent()
        deleteAllHistory()
    }

    private fun deleteAllHistory(){
        binding.floatingActionButton.setOnClickListener {
            viewModel.deleteFromTable()
        }
    }

    private fun getContent(){
        if (activity != null){
            viewModel.getAllHistory().observe(viewLifecycleOwner, { data ->
                binding.recylcerViewHistory.adapter?.let { adapter ->
                    when (adapter) {
                        is RecyclerViewAdapter -> {
                            adapter.setData(data)
                            adapter.setOnItemClickCallback(object :
                                RecyclerViewAdapter.OnItemClickCallback {
                                override fun onItemClicked(data: HistoryEntity) {
                                    (activity as MainActivity).receiveHistory(data)
                                }
                            })
                            adapter.setCallBack(object : RecyclerViewAdapter.OnItemClickCallback {
                                override fun onItemClicked(data: HistoryEntity) {
                                    viewModel.deleteHistory(data)
                                }
                            })
                        }
                    }
                }
            })
        }
    }

    private fun setup(){
        binding.title.text = getString(R.string.riwayat)
        binding.recylcerViewHistory.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RecyclerViewAdapter()
        }
    }

    override fun onResume() {
        super.onResume()
        getContent()
    }


}