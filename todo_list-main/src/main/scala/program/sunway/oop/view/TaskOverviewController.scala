package program.sunway.oop.view

import program.sunway.oop.model.Task
import program.sunway.oop.Program
import program.sunway.oop.model.Completed

import scalafx.scene.control.{ ListView,Button, TextField, SelectionModel,Alert,DatePicker}
import scalafxml.core.macros.sfxml
import scalafx.beans.property.{StringProperty} 
import scalafx.Includes._
import javafx.{scene => jfxs}
import scalafx.scene.control.Alert.AlertType
import scalafxml.core.{NoDependencyResolver, FXMLLoader}
import scalafx.event.ActionEvent
@sfxml
class TaskOverviewController(
    private val taskTextField:TextField,
    private val addbutton:Button,
    private val deletebutton:Button,
    private val editbutton:Button,
    private val completedbutton:Button,
    private val taskList:ListView[Task],
){
// assign observable buffer
taskList.items=Program.tasklist
addbutton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;")
editbutton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;")
deletebutton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;")
completedbutton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;")
// an operation to delete task
def handleRemoveTask(action:ActionEvent){
    val selectedIndex=taskList.selectionModel().selectedIndex()
    if (selectedIndex >=0){
        taskList.items().remove(selectedIndex)
    }
  else{
        new Alert(AlertType.Warning){
        initOwner(Program.stage)
        title= "No selection"
        headerText="No task Selected"
        contentText="please select a task in the table."
    }.showAndWait()
} 

}
def handleNewTask(action:ActionEvent) {
    // defined a variable to secify textfield value
    val text = taskTextField.text.value
    // if textfiled length is 0 pop out the warning message
    if (text.length == 0) {
        new Alert(AlertType.Warning){
        initOwner(Program.stage)
        title= "No selection"
        headerText="No task Selected"
        contentText="please select a task in the table."
    }.showAndWait()
 } 
    else{
      // if not 0 adding the task into the listview.
      val task = new Task(text,"")
      Program.tasklist+=task
    }  
  }

/*
if edit button is clicked it will pop out edit dialog and  trigger the post
if selected task is null, it will pop out the warning message.
*/
def handleEditTask(action:ActionEvent){
    val selectedTask=taskList.selectionModel().selectedItem.value
    // checking if the select task is null or not
    if (selectedTask !=null){
      val okCliked=Program.showTaskEditDialog(selectedTask)
      if (okCliked){
        refreshlist()
      }
    }else{
          new Alert(Alert.AlertType.Warning){
          initOwner(Program.stage)
          title       = "No Selection"
          headerText  = "No Task Selected"
          contentText = "Please select a task in the table."
        }.showAndWait()
    }
  }
  // to trigger the change 
def refreshlist():Unit={
  val a =new Task("","")
  Program.tasklist += a
  Program.tasklist -= a
}

/*
method to handle complete task
*/
def handleCompletedtask(action:ActionEvent){
    val selectedvalue=taskList.selectionModel().selectedItem()
    if (selectedvalue !=null){
        taskList.items().remove(selectedvalue)
    }
  else{
        new Alert(AlertType.Warning){
        initOwner(Program.stage)
        title= "No selection"
        headerText="No task Selected"
        contentText="please select a task in the table."
    }.showAndWait()
  }
 }
}