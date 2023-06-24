package com.example.todo_kpb.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @ColumnInfo("title")
    var title:String,
    @ColumnInfo("notes")
    var notes:String,
    @ColumnInfo("priority")
    var priority:Int,
    @ColumnInfo("is_done")
    var isDone:Boolean,
)
{
    @PrimaryKey(true)
    var uuid:Int=0
}