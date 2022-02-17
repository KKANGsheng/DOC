package program.sunway.oop.view

import program.sunway.oop.Program
import scalafxml.core.macros.sfxml
import scalafx.scene.control.{MenuItem,ButtonType}
import scalafx.Includes._
import javafx.{scene => jfxs}
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType
import scalafxml.core.{NoDependencyResolver, FXMLLoader}
import scalafx.application.Platform

@sfxml
class RootLayoutController(private val closeMenuButton:MenuItem,private val aboutMenuButton:MenuItem){

/*
a method to allow user to close the windows
*/
def closeWindow(){
   val comfirm=new Alert(AlertType.Confirmation){
        initOwner(Program.stage)
        title="comfirmation "
        headerText="are you sure want to exit from this program"
    }
    val close= comfirm.showAndWait()
    // pattern matching if user  click  button okay it will exit from the program,else nothing will happens
    close match {
      case Some(ButtonType.OK) => Platform.exit
      case _               =>
    }    
  }
def showAbout() {
    Program.showAbout()
  }

}


