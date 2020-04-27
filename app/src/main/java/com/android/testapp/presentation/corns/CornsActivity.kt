package com.android.testapp.presentation.corns

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.testapp.R
import com.android.testapp.base.utils.UIState
import com.android.testapp.base.utils.extensions.observe
import com.android.testapp.domain.model.CornModel
import com.android.testapp.presentation.map.MapActivity
import kotlinx.android.synthetic.main.activity_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CornsActivity : AppCompatActivity(R.layout.activity_list) {
    private val viewModel by viewModel<CornsViewModel>()
    private val adapter = CornAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe(viewModel.corns) { observeCorns(it) }
        setupUI()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.corns_menu, menu)
        val search = menu.findItem(R.id.searchView)
        setupSearch(search.actionView as SearchView)
        return true
    }

    private fun setupUI() {
        setupAdapter()
    }

    private fun setupSearch(searchView: SearchView) {
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.filterCorns(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.filterCorns(newText)
                return true
            }
        })
    }

    private fun setupAdapter() {
        adapter.itemClickListener = ::handleClickEvent
        rvCorn.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvCorn.adapter = adapter
    }

    private fun handleClickEvent(cornModel: CornModel) {
        showMapScreen(cornModel)
    }

    private fun showMapScreen(cornModel: CornModel) {
        startActivity(MapActivity.newInstance(this, cornModel))
    }

    private fun observeCorns(uiState: UIState<List<CornModel>>) {
        if (uiState is UIState.Success) {
            adapter.submitList(uiState.data)
        }

        progressBar.isVisible = uiState is UIState.Loading
        rvCorn.isVisible = uiState is UIState.Success<*>
        tvEmpty.isVisible = uiState is UIState.Empty
    }
}
