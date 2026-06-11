package Test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Test
{
    public static void main(String[] args) throws InterruptedException {
//        sandbox
    Thread serverThread = new Thread(()->
    {
        try(ServerSocket server=new ServerSocket(8080))
        {
            int numarClienti=0;
            while(numarClienti<3)
            {
                Socket socketClient= server.accept(); // mi a dat pachet(clientul)

                Thread procesareCerere=new Thread(()->{
                    try(Socket socket=socketClient;
                    BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out=new PrintWriter(socket.getOutputStream(),true))
                    {
                        String cerere=in.readLine();
                        //procesare
                        String cerereProcesata=cerere+"-ADAOS SERVER";

                        out.println(cerereProcesata);
                    }
                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                procesareCerere.start();
                numarClienti++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    });

    serverThread.start();
    Thread.sleep(1000);

    //client
        for(int i=0;i<3;i++)
        {
            final int idClient=i;

            Thread clientThread=new Thread(()->{
                try(Socket socket=new Socket("localhost",8080);
                BufferedReader in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out= new PrintWriter(socket.getOutputStream(),true))
                {
                    String cerere="Cererea clientului "+idClient;
                    out.println(cerere);

                    String raspuns=in.readLine();
                    System.out.println(raspuns);
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            clientThread.start();
        }


    }
}
