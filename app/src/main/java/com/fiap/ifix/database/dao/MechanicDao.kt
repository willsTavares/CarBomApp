package com.fiap.ifix.database.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.fiap.ifix.model.MechanicItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MechanicDao {
    @Insert(onConflict = REPLACE)
    suspend fun save(note: MechanicItem)

    @Insert(onConflict = REPLACE)
    suspend fun save(note: List<MechanicItem>)

    @Query("SELECT * FROM Mechanic")
    fun getAll() : Flow<List<MechanicItem>>

    @Query("SELECT * FROM Mechanic WHERE id = :id")
    fun getById(id: String): Flow<MechanicItem>

    @Query("DELETE FROM Mechanic WHERE id = :id")
    suspend fun remove(id: String)
}