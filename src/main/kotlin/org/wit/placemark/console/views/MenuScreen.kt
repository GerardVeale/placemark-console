package org.wit.placemark.console.views

import javafx.application.Platform
import javafx.geometry.Orientation
import org.wit.placemark.console.controllers.PlacemarkUIController
import tornadofx.*

class MenuScreen : View("Placemark Main Menu") {

    val placemarkUIController: PlacemarkUIController by inject()

    override val root = form {
        setPrefSize(400.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Placemark") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        placemarkUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("List Placemarks") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        placemarkUIController.loadListScreen()
                    }
                }
            }
            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }


}