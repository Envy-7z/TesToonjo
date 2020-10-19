package com.tif.testoonjo.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.tif.testoonjo.local.room.entity.ContactEntity
import io.reactivex.Flowable

@Dao
interface ContactDao {
    @Query("SELECT * from contactTable")
    fun getAll(): List<ContactEntity>

    @Query("SELECT * FROM contactTable WHERE id = :id ")
    fun getById(id: String): Flowable<ContactEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: ContactEntity)


}