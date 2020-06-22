package com.skyeng.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.skyeng.R
import com.skyeng.databinding.MeaningFragmentBinding
import com.skyeng.viewmodel.MeaningViewModel
import com.squareup.picasso.Picasso

const val ARG_MEANING_IDS = "ids"

class MeaningFragment : Fragment() {
    var ids: IntArray? = null
    lateinit var picasso: Picasso

    companion object {
        fun newInstance(ids: IntArray): MeaningFragment {
            val arguments = Bundle()
            arguments.putIntArray(ARG_MEANING_IDS, ids)

            val fragment = MeaningFragment()
            fragment.arguments = arguments

            return fragment
        }
    }

    private lateinit var viewModel: MeaningViewModel
    private lateinit var binding: MeaningFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments == null || arguments?.getIntArray(ARG_MEANING_IDS) == null) {
            throw IllegalArgumentException("Wrong arguments")
        }

        arguments?.let {
            ids = it.getIntArray(ARG_MEANING_IDS)
        }

        viewModel = ViewModelProvider(this).get(MeaningViewModel::class.java)
        picasso = Picasso.Builder(requireContext())
            .loggingEnabled(true)
            .build()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.meaning_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycle.addObserver(viewModel)

        viewModel.meaningData.observe(viewLifecycleOwner, Observer { meaning ->
            binding.data = meaning

            var imageUrl = meaning.images[0].url
            if (imageUrl.startsWith("//")) {
                imageUrl = "http:$imageUrl"
            }
            picasso
                .load(imageUrl)
                .into(binding.image)
        })
        ids?.let { viewModel.load(it[0]) }
    }
}