package com.example.fundatecheroes.character_creation.presentation

import androidx.constraintlayout.motion.utils.ViewState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundatecheroes.character_creation.domain.CharacterUseCase
import com.example.fundatecheroes.character_creation.presentation.model.CharacterViewState
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
        universeType: String,
        characterType: String,
        age: Int,
        birthday:Date?= null
    ) {

        if(name.isNullOrEmpty()) {
            viewState.value = CharacterViewState.EmptyFieldError
            return
        }

        if(description.isNullOrEmpty()) {
            viewState.value = CharacterViewState.EmptyFieldError
            return
        }

        if(image.isNullOrEmpty()) {
            viewState.value = CharacterViewState.EmptyFieldError
            return
        }

        if(universeType.isNullOrEmpty()) {
            viewState.value = CharacterViewState.EmptyFieldError
            return
        }

        if (characterType.isNullOrEmpty()) {
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
                universeType = universeType,
                characterType = characterType,
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


