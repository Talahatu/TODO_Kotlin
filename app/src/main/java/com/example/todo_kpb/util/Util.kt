package com.example.todo_kpb.util

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
val MIGRATION_2_3 = object:Migration(2,3){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 not null"); // Karena SQLite tidak support boolean sehingga Room secara otomatis akan mengubah true menjadi 1 dan false menjadi 0 serta sebaliknya
    }
}
fun buildDb(context: Context):TodoDatabase{
    return Room.databaseBuilder(context,TodoDatabase::class.java, DB_NAME).addMigrations(
        MIGRATION_1_2, MIGRATION_2_3).build()
}
