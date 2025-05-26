package com.ctis487.workerjsondatabase.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.ctis487.workerjsondatabase.model.Cars
import com.ctis487.workerjsondatabase.util.Utils
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [Cars::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class CarRoomDatabase : RoomDatabase() {

    abstract fun carDao(): CarDAO

    companion object{
        @Volatile  //it makes that instance to visible to other threads
        private var INSTANCE: CarRoomDatabase?=null

        fun getDatabase(context:Context): CarRoomDatabase {
            val tempInstance = INSTANCE
            if(tempInstance !=null){
                return  tempInstance
            }
            synchronized(this){
                val  instance =Room.databaseBuilder(context.applicationContext, CarRoomDatabase::class.java, Utils.TABLENAME_CARS).build()
                INSTANCE = instance
                return instance
                }
        }

    }
}
