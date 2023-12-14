package com.example.fundatecheroes.character_creation.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundatecheroes.character_creation.domain.CharacterUseCase
import com.example.fundatecheroes.character_creation.presentation.model.CharacterType
import com.example.fundatecheroes.character_creation.presentation.model.CharacterViewState
import com.example.fundatecheroes.character_creation.presentation.model.UniverseType
import kotlinx.coroutines.launch
import java.util.Date

class CharacterViewModel : ViewModel() {

    private val viewState: MutableLiveData<CharacterViewState> = MutableLiveData()
    val state: LiveData<CharacterViewState> = viewState

    private val useCase by lazy {
        CharacterUseCase()
    }

    fun validarInputs(
        name: String,
        description: String,
        image: String,
        universeType: Int,
        characterType: Int,
        age: Int,
        birthday: Date? = null
    ) {
        //viewState.value = CharacterViewState.Loading

        if (name.isNullOrEmpty()) {
            viewState.value = CharacterViewState.EmptyFieldError
            return
        }

        if (description.isNullOrEmpty()) {
            viewState.value = CharacterViewState.EmptyFieldError
            return
        }

        if (image.isNullOrEmpty()) {
            viewState.value = CharacterViewState.EmptyFieldError
            return
        }

        if (universeType == 0) {
            viewState.value = CharacterViewState.EmptyFieldError
            return
        }

        if (characterType == 0) {
            viewState.value = CharacterViewState.EmptyFieldError
            return
        }

        if (age == 0) {
            viewState.value = CharacterViewState.AgeError
            return
        }

        viewModelScope.launch {
            val isSuccess = useCase.createCharacter(
                name = name,
                description = description,
                image = image,
                universeType = UniverseType.valueOf(universeType),
                characterType = CharacterType.valueOf(characterType),
                age = age,
                birthday = birthday
            )

            if (isSuccess) {
                viewState.value = CharacterViewState.Success
            } else {
                viewState.value = CharacterViewState.Error
            }
        }


    }
}


