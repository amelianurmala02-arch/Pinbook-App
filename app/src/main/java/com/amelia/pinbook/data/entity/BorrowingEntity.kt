package com.amelia.pinbook.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "borrowing")
data class BorrowingEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val borrower: String,
    val borrowDate: String,
    val returnDate: String,
    val status: String,
    val fine: String,
    val coverImage: String = ""  // 🆕 TAMBAHAN BARU!
)
