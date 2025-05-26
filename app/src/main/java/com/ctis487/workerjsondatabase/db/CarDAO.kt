package com.ctis487.workerjsondatabase.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ctis487.workerjsondatabase.model.Cars
import com.ctis487.workerjsondatabase.util.Utils
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCar(cars: Cars)

    @Update
    fun updateCar(cars: Cars)

    @Delete
    fun deleteCar(cars: Cars)

    @Query("SELECT * FROM ${Utils.TABLENAME_CARS} ORDER BY id")
    fun getAllCars():LiveData<List<Cars>>
}