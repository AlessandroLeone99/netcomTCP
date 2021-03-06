/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;

/**
 *
 * @author Monica Ciuchetti
 */
public class ClientConnessioneTCP {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        //oggetto da usare per realizzare la connessione TCP
        Socket connection = null;
        //nome o IP del server
        String serverAddress = "localhost";
        //porta del server in ascolto
        int port = 2000;
                

        //apertura della connessione al server sulla porta specificata
        try{
            connection = new Socket(serverAddress, port);
            System.out.println("Connessione aperta");
            BufferedReader tastiera= new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter outVersoServer= new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            BufferedReader inDalServer= new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            System.out.println("inserisci la stringa da inviare \n");
            String stringaUtente=tastiera.readLine();
            //spedisco al server
            System.out.println("invio la stringa e attendo \n");
            outVersoServer.write(stringaUtente );
            outVersoServer.newLine();
            outVersoServer.flush();
            //chiudo la connessione
            System.out.println("termine elaborazione e chiusura connessione");
            
        }
        catch(ConnectException e){
            System.err.println("Server non disponibile!");
        }
        catch(UnknownHostException e1){
            System.err.println("Errore DNS!");
        }

        catch(IOException e2){//
            System.err.println(e2);
            e2.printStackTrace();
        }

        //chiusura della connnessione
        finally{
                try {
            if (connection!=null)
                {
                    connection.close();
                    System.out.println("Connessione chiusa!");
                }
            }
            catch(IOException e){
                System.err.println("Errore nella chiusura della connessione!");
            }
        }
    }
}
