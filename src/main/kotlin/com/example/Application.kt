package com.example

import com.example.model.Order
import com.example.model.Pizza
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val client = KMongo
    .createClient(
        "mongodb+srv://mito:cherifi2003@mitocluster.bpzkl.mongodb.net/pizzas_db?retryWrites=true&w=majority"
    ).coroutine
val database = client.getDatabase("pizzas_db")
val pizza_collection = database.getCollection<Pizza>("pizza")
val order_collection = database.getCollection<Order>("order")

fun main() {



    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting(pizza_collection, order_collection)
        configureSerialization()
    }.start(wait = true)
}
