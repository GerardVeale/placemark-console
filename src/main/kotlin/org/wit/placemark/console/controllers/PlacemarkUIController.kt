package org.wit.placemark.console.controllers

import mu.KotlinLogging
import org.wit.placemark.console.models.PlacemarkJSONStore
import org.wit.placemark.console.models.PlacemarkModel
import org.wit.placemark.console.views.AddPlacemarkScreen
import org.wit.placemark.console.views.ListPlacemarkScreen
import org.wit.placemark.console.views.MenuScreen
import tornadofx.*

class PlacemarkUIController : Controller() {

    val placemarks = PlacemarkJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Placemark TornadoFX UI App" }
    }
    fun add(_title : String, _description : String){

        var aPlacemark = PlacemarkModel(title = _title, description = _description)
            placemarks.create(aPlacemark)
            logger.info("Placemark Added")
    }

    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListPlacemarkScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        placemarks.logAll()
    }

    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddPlacemarkScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeAdd() {
        runLater {
            find(AddPlacemarkScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeList() {
        runLater {
            find(ListPlacemarkScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

}