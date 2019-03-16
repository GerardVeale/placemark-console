package org.wit.placemark.console.views


import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import javafx.scene.control.TableView
import javafx.scene.layout.GridPane
import org.wit.placemark.console.controllers.PlacemarkUIController
import org.wit.placemark.console.models.PlacemarkModel
import tornadofx.*

class ListPlacemarkScreen : View("List Placemarks") {

    val placemarkUIController: PlacemarkUIController by inject()
    val tableContent = placemarkUIController.placemarks.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        tableview(data) {
            readonlyColumn("ID", PlacemarkModel::id)
            readonlyColumn("TITLE", PlacemarkModel::title)
            readonlyColumn("DESCRIPTION", PlacemarkModel::description)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    placemarkUIController.closeList()
                }
            }
        }
    }

}