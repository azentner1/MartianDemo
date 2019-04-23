package com.example.martiandemo.data.db.models

import androidx.room.Embedded


data class Address(val street: String,
                   val suite: String,
                   val city: String,
                   val zipcode: String,
                   @Embedded(prefix = "geo_")
                   val geo: Geo)