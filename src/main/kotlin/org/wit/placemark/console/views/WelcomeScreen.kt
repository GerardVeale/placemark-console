package loginapp.views

import javafx.geometry.Pos
import javafx.scene.text.FontWeight
import loginapp.controllers.PlacemarkUIController
import loginapp.models.UserModel
import tornadofx.*

class WelcomeScreen : View("Welcome") {
    val user: UserModel by inject()
    val placemarkUIController: PlacemarkUIController by inject()

    override val root = vbox(10) {
        setPrefSize(1200.0, 600.0)
        alignment = Pos.CENTER

        label(user.name) {
            style {
                fontWeight = FontWeight.BOLD
                fontSize = 24.px
            }
        }

        button("Logout").action(placemarkUIController::logout)

    }
}