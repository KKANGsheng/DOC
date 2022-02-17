package program.sunway.oop.view

import program.sunway.oop.model.Task
import scalafx.scene.control.{TextField, TableColumn, Label, Alert}
import scalafxml.core.macros.sfxml
import scalafx.stage.Stage
import scalafx.Includes._
import scalafx.event.ActionEvent
import program.sunway.oop.Program
import scalafx.scene.control.Alert.AlertType

@sfxml
class TaskEditDialogController(
    private val taskNameField:TextField,
    private val descriptionNameField:TextField
){
var dialogStage:Stage = null
// properties
private var _task:Task = null
var okClicked: Boolean = false

def task = _task
// setter
def task_=(x: Task) {
// update datafield
_task = x
      // based on x get every value
      taskNameField.text = _task.task.value
      descriptionNameField.text = _task.description.value
    }
/*
if the length of tasknamefiled in the edit task is 0 it will pop out the warning else
the edit value will set into the value.
*/
def handleOK(action:ActionEvent)={
    if (taskNameField.text.value.length == 0) {
        new Alert(AlertType.Warning){
        initOwner(Program.stage)
        title= "No selection"
        headerText="No task Selected"
        contentText="please select a task in the table."
    }.showAndWait()
      } else {
        _task.task.value =taskNameField.text()
        _task.description.value=descriptionNameField.text()
        okClicked = true
        dialogStage.close()
      }
    }
/*
a method to cancel
*/
def handleCancel(action:ActionEvent)={
  dialogStage.close()
}


}

