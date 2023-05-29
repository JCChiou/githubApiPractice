package com.example.githubsearchrepo.presentation

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsearchrepo.R
import com.example.githubsearchrepo.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor() :
    Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var adapter: SearchRepoAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recycler view
        val recyclerView = binding.rvRepo
        val linearLayoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(StickyHeaderDecoration(this.requireContext()))

        binding.edSearch.setOnEditorActionListener { _, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_SEARCH ||
                i == EditorInfo.IME_ACTION_DONE ||
                keyEvent != null &&
                keyEvent.action == KeyEvent.ACTION_DOWN &&
                keyEvent.keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                if (keyEvent == null || !keyEvent.isShiftPressed) {
                    lifecycleScope.launch {
                        homeViewModel.getRepositories(binding.edSearch.text.toString()).collect {
                            if (it.items.isNotEmpty()) {
                                adapter.settingItemsList = it.items
                            }
                        }
                    }
                    return@setOnEditorActionListener true
                }
            }
            return@setOnEditorActionListener false
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}