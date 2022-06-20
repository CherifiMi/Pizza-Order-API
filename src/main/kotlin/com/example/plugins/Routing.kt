package com.example.plugins

import com.example.model.Order
import com.example.model.Pizza
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.gson.*
import org.litote.kmongo.coroutine.CoroutineCollection

fun Application.configureRouting(pizza_collection: CoroutineCollection<Pizza>, order_collection: CoroutineCollection<Order>) {

    routing {

        install(ContentNegotiation) {
            gson()
        }

        get("/") {
            call.respondText("Hello Pizza Ordering API!")
        }
        get("/pizzas"){

            //call.respondText (pizza_collection.find().toList().size.toString())
            call.respond(pizza_collection.find().toList())
        }
        get("/orders"){
            call.respond(order_collection.find().toList())
        }
    }
}
