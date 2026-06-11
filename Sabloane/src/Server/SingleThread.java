package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SingleThread {
    public static void main(String[] args) throws IOException, InterruptedException
    {
// --------SERVER------------

     Thread serverThread = new Thread(()->{
         try(ServerSocket server=new ServerSocket(8080))
         {
             System.out.println("Server astept CLient");

             try(Socket clientSocket = server.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out= new PrintWriter(clientSocket.getOutputStream(),true))
             {
                 String cerere=in.readLine();//ce primesc de la client in stream ul in

                 //prelucrare ce a trimis clientul pe server aici
                 String cererePrelucrata=cerere+" Modificate de server";
                 System.out.println("Clientul a transmis: "+cerere);

                 out.println(cererePrelucrata); //Ce trimit la Client

             }
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     });

     //pornire SERVER
        serverThread.start();//pornesc server
        Thread.sleep(500); // pun main pe pauza 1 secunda

// --------CLIENT------------
        Thread clientThread =new Thread(()->{
            try(Socket socket=new Socket("localhost",8080);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out= new PrintWriter(socket.getOutputStream(),true))
            {
                String cerere="DATELE TRANSMISE DE CLIENT";
                out.println(cerere); // Trimit la Server

                String raspuns= in.readLine(); //Primesc din Server
                System.out.println(("Raspunsul primit este: "+raspuns));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //pornire CLIENT
        clientThread.start();

    }
}
