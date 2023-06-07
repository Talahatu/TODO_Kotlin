package com.example.todo_kpb.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo_kpb.Util.MIGRATION_1_2

@Database(entities=[Todo::class], version = 2)
abstract class TodoDatabase:RoomDatabase() {
    abstract fun todoDao():TodoDao
    companion object{
        @Volatile private var instance:TodoDatabase?=null
        private val Lock = Any()
        private fun buildDatabase(context: Context)= Room.databaseBuilder(context.applicationContext,TodoDatabase::class.java,"tododb").addMigrations(
            MIGRATION_1_2).build()
        operator fun invoke(context: Context){
            if(instance!=null){
                synchronized(Lock){
                    instance?: buildDatabase(context).also {
                        instance=it
                    }
                }
            }
        }
    }
}