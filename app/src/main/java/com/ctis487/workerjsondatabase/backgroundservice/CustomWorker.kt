package com.ctis487.workerjsondatabase.backgroundservice

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.ctis487.workerjsondatabase.db.CarRoomDatabase
import com.ctis487.workerjsondatabase.model.Cars
import com.ctis487.workerjsondatabase.retrofit.ApiClient
import com.ctis487.workerjsondatabase.retrofit.ApiService
import kotlinx.coroutines.runBlocking

class CustomWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        return try {
            // Get the API service
            val apiService = ApiClient.getClient().create(ApiService::class.java)
            
            // Get the database
            val database = CarRoomDatabase.getDatabase(applicationContext)
            val carDao = database.carDao()

            // Make the API call synchronously
            runBlocking {
                try {
                    val response = apiService.getCars().execute()
                    if (response.isSuccessful) {
                        response.body()?.let { cars ->
                            // Insert cars into database
                            cars.forEach { car ->
                                carDao.insertCar(car)
                            }
                        }
                        Result.success()
                    } else {
                        Log.e("CustomWorker", "API call failed: ${response.code()}")
                        Result.failure()
                    }
                } catch (e: Exception) {
                    Log.e("CustomWorker", "Error in worker", e)
                    Result.failure()
                }
            }
            Result.success()
        } catch (e: Exception) {
            Log.e("CustomWorker", "Error in worker", e)
            Result.failure()
        }
    }
}