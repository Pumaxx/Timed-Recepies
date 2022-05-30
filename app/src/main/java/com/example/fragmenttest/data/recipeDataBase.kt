package com.example.timersinwindows.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.timersinwindows.Timers
import java.security.AccessControlContext

@Database(entities = [Timers::class], version = 1, exportSchema = false)
abstract class recipeDataBase: RoomDatabase() {

    abstract fun recipeDao(): recipeDao

    companion object{
        @Volatile
        private var INSTANCE: recipeDataBase? = null

        fun getDatabase(context: Context): recipeDataBase {
            val tempIstance = INSTANCE
            if(tempIstance != null) {
                return tempIstance
            }
            synchronized(this){
                val istance = Room.databaseBuilder(
                    context.applicationContext,
                    recipeDataBase::class.java,
                    "recipe_database"
                ).build()
                INSTANCE = istance
                return  istance
            }
        }
    }
}