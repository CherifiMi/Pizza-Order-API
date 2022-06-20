package com.example.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Order(
    @BsonId
    val id: String= ObjectId().toString(),
    val name: String,
    val size : Int,
    val user: String,
    val address: String,
)
