package program.sunway.oop.model
import scalafx.beans.property.{StringProperty,ObjectProperty}
// a class to hold data 
class Task(_task:String,_description:String){
var task=new StringProperty(_task)
var description=new StringProperty(_description)

// override to string method to get the name
override def toString() : String = {
s"${task.value} , ${description.value}\n"
}

// apply method to return task
object Task {
	def apply(task: String, description: String):Task = {
		 new Task(task,description)
	}
}

}