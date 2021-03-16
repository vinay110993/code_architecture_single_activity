package com.example.reactivepractise.listing.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reactivepractise.R
import com.example.reactivepractise.databinding.FragmentListLayoutBinding
import com.example.reactivepractise.fragment.base.BaseFragment
import com.example.reactivepractise.listing.viewmodel.ListViewModel
import com.example.reactivepractise.utils.AppConstants
import com.example.reactivepractise.utils.hideKeyboard
import javax.inject.Inject

class ListFragment: BaseFragment() {

    @Inject lateinit var factory : ViewModelProvider.Factory
    private var binding: FragmentListLayoutBinding?= null
    private var viewModel: ListViewModel ?= null
    private val listAdapter by lazy {
        ListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViewModel()

        view.findViewById<RecyclerView>(R.id.recyclerview).apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = listAdapter
        }

        binding?.etSearchBar?.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val query = binding?.etSearchBar?.text.toString().trim()
                AppConstants.QUERY_SEARCH = query
                listAdapter.currentList?.dataSource?.invalidate()
                binding?.etSearchBar?.hideKeyboard()
            }
            return@OnEditorActionListener true
        })
    }

    private fun initializeViewModel(){
        viewModel = factory.create(ListViewModel::class.java)
        viewModel?.getAccessToken()
        viewModel?.dataList?.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })
    }
    override fun provideScreenTitle() = "Search..."
}