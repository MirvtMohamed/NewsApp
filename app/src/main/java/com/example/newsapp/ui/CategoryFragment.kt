package com.example.newsapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.FragmentCategoryBinding
import com.example.newsapp.model.entity.NewsModel
import com.example.newsapp.model.remote.firebase.FirebaseHelper
import com.example.newsapp.model.viewmodel.NewsViewModel
import com.example.newsapp.model.viewmodel.RemoteNewsViewModel
import kotlinx.coroutines.launch
class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var adapter: NewsAdapter
    private val favoriteArticles = mutableListOf<NewsModel>()
    private val remoteNewsViewModel: RemoteNewsViewModel by viewModels()
    private val viewModel: NewsViewModel by viewModels()
    private val args: CategoryFragmentArgs by navArgs()  // Safe Args

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryName = args.categoryName

        // Set up RecyclerView
        adapter = NewsAdapter(arrayListOf()) { article ->
            onFavoriteClick(article)
        }

        binding.newsList.adapter = adapter
        binding.newsList.layoutManager = LinearLayoutManager(requireContext())

        // Show progress bar before starting data fetch
        binding.progress.visibility = View.VISIBLE

        // Fetch data based on the category name
        lifecycleScope.launch {
            try {
                val response = remoteNewsViewModel.getCategoryNewsApi(categoryName)
                if (response.isNotEmpty()) {
                    adapter.articles.clear()
                    adapter.articles.addAll(response)
                    adapter.notifyDataSetChanged()
                } else {
                    Log.d("CategoryFragment", "No articles found")
                }
            } catch (e: Exception) {
                Log.d("CategoryFragment", "Error fetching data: ${e.message}")
            } finally {
                // Hide progress bar
                binding.progress.visibility = View.GONE
            }
        }

        // Ensure backButton is not null
        binding.backButton?.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    // Handle favorite button click
    @SuppressLint("NotifyDataSetChanged")
    private fun onFavoriteClick(article: NewsModel) {
        if (!favoriteArticles.contains(article)) {
            favoriteArticles.add(article)
            viewModel.addNews(article)
            val trial= FirebaseHelper()
            trial.addNews(article)
        }
        adapter.notifyDataSetChanged()
    }
}


//    private lateinit var binding: FragmentCategoryBinding
//    private lateinit var adapter: com.example.newsapp.ui.NewsAdapter
//    private lateinit var firebaseHelper: FirebaseHelper
//    private val favoriteArticles = mutableListOf<NewsM>()
//    private val viewModel: NewsViewModel by viewModels()// List to hold favorite articles
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment using ViewBinding
//        binding = FragmentCategoryBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Get ViewModel instance
//        val remoteNewsViewModel: RemoteNewsViewModel by viewModels()
//
//        // Set up RecyclerView
//        adapter = com.example.newsapp.ui.NewsAdapter(arrayListOf()) { article ->
//            onFavoriteClick(article) // Handle favorite click
//        }
//
//        binding.newsList.adapter = adapter
//        binding.newsList.layoutManager = LinearLayoutManager(requireContext())
//
//        // Fetch data and update the adapter
//        lifecycleScope.launch {
//            val articles = remoteNewsViewModel.getCategoryNewsApi("technology")
//            articles?.let {
//                adapter.articles.clear()  // Clear any previous data
//                adapter.articles.addAll(it)  // Add new articles
//                adapter.notifyDataSetChanged()  // Notify the adapter of data changes
//            }
//        }
//    }
//
//    // Callback function to handle favorite button click
//    private fun onFavoriteClick(article: NewsM) {
//        if (!favoriteArticles.contains(article)) {
//            favoriteArticles.add(article)
//            firebaseHelper.addNews(article)
//            viewModel.addNews(article)
//        }
//
//        // Notify the adapter to refresh the UI (if you want to change the favorite icon state)
//        adapter.notifyDataSetChanged()
//    }
//}
