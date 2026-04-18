package com.amelia.pinbook.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.amelia.pinbook.data.entity.BorrowingEntity

@Dao
interface BorrowingDao {

    @Insert
    suspend fun insertBorrowing(borrowing: BorrowingEntity)

    @Query("SELECT * FROM borrowing WHERE status = 'dipinjam'")
    suspend fun getOngoing(): List<BorrowingEntity>

    @Query("SELECT * FROM borrowing WHERE status = 'dikembalikan'")
    suspend fun getHistory(): List<BorrowingEntity>

    @Query("DELETE FROM borrowing WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("""
        UPDATE borrowing
        SET status = 'dikembalikan',
            returnDate = :returnDate,
            fine = :fine
        WHERE id = :id
    """)
    suspend fun returnBook(
        id: String,
        returnDate: String,
        fine: String
    )
}

