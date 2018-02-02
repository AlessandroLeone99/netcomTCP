/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Monica Ciuchetti
 */
public class ServerConnessioneTCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // porta del server maggiore di 1024 
        int port=2000;
        //oggetto ServerSocket necessario per accettare richieste dal client
        ServerSocket sSocket = null;
        //oggetto da usare per realizzare la connessione TCP
        Socket client= null;
        
        String stringaRicevuta= null;
        String stringaModificata=null;
        
        BufferedReader inDalClient = null;
        DataOutputStream outVersoClient = null;

        while(true){
            try{
                // il server si mette in ascolto sulla porta voluta
                sSocket = new ServerSocket(port);
                System.out.println("In attesa di connessioni!");
                //si è stabilita la connessione
                client = sSocket.accept();
                System.out.println("Connessione stabilita!");
                System.out.println("Socket server: " + client.getLocalSocketAddress());
                System.out.println("Socket client: " + client.getRemoteSocketAddress());
                
                inDalClient= new BufferedReader(new InputStreamReader(client.getInputStream()));
                outVersoClient=new DataOutputStream(client.getOutputStream());
                
            }
               catch(IOException e){
                   System.err.println("Errore di I/O!");
            }
            
            //chiusura della connessione con il client
            try {
                if (sSocket!=null) sSocket.close();
            } catch (IOException ex) {
                System.err.println("Errore nella chiusura della connessione!");
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Errore durante l'istanza del server");
                System.exit(1);
            }
            
           
            System.out.println("scrivi qualcosa");
            stringaRicevuta= inDalClient.readLine();
            System.out.println("ricevuta la stringa dal client"+stringaRicevuta);
            
            //la modifico e la rispedisco al client
            System.out.println("invio la stringa modificata...");
            outVersoClient.writeBytes(stringaModificata+"\n");
            
            //termina elaborazione sul server: chiudo la connessione
            System.out.println("Connessione chiusa!");
            client.close();
           
           
            }
        }
      
}
