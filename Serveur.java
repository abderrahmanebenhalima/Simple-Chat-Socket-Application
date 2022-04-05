
package serveur;
import java.io.DataInputStream; 
import java.io.DataOutputStream;
import java.net.ServerSocket; 
import java.net.Socket; 
import java.util.Scanner;


public class Serveur {

    public static void main(String[] args) {
        Scanner javain = new Scanner(System.in);
Socket socket = null;
ServerSocket serverSocket = null;
DataInputStream in = null;
DataOutputStream out = null;
String emis="",recu="" ;
try {
//création du socket du serveur
serverSocket = new ServerSocket(2105);
//création du socket réseau
System.out.println("Le serveur a démarré");
//attente de connexion
socket = serverSocket.accept();
System.out.println("Un Client a connecté");
//création du flux d'entrée
in = new DataInputStream(socket.getInputStream());
//création du flux de sortie
out = new DataOutputStream(socket.getOutputStream());
//l'émission de message Bienvenue vers le client
out.writeUTF("Bienvenue sur le serveur vous pouvez envoyer votre message Client");
out.flush();
while (true) {
recu = in.readUTF();
if (recu.contains("bye")) {
         System.out.println("Le Client a quitté");
         out.writeUTF("bye");
         break;
         }
     System.out.print(recu + "\n Vous : ");
     emis = javain.nextLine();
     out.writeUTF("Votre nom : " + emis);
     out.flush();
       }
        }
        //gestion des erreurs
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Communication terminée");
        }

        
    }
}
