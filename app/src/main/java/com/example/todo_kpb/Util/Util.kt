package com.example.todo_kpb.Util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todo_kpb.model.TodoDatabase

val DB_NAME = "tododb"

val MIGRATION_1_2 = object:Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN priority INTEGER DEFAULT 3 not null");
    }
}

fun buildDb(context: Context):TodoDatabase{
    return Room.databaseBuilder(context,TodoDatabase::class.java, DB_NAME).addMigrations(
        MIGRATION_1_2).build()
}