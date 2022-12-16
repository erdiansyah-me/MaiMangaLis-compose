package com.greil.maimangalis.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greil.maimangalis.data.State
import com.greil.maimangalis.domain.model.MangaDetailModel
import com.greil.maimangalis.domain.model.MangaListModel
import com.greil.maimangalis.domain.usecase.MangaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.State as State1

@HiltViewModel
class MangaViewModel @Inject constructor(private val useCase: MangaUseCase) : ViewModel() {
    val mangaList: StateFlow<State<List<MangaListModel>>> =
        useCase.getListManga().stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            State.NoState()
        )
    private val idSelect = MutableStateFlow(0)
    fun setIdSelect(id:Int) {
        idSelect.value = id
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val mangaDetail = idSelect.distinctUntilChanged{ old, new -> old == new }
        .transformLatest { it ->
            if (it == 0) {
            emit(State.NoState())
        } else {
            useCase.getDetailManga(it)
                .collect{
                    this.emit(it)
                }
        }
    }

    val favoriteManga: StateFlow<List<MangaListModel>> =
        useCase.getFavoriteManga().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    fun insertFavoriteManga(manga: MangaListModel) {
        viewModelScope.launch {
            useCase.insertFavoriteManga(manga)
        }
    }

    fun deleteNonFavoriteManga(id: Int) {
        viewModelScope.launch {
            useCase.deleteNonFavoriteManga(id)
        }
    }


    private val isFavorite = MutableStateFlow(0)
    fun setIsFavorite(id:Int) {
        isFavorite.value = id
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val isFavoriteManga = isFavorite
        .transformLatest {
            if (it == 0) {
                emit(false)
            } else {
                val count = useCase.checkFavoriteManga(it)
                if (count < 1) {
                    emit(false)
                } else {
                    emit(true)
                }
            }
        }
}