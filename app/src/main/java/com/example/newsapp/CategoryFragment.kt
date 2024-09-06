import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.FragmentCategoryBinding
import com.example.newsapp.model.entity.NewsModel
import com.example.newsapp.model.remote.firebase.FirebaseHelper
import com.example.newsapp.ui.favoriteList.NewsViewModel
import com.example.newsapp.ui.favoriteList.RemoteNewsViewModel
import kotlinx.coroutines.launch

class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var adapter: NewsAdapter
    private lateinit var firebaseHelper: FirebaseHelper
    private val favoriteArticles = mutableListOf<NewsModel>()
    private val viewModel: NewsViewModel by viewModels()// List to hold favorite articles
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using ViewBinding
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get ViewModel instance
        val remoteNewsViewModel: RemoteNewsViewModel by viewModels()

        // Set up RecyclerView
        adapter = NewsAdapter(arrayListOf()) { article ->
            onFavoriteClick(article) // Handle favorite click
        }

        binding.newsList.adapter = adapter
        binding.newsList.layoutManager = LinearLayoutManager(requireContext())

        // Fetch data and update the adapter
        lifecycleScope.launch {
            val articles = remoteNewsViewModel.getCategoryNewsApi("technology")
            articles?.let {
                adapter.articles.clear()  // Clear any previous data
                adapter.articles.addAll(it)  // Add new articles
                adapter.notifyDataSetChanged()  // Notify the adapter of data changes
            }
        }
    }

    // Callback function to handle favorite button click
    private fun onFavoriteClick(article: NewsModel) {
        if (!favoriteArticles.contains(article)) {
            favoriteArticles.add(article)
            firebaseHelper.addNews(article)
            viewModel.addNews(article)
        }

        // Notify the adapter to refresh the UI (if you want to change the favorite icon state)
        adapter.notifyDataSetChanged()
    }
}
