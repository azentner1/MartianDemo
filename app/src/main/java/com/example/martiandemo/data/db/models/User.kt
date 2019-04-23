package com.example.martiandemo.data.db.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    @Embedded(prefix = "address_")
    val address: Address,
    val phone: String,
    val website: String,
    @Embedded(prefix = "company_")
    val company: Company
)