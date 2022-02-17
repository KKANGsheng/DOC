package program.sunway.oop.model
import scalafx.beans.property.{StringProperty,ObjectProperty}


class Completed(_task:String,_description:String) extends Task(_task,_description){
var comp_task=new StringProperty(_task)
var comp_decrip=new StringProperty(_description)
}

