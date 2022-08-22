package com.fidelsoft.ricknmonty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.fidelsoft.ricknmonty.databinding.ActivityMainBinding
import com.fidelsoft.ricknmonty.network.createApiService
import com.fidelsoft.ricknmonty.repo.CharacterRepositoryImpl
import com.fidelsoft.ricknmonty.viewmodal.CharacterAdapter
import com.fidelsoft.ricknmonty.viewmodal.CharacterLoadingAdaptor
import com.fidelsoft.ricknmonty.viewmodal.CharacterViewModal
import com.fidelsoft.ricknmonty.viewmodal.ViewModalFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val characterAdapter = CharacterAdapter()
    private val apiService = createApiService()
    private val characterViewModel: CharacterViewModal by viewModels {
        ViewModalFactory(
            repository = CharacterRepositoryImpl(apiService)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        characterViewModel.getCharacters()

        //function call to recycler view
        setupRecView()

        //
        observeViewModelCharacters()
    }

    private fun observeViewModelCharacters() {
        lifecycleScope.launch {
            characterViewModel.characterData
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { data ->
                    if (data != null) {
                        characterAdapter.submitData(data)
                    }
                }
        }
    }

    private fun setupRecView() {
        binding.RVcharacterlist.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = characterAdapter
            adapter = characterAdapter.withLoadStateHeaderAndFooter(
                header = CharacterLoadingAdaptor{ characterAdapter.retry() },
                footer = CharacterLoadingAdaptor{ characterAdapter.retry() }
            )
        }
    }
}