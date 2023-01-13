package com.mfk.dictionary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mfk.dictionary.R
import com.mfk.dictionary.data.model.LocalSearch
import com.mfk.dictionary.databinding.FragmentHomeBinding
import com.mfk.dictionary.ui.adapter.LastSearchAdapter
import com.mfk.dictionary.utils.Constants.SEARCH_TEXT_BUNDLE_KEY
import com.mfk.dictionary.utils.customNavigate
import com.mfk.dictionary.utils.getCurrentTime
import com.mfk.dictionary.utils.remove
import com.mfk.dictionary.utils.show
import com.mfk.dictionary.viewModel.DictionaryViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!
    private val dictionaryViewMode: DictionaryViewModel by activityViewModels()
    private val lastSearchAdapter: LastSearchAdapter by lazy { LastSearchAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        handleClickEvents()
        setupAdapter()
        subscribeObserve()
    }

    private fun setupAdapter() {
        binding.apply {
            rvLastSearch.apply {
                adapter = lastSearchAdapter
            }
        }
    }

    private fun initialize() {
        dictionaryViewMode.getLastSearch()
    }

    private fun subscribeObserve() {

        dictionaryViewMode.getLastSearchResponse.observe(viewLifecycleOwner) { response ->
            response?.let { result ->
                if (response.isNullOrEmpty()) {
                    setupUI(isShow = false)
                } else {
                    setupUI(isShow = true)
                    handleSuccessResponse(result)
                }
            } ?: run {
                setupUI(isShow = false)
            }

        }

        dictionaryViewMode.insertSearchResponse.observe(viewLifecycleOwner) { response ->
            response?.let { result ->
                if (result > 1) {
                    Timber.e("INSERT SUCCESS")
                }
                dictionaryViewMode.clearInsertSearchResponse()
            }
        }
    }

    private fun handleSuccessResponse(result: List<LocalSearch>) {
        lastSearchAdapter.differ.submitList(result)
    }

    private fun setupUI(isShow: Boolean) {
        binding.apply {
            if (isShow) {
                tvRecentHeader.show()
                rvLastSearch.show()
            } else {
                tvRecentHeader.remove()
                rvLastSearch.remove()
            }
        }
    }

    private fun handleClickEvents() {
        binding.apply {
            btnSearch.setOnClickListener {
                setupSearch()
            }
        }
        lastSearchAdapter.setOnItemClickListener { localSearch ->
            setupDetailNavigate(localSearch.searchText)
        }
    }

    private fun setupDetailNavigate(searchText: String) {
        val bundle = Bundle().apply {
            putString(SEARCH_TEXT_BUNDLE_KEY, searchText)
        }
        customNavigate(R.id.detailFragment, bundle = bundle)
    }

    private fun setupSearch() {
        if (checkNullOrEmpty()) {
            // TODO: show error
            Timber.e("ERROR")
        } else {
            Timber.e("SUCCESS")
            val localString = LocalSearch(
                null,
                binding.etSearch.text.toString(),
                getCurrentTime()
            )
            dictionaryViewMode.insertSearch(localString)
            setupDetailNavigate(binding.etSearch.text.toString())
        }
    }

    private fun checkNullOrEmpty(): Boolean {
        return binding.etSearch.text.toString().isEmpty()
    }


}