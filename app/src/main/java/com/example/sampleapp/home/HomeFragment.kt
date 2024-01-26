package com.example.sampleapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sampleapp.ListAdapter
import com.example.sampleapp.R
import com.example.sampleapp.databinding.FragmentHomeBinding
import com.example.sampleapp.utils.gone
import com.example.sampleapp.utils.goneIf
import com.example.sampleapp.utils.openBrowser
import com.example.sampleapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()
    private val adapter by lazy {
        ListAdapter {
            openBrowser(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
    }

    private fun initView() {
        binding.apply {
            shimmerLayout.listShimmer.startShimmer()
            rvList.adapter = adapter
            val gridLayoutManager = GridLayoutManager(context, resources.getInteger(R.integer.column_count))
            rvList.layoutManager = gridLayoutManager
        }
    }

    private fun initViewModel() {
        viewModel.responseList.onEach {
            binding.apply {
                shimmerLayout.listShimmer.stopShimmer()
                flShimmer.gone()
                emptyState.goneIf(it.items.isNotEmpty())
            }
            adapter.submitList(it.items)
        }.launchIn(lifecycleScope)
        viewModel.error.onEach {
            binding.apply {
                rvList.gone()
                emptyState.visible()
            }
        }.launchIn(lifecycleScope)
    }
}