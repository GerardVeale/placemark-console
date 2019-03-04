package loginapp.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import loginapp.controllers.PlacemarkUIController
import tornadofx.*

class LoginScreen : View("Placemark (Gitub) Login") {
    val model = ViewModel()
    val username = model.bind { SimpleStringProperty() }
    val password = model.bind { SimpleStringProperty() }
    val placemarkUIController: PlacemarkUIController by inject()

    override val root = form {
        setPrefSize(400.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Username") {
                textfield(username).required()
            }
            field("Password") {
                passwordfield(password).required()
            }
            button("Log in") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        placemarkUIController.login(username.value, password.value)
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
        username.value = ""
        password.value = ""
        model.clearDecorators()
    }
}