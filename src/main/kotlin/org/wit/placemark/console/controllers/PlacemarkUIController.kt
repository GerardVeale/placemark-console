package org.wit.placemark.console.controllers

import javafx.beans.property.SimpleStringProperty
import loginapp.models.UserModel
import loginapp.views.AddPlacemarkScreen
import loginapp.views.LoginScreen
import loginapp.views.MenuScreen
import loginapp.views.WelcomeScreen
import mu.KotlinLogging
import org.wit.placemark.console.models.PlacemarkJSONStore
import org.wit.placemark.console.views.PlacemarkView
import tornadofx.*

class PlacemarkUIController : Controller() {
    val statusProperty = SimpleStringProperty("")
    var status by statusProperty

    val api: Rest by inject()
    val user: UserModel by inject()

    val placemarks = PlacemarkJSONStore()
    val placemarkView = PlacemarkView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Placemark Console App" }
        println("Placemark Kotlin App Version 4.0")
    }

    init {
        api.baseURI = "https://api.github.com/"
    }

    fun login(username: String, password: String) {
        runLater { status = "" }
        api.setBasicAuth(username, password)
        val response = api.get("user")
        val json = response.one()
        runLater {
            if (response.ok()) {
                user.item = json.toModel()
                find(LoginScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
            } else {
                status = json.string("message") ?: "Login failed"
            }
        }
    }

    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddPlacemarkScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun logout() {
        user.item = null
        primaryStage.uiComponent<UIComponent>()?.replaceWith(LoginScreen::class, sizeToScene = true, centerOnScreen = true)
    }

}