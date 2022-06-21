package com.example.plugins

import com.example.model.Order
import com.example.model.Pizza
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.gson.*
import io.ktor.request.*
import org.litote.kmongo.coroutine.CoroutineCollection

fun Application.configureRouting(pizza_collection: CoroutineCollection<Pizza>, order_collection: CoroutineCollection<Order>) {

    routing {

        install(ContentNegotiation) {
            gson()
        }

        //-----------------hello api
        get("/") {
            call.respondText("Hello Pizza Ordering API!")
        }

        //-----------------pizza list
        route("/pizzas"){
            get{
                call.respond(pizza_collection.find().toList())
            }
            post {
                call.parameters
                val requestBody = call.receive<Pizza>()
                val isSuccess = pizza_collection.insertOne(requestBody).wasAcknowledged()
                call.respond(isSuccess)
            }
            delete {
                call.parameters
                val requestBody = call.receive<Int>()
                val isSuccess = pizza_collection.deleteOneById(requestBody).wasAcknowledged()
                call.respond(isSuccess)
            }
        }

        //-----------------orders
        route("/orders"){
            get{
                call.respond(order_collection.find().toList())
            }
            post {
                call.parameters
                val requestBody = call.receive<Order>()
                val isSuccess = order_collection.insertOne(requestBody).wasAcknowledged()
                call.respond(isSuccess)
            }
            delete {
                call.parameters
                val requestBody = call.receive<Int>()
                val isSuccess = order_collection.deleteOneById(requestBody).wasAcknowledged()
                call.respond(isSuccess)
            }
        }

    }
}
