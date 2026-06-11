package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MultiThread {
    public static void main(String[] args) throws InterruptedException {
        Thread serverThread=new Thread(()->{
            try(ServerSocket server=new ServerSocket(8080))
            {
                System.out.println("SERVER asteapta clienti");

                //daca am limita de clienti fac un contor
                int numarClienti=0;

                //cat timp nu am servit 3 clienti
                while(numarClienti<3)
                {
                    Socket clientSocket=server.accept(); //primesc pachet de la Client

                    Thread procesareCerere=new Thread(()->{

                        try(Socket socket=clientSocket;
                            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            PrintWriter out=new PrintWriter(socket.getOutputStream(),true))
                        {
                            String cerere=in.readLine();

                            //procesare
                            String cerereProces=cerere+" -PROCESATA!";

                            out.println(cerereProces);
                        }catch (IOException e) {
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

        //CLIENTI
        for(int i=0;i<3;i++)
        {
            final int idClient=i; //ca sa stiu al catelea client e

            Thread clientThread = new Thread(()->{
                try(Socket socket=new Socket("localhost",8080);
                  BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                  PrintWriter out=new PrintWriter(socket.getOutputStream(),true))
                {
                    String cerere="Cererea clientului "+idClient;
                    out.println(cerere);

                    String raspuns=in.readLine();
                    System.out.println(raspuns);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            clientThread.start();
        }
    }
}
