package com.judelakshitha.arimacnewsapp.ui.base.component.news

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.judelakshitha.arimacnewsapp.R
import com.judelakshitha.arimacnewsapp.data.Resource
import com.judelakshitha.arimacnewsapp.data.dto.news.News
import com.judelakshitha.arimacnewsapp.data.dto.news.NewsItem
import com.judelakshitha.arimacnewsapp.data.error.SEARCH_ERROR
import com.judelakshitha.arimacnewsapp.ui.base.BaseActivity
import com.judelakshitha.arimacnewsapp.ui.base.component.details.NewsDetailsActivity
import com.judelakshitha.arimacnewsapp.ui.base.component.news.adapter.NewsAdapter
import  com.judelakshitha.arimacnewsapp.databinding.HomeActivityBinding

class NewsListActivity : BaseActivity() {
    private lateinit var binding: HomeActivityBinding

    private val newsListViewModel:NewsListViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter


    override fun initViewBinding() {
        binding = HomeActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.news)
        val layoutManager = LinearLayoutManager(this)
        binding.rvRecipesList.layoutManager = layoutManager
        binding.rvRecipesList.setHasFixedSize(true)
        newsListViewModel.getRecipes()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_actions, menu)
        // Associate searchable configuration with the SearchView
        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_by_name)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                handleSearch(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> recipesListViewModel.getRecipes()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun handleSearch(query: String) {
        if (query.isNotEmpty()) {
            binding.pbLoading.visibility = View.VISIBLE
            recipesListViewModel.onSearchClick(query)
        }
    }

    private fun bindListData(recipes: News) {
        if (!(recipes.newsList.isNullOrEmpty())) {
            recipesAdapter = NewsAdapter(recipesListViewModel, recipes.newsList)
            binding.rvRecipesList.adapter = recipesAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }
    private fun navigateToDetailsScreen(navigateEvent: SingleEvent<NewsItem>) {
        navigateEvent.getContentIfNotHandled()?.let {
            val nextScreenIntent = Intent(this, NewsDetailsActivity::class.java).apply {
                putExtra(NEWS_ITEM_KEY, it)
            }
            startActivity(nextScreenIntent)
        }
    }

    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private fun showSearchError() {
        recipesListViewModel.showToastMessage(SEARCH_ERROR)
    }

    private fun showDataView(show: Boolean) {
        binding.tvNoData.visibility = if (show) View.GONE else View.VISIBLE
        binding.rvRecipesList.visibility = if (show) View.VISIBLE else View.GONE
        binding.pbLoading.toGone()
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.rvRecipesList.toGone()
    }


    private fun showSearchResult(recipesItem: NewsItem) {
        recipesListViewModel.openRecipeDetails(recipesItem)
        binding.pbLoading.toGone()
    }

    private fun noSearchResult(unit: Unit) {
        showSearchError()
        binding.pbLoading.toGone()
    }

    private fun handleNewsList(status: Resource<News>) {
        when (status) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> status.data?.let { bindListData(recipes = it) }
            is Resource.DataError -> {
                showDataView(false)
                status.errorCode?.let { recipesListViewModel.showToastMessage(it) }
            }
        }
    }

    override fun observeViewModel() {
        observe(newsListViewModel.newsLiveData, ::handleRecipesList)
        observe(newsListViewModel.newsSearchFound, ::showSearchResult)
        observe(newsListViewModel.noSearchFound, ::noSearchResult)
        observeEvent(newsListViewModel.openNewsDetails, ::navigateToDetailsScreen)
        observeSnackBarMessages(newsListViewModel.showSnackBar)
        observeToast(newsListViewModel.showToast)

    }
}

