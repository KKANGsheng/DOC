package program.sunway.oop
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import javafx.{scene => jfxs}
import scalafxml.core.{NoDependencyResolver, FXMLView, FXMLLoader}
import scalafx.Includes._
import scalafx.collections.ObservableBuffer
import program.sunway.oop.model.Task
import program.sunway.oop.model.Completed
import scalafx.stage.{Stage,Modality}
import program.sunway.oop.view.TaskEditDialogController
object Program extends JFXApp{
// main app create observablebuffer task
// task as a property of main program
// main program  has a collection of buffer
// collection implement public subscribe design pattern
//val taskList=new ObservableBuffer[Task]
//relative path
val rootResource=getClass.getResource("view/RootLayout.fxml")
val tasklist=new ObservableBuffer[Task]()
tasklist+=new Task("doing oop whole day","")
tasklist+=new Task("study","")
tasklist+=new Task("Study for final","")
val loader=new FXMLLoader(rootResource,NoDependencyResolver)
val completedList=new ObservableBuffer[Completed]
loader.load()
// a parent object
val roots:jfxs.layout.BorderPane=loader.getRoot[jfxs.layout.BorderPane]
// initialize  window
stage=new PrimaryStage{
    height=460
    title="TodoList"
    scene=new Scene(){
        root=roots
    }
}
  def showTaskOverview()={
   val resource=getClass.getResource("view/TaskOverview.fxml")
   val loader=new FXMLLoader(resource,NoDependencyResolver)
   loader.load();
    val root:jfxs.layout.AnchorPane=loader.getRoot[jfxs.layout.AnchorPane]
   this.roots.setCenter(root)
  }

def showTaskEditDialog(task:Task):Boolean={
  val resource=getClass.getResource("view/TaskEditDialog.fxml")
  val loader=new FXMLLoader(resource,NoDependencyResolver)
  loader.load();
  // load parent
  val root2=loader.getRoot[jfxs.Parent]
  // load controller
  val control=loader.getController[TaskEditDialogController#Controller]
  // create new window
  val dialog=new Stage(){
    initModality(Modality.APPLICATION_MODAL)
    initOwner(stage)
    // every window must have a scene
    scene=new Scene{
      root=root2
    }
  }
  //initialise window object
  control.dialogStage=dialog
  control.task=task
  // pop out window
  dialog.showAndWait()
  // once user click okay the show and wait will return
  control.okClicked
}
def showAbout(){
  val resource=getClass.getResource("view/About.fxml")
  val loader=new FXMLLoader(resource,NoDependencyResolver)
  loader.load();
  val root2=loader.getRoot[jfxs.Parent]
    val dialog=new Stage(){
    initModality(Modality.APPLICATION_MODAL)
    initOwner(stage)
    title="About"
    scene= new Scene{
      root=root2
    }
  }
  dialog.showAndWait()
}
showTaskOverview()
}


  

