package com.mfk.dictionary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mfk.dictionary.data.model.DataMuse
import com.mfk.dictionary.data.model.Dictionary
import com.mfk.dictionary.data.model.Meanings
import com.mfk.dictionary.databinding.FragmentDetailBinding
import com.mfk.dictionary.ui.adapter.DataMuseAdapter
import com.mfk.dictionary.ui.adapter.DetailGroupAdapter
import com.mfk.dictionary.ui.adapter.DictionaryDetailAdapter
import com.mfk.dictionary.utils.Constants
import com.mfk.dictionary.utils.Resource
import com.mfk.dictionary.utils.show
import com.mfk.dictionary.viewModel.DataMuseViewModel
import com.mfk.dictionary.viewModel.DictionaryViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!
    private val dictionaryViewModel: DictionaryViewModel by activityViewModels()
    private val dataMuseViewModel: DataMuseViewModel by activityViewModels()
    private val dictionaryDetailAdapter: DictionaryDetailAdapter by lazy { DictionaryDetailAdapter() }
    private val groupAdapter: DetailGroupAdapter by lazy { DetailGroupAdapter() }
    private val dataMuseAdapter: DataMuseAdapter by lazy { DataMuseAdapter() }
    private var searchText = ""
    private var dictionaryAdapterList = ArrayList<Meanings>()
    private var filterList = ArrayList<Meanings>()
    private var headerGroupList = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleArgument()
    }

    private fun handleArgument() {
        val searchText = arguments?.getString(Constants.SEARCH_TEXT_BUNDLE_KEY)
        if (searchText != null) {
            this.searchText = searchText
            Timber.e(searchText)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        handleClickEvents()
        setupAdapter()
        subscribeObserve()
    }

    private fun handleClickEvents() {
        binding.apply {
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        groupAdapter.setOnItemClickListener { item ->
            setupFilter(item)
        }
    }

    private fun setupFilter(item: String) {
        dictionaryAdapterList.filterTo(
            filterList
        ) { it.partOfSpeech?.lowercase() == item }
        setupDictionaryAdapterList(filterList)
    }

    private fun setupDictionaryAdapterList(list: ArrayList<Meanings>) {
        dictionaryDetailAdapter.differ.submitList(list)
    }

    private fun setupGroupAdapterList(groupList: ArrayList<String>) {
        groupAdapter.differ.submitList(groupList)
    }

    private fun subscribeObserve() {
        dictionaryViewModel.getDictionaryDetailResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    response?.data?.let { result ->
                        handleSuccessResponse(result)
                    }

                }
            }
        }

        dataMuseViewModel.getDataMuseResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    response?.data?.let { result ->
                        handleDataMuseSuccessResponse(result)
                    }

                }
            }
        }
    }

    private fun handleDataMuseSuccessResponse(result: List<DataMuse>) {
        val list = result.sortedByDescending { it.score }
        binding.tvSynonymHeader.show()
        dataMuseAdapter.differ.submitList(list.subList(0,5))
    }

    private fun handleSuccessResponse(result: List<Dictionary>) {
        populateUI(result)
        dictionaryAdapterList = ArrayList()
        headerGroupList = ArrayList()
        for (i in result[0].meanings) {
            i.partOfSpeech?.let { tmp ->
                if (!headerGroupList.contains(tmp)) {
                    headerGroupList.add(tmp)
                }
            }

            if (i.partOfSpeech?.lowercase()?.trim().equals("noun")) {
                dictionaryAdapterList.add(i)
            }
        }
        for (i in result[0].meanings) {
            if (i.partOfSpeech?.lowercase()?.trim().equals("verb")) {
                dictionaryAdapterList.add(i)
            }
        }
        for (i in result[0].meanings) {
            if (i.partOfSpeech?.lowercase()?.trim().equals("adverb")) {
                dictionaryAdapterList.add(i)
            }
        }
        for (i in result[0].meanings) {
            if (i.partOfSpeech?.lowercase()?.trim().equals("adjective")) {
                dictionaryAdapterList.add(i)
            }
        }
        setupDictionaryAdapterList(dictionaryAdapterList)
        setupGroupAdapterList(headerGroupList)
    }

    private fun populateUI(result: List<Dictionary>) {
        binding.apply {
            tvWord.text = result[0].word
            tvPhonetic.text = result[0].phonetic
        }
    }

    private fun setupAdapter() {
        binding.apply {
            rvEntries.apply {
                adapter = dictionaryDetailAdapter
            }
            rvGroup.apply {
                adapter = groupAdapter
            }
            rvDataMuse.apply {
                adapter = dataMuseAdapter
                layoutManager = GridLayoutManager(requireContext(),4)
            }
        }
    }

    private fun initialize() {
        dictionaryViewModel.getDictionaryDetail(searchText)
        dataMuseViewModel.getDataMuse(searchText)
    }
}