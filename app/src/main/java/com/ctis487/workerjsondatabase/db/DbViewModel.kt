package com.ctis487.workerjsondatabase.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ctis487.workerjsondatabase.model.Cars
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DbViewModel(application: Application) : AndroidViewModel(application) {
    val readAllDataCar: LiveData<List<Cars>>
    private val carDAO: CarDAO

    init {
        carDAO = CarRoomDatabase.getDatabase(application).carDao()
        readAllDataCar = carDAO.getAllCars()
    }

    fun addCar(car: Cars) {
        viewModelScope.launch(Dispatchers.IO) {
            carDAO.insertCar(car)
        }
    }

    fun updateCar(car: Cars) {
        viewModelScope.launch(Dispatchers.IO) {
            carDAO.updateCar(car)
        }
    }

    fun deleteCar(car: Cars) {
        viewModelScope.launch(Dispatchers.IO) {
            carDAO.deleteCar(car)
        }
    }
}
