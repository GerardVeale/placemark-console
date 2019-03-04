package loginapp.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import loginapp.controllers.PlacemarkUIController
import tornadofx.*

class AddPlacemarkScreen : View("Add Placemark") {
    val model = ViewModel()
    val _title = model.bind { SimpleStringProperty() }
    val _description = model.bind { SimpleStringProperty() }
    val placemarkUIController: PlacemarkUIController by inject()

    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Title") {
                textfield(_title).required()
            }
            field("Description") {
                textarea(_description).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        //placemarkUIController.login(username.value, password.value)
                    }
                }
            }
        }
        label(placemarkUIController.statusProperty) {
            style {
                paddingTop = 10
                textFill = Color.RED
                fontWeight = FontWeight.BOLD
            }
        }
    }

    override fun onDock() {
        _title.value = ""
        _description.value = ""
        model.clearDecorators()
    }
}