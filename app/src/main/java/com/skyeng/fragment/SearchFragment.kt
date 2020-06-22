package com.skyeng.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skyeng.MainActivity
import com.skyeng.R
import com.skyeng.adapter.SearchResultListAdapter
import com.skyeng.databinding.SearchFragmentBinding
import com.skyeng.viewmodel.SearchViewModel

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.data = viewModel
        viewLifecycleOwner.lifecycle.addObserver(viewModel)

        val listAdapter = SearchResultListAdapter(object: SearchResultListAdapter.OnMeaningClickListener {
            override fun onClick(id: Int) {
                val activity = requireActivity()
                if (activity is MainActivity) {
                    activity.navigate(MeaningFragment.newInstance(intArrayOf(id)))
                }
            }
        })
        binding.list.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = listAdapter
        }

        viewModel.resultData.observe(viewLifecycleOwner, Observer { result ->
            listAdapter.setData(result)
        })
    }
}