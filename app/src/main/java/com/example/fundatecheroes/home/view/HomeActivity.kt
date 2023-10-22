package com.example.fundatecheroes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fundatecheroes.character_creation.view.CharacterCreationActivity
import com.example.fundatecheroes.databinding.ActivityHomeBinding
import com.example.fundatecheroes.home.domain.CharacterModel
import com.example.fundatecheroes.home.view.CharacterListAdapter

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val adapter: CharacterListAdapter by lazy {
        CharacterListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.batman.adapter = adapter
        adapter.addList(
            listOf(
                CharacterModel(
                    "batman",
                    "https://s2-g1.glbimg.com/bZJPGF3z5sKBM2qx9LQTikw7zc4=/1200x/smart/filters:cover():strip_icc()/i.s3.glbimg.com/v1/AUTH_59edd422c0c84a879bd37670ae4f538a/internal_photos/bs/2022/m/U/1UvLUASZAevRCb1TBygQ/the-batman-robert-pattinson.jpeg"
                ),
                CharacterModel(
                    "batman",
                    "https://s2-g1.glbimg.com/bZJPGF3z5sKBM2qx9LQTikw7zc4=/1200x/smart/filters:cover():strip_icc()/i.s3.glbimg.com/v1/AUTH_59edd422c0c84a879bd37670ae4f538a/internal_photos/bs/2022/m/U/1UvLUASZAevRCb1TBygQ/the-batman-robert-pattinson.jpeg"
                ),
                CharacterModel(
                    "batman",
                    "https://s2-g1.glbimg.com/bZJPGF3z5sKBM2qx9LQTikw7zc4=/1200x/smart/filters:cover():strip_icc()/i.s3.glbimg.com/v1/AUTH_59edd422c0c84a879bd37670ae4f538a/internal_photos/bs/2022/m/U/1UvLUASZAevRCb1TBygQ/the-batman-robert-pattinson.jpeg"
                )

            )
        )

        binding.removerPersonagem.setOnClickListener {
            adapter.removeItem()
        }

        binding.criarPersonagem.setOnClickListener{
            val intent = Intent(this@HomeActivity, CharacterCreationActivity::class.java)
            startActivity(intent)
        }

    }


}


