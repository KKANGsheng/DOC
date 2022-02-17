import java.net.Socket
import java.io.DataInputStream
import java.io.DataOutputStream

object TCPClient extends App{

        
      println(Console.YELLOW +
        "+----------------------------+\n" +
        "|  |     | _____________       \n" +
        "|  |_____|       |             \n" +
        "|  |     |       |            \n" +
        "|  |     |       |            \n" +
        "|  |     | ______|_______     \n" +
        "|                            \n" +
        "+----------------------------+" + Console.RESET)

    val socket=new Socket("localhost",8000)
    // for inputstream object
    val is = new DataInputStream(socket.getInputStream() );
    // for outputstream object
    val os = new DataOutputStream(socket.getOutputStream() );
    // writebyte to server
    println(" Menu\n 1.fried rice, 2.fried mee, 3.fried mee hun, 4.fried spaghetti, 5.fried kuey teow,6.fried chicken,7.fried fishball")
    // need to type the items name correctly then correct will print it out
    println(Console.GREEN + "Enter the item name correctly for checking the price" + Console.RESET)
    os.writeBytes(s"${scala.io.StdIn.readLine("Enter item: ")}\n")
    var line:String =is.readLine();
    println(s"$line")


   socket.close

}