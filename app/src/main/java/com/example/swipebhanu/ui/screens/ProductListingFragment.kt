package com.example.swipebhanu.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swipebhanu.R
import com.example.swipebhanu.data.models.Product
import com.example.swipebhanu.databinding.FragmentProductListingBinding
import com.example.swipebhanu.ui.adapter.ProductListAdapter
import com.example.swipebhanu.ui.viewmodels.ProductViewModel
import com.example.swipebhanu.utils.UiState
import com.example.swipebhanu.utils.toast
import org.koin.android.ext.android.inject
import java.util.*

class ProductListingFragment : Fragment() {

    private lateinit var binding: FragmentProductListingBinding
    private val viewModel by inject<ProductViewModel>()
    private lateinit var searchView: SearchView
    private lateinit var productAdapter: ProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()

        searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
        binding.noProductFound.visibility = View.GONE
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            productAdapter = ProductListAdapter()
            adapter = productAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.productListState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is UiState.Success<*> -> {
                    binding.noProductFound.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE

                    val productList = state.data as? List<Product> ?: emptyList() // ✅ Cast safely
                    productAdapter.updateList(productList)
                }
                is UiState.Failure -> {
                    binding.noProductFound.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    toast(state.error)
                }
            }
        }
    }

    private fun filterList(query: String?) {
        val deviceListState = viewModel.productListState.value
        if (query != null && deviceListState is UiState.Success) {
            val deviceList = deviceListState.data as? List<Product> ?: emptyList() // ✅ Safe cast
            val filteredList = deviceList.filter { item ->
                item.product_name.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT)) ||
                        item.product_type.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT)) ||
                        item.tax.toString().contains(query) ||
                        item.price.toString().contains(query)
            }

            if (filteredList.isEmpty()) {
                binding.noProductFound.visibility = View.VISIBLE
                productAdapter.updateList(mutableListOf())
                toast("No Data found")
            } else {
                binding.noProductFound.visibility = View.GONE
                productAdapter.updateList(filteredList.toMutableList())
            }
        } else {
            binding.noProductFound.visibility = View.GONE
            productAdapter.updateList(mutableListOf())
        }
    }
}
