package com.example.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class NoteEntity(
  val title: String,
  val description: String,
  @PrimaryKey
  val id: Int? = null
)
