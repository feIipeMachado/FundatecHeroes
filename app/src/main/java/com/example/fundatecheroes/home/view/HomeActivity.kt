package com.example.fundatecheroes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.fundatecheroes.character_creation.view.CharacterCreationActivity
import com.example.fundatecheroes.databinding.ActivityHomeBinding
import com.example.fundatecheroes.home.domain.CharacterModel
import com.example.fundatecheroes.home.presentation.HomeViewModel
import com.example.fundatecheroes.home.presentation.model.HomeViewState
import com.example.fundatecheroes.home.view.CharacterListAdapter
import com.example.fundatecheroes.login.presentation.LoginViewModel
import com.example.fundatecheroes.login.presentation.model.LoginViewState
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private val adapter: CharacterListAdapter by lazy {
        CharacterListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        botaoCriarPersonagem()
        binding.list.adapter = adapter

        viewModel.state.observe(this){
            when (it) {
                is HomeViewState.Success -> adapter.addList(it.listaPersonagens)
                HomeViewState.Error -> snackbarListaVazia()
                HomeViewState.Loading -> TODO()
            }
        }

    }



    private fun botaoCriarPersonagem() {
        binding.criarPersonagem.setOnClickListener {
            val intent = Intent(this@HomeActivity, CharacterCreationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun snackbarListaVazia() {
        Snackbar.make(
            binding.root,
            R.string.lista_vazia,
            BaseTransientBottomBar.LENGTH_LONG
        )
            .show()

    }

//    binding.batman.adapter = adapter
//    adapter.addList(
//            listOf(
//                CharacterModel(
//                    "batman",
//                    "https://s2-g1.glbimg.com/bZJPGF3z5sKBM2qx9LQTikw7zc4=/1200x/smart/filters:cover():strip_icc()/i.s3.glbimg.com/v1/AUTH_59edd422c0c84a879bd37670ae4f538a/internal_photos/bs/2022/m/U/1UvLUASZAevRCb1TBygQ/the-batman-robert-pattinson.jpeg"
//                ),
//                CharacterModel(
//                    "batman",
//                    "https://s2-g1.glbimg.com/bZJPGF3z5sKBM2qx9LQTikw7zc4=/1200x/smart/filters:cover():strip_icc()/i.s3.glbimg.com/v1/AUTH_59edd422c0c84a879bd37670ae4f538a/internal_photos/bs/2022/m/U/1UvLUASZAevRCb1TBygQ/the-batman-robert-pattinson.jpeg"
//                ),
//                CharacterModel(
//                    "batman",
//                    "https://s2-g1.glbimg.com/bZJPGF3z5sKBM2qx9LQTikw7zc4=/1200x/smart/filters:cover():strip_icc()/i.s3.glbimg.com/v1/AUTH_59edd422c0c84a879bd37670ae4f538a/internal_photos/bs/2022/m/U/1UvLUASZAevRCb1TBygQ/the-batman-robert-pattinson.jpeg"
//                )
//
//            )
//        )

}


