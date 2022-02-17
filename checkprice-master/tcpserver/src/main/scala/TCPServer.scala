import java.net.{ServerSocket,Socket}
import java.io.DataInputStream
import java.io.DataOutputStream
import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object TCPServer extends App{
  // initialised the array buffer for storing empty Item object.
    val items =new ArrayBuffer[Item]()
    // create instance and price for each category
    val item1=new Item("fried rice",8.00)
    val item2=new Item("fried mee",7.00)
    val item3=new Item("fried mee hun",6.50)
    val item4=new Item("fried spaghetti",8.00)
    val item5=new Item("fried kuey teow",11.00)
    val item6=new Item("fried chicken",10.00)
    val item7=new Item("fried fishball",3.00)

    // append all the items to the arraybuffer
    items.append(item1)
    items.append(item2)
    items.append(item3)
    items.append(item4)
    items.append(item5)
    items.append(item6)
    items.append(item7)

    println("Server is running and waiting for client to run")
    // a method to check for price
    // loop through all the arraybuffer
    // if the userinput name equals to the item name
    // return the value
    // else print warning to user
   def price(userinput:String):String={
      for (item <- items){
        if(item.name.contains(userinput)){
          var price=item.price
          return (s"RM $price")
        }
        }
          return (Console.RED +"sorry please enter item correctly and try it again" +Console.RESET)
      }
    // server socket port create  communication channel to 8000 port
    // must above 1024
    val server=new ServerSocket(8000)
    // different port and identify as client
    // block a main threat until  a connection make to this port it will return random new socket
 try {   // x is socket
    val client:Future[Socket]=Future{server.accept()}
    client.foreach(x=>{
        process(x)
    })
   def process(client:Socket){
       val client2:Future[Socket]=Future{server.accept()}
       client2.foreach(x=>{
        process(x)
       })
        Thread.sleep(2000)
        // return input stream object
        // incoming
        // deode to (bystream)
        // whatever write in dataouptstream can read in datainputstream
        val is = new DataInputStream(client.getInputStream() )
        // return output stream object
        // output
        // encode
        val os = new DataOutputStream(client.getOutputStream() )
        // read input string
        // read from connection(client)
        var input: String = is.readLine()
        // check the price from user input
        var userItem=price(input)
        // write a byte to client
        // display out the price to the client
        os.writeBytes(s" ${userItem}\n")
       // close client socket
        client.close
   }
 } catch{
       case x:Exception=>
        server.close
   }
   scala.io.StdIn.readLine()
   //server.close
 }