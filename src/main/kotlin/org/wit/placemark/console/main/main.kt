package org.wit.placemark.console.main

import org.wit.placemark.console.controllers.PlacemarkController

fun main(args: Array<String>) {

val controller = PlacemarkController()

    var input: Int

    do {
        input = controller.menu()
        when(input) {
            1 -> controller.add()
            2 -> controller.update()
            3 -> controller.list()
            4 -> controller.search()
            -99 -> controller.dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    controller.logger.info { "Shutting Down Placemark Console App" }
}


