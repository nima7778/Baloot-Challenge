package ir.nima.balootchallenge.ui.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import ir.nima.balootchallenge.data.db.entities.ArticleEntity
import ir.nima.balootchallenge.data.repository.LocalRepository
import ir.nima.balootchallenge.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class NewsViewModel @ViewModelInject constructor(
    private val repository: MainRepository,
    private val localRepository: LocalRepository
) : ViewModel() {
    lateinit var getDataFromDb:LiveData<List<ArticleEntity>>

    private val getAllLiveData = MutableLiveData(null)
    val all = getAllLiveData.switchMap {
        repository.getAll().cachedIn(viewModelScope)
    }
    fun insert (articleEntity: ArticleEntity){
        localRepository.insertData(articleEntity)
    }

    fun getAll(){
       getDataFromDb= localRepository.getDao.getAllFromDb().flowOn(Dispatchers.IO)
            .asLiveData(context = viewModelScope.coroutineContext)
    }

}