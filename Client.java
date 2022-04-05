
package client;
import java.io.DataInputStream; 
import java.io.DataOutputStream;
import java.net.Socket; 
import java.util.Scanner;
    
public class Client {

    public static void main(String[] args) {
        Scanner javain = new Scanner(System.in);
Socket socket = null;
DataInputStream in = null;
DataOutputStream out = null;
String emis="",recu="" ;
try {
 //création d'un socket avec l’adresse IP du destinataire et numéro de port
 socket = new Socket("192.168.1.15", 2105);
 System.out.println("Connecter avec le serveur");
 //création des flux de données
 in =  new DataInputStream (socket.getInputStream());
 out = new DataOutputStream(socket.getOutputStream());
        
while (true) {
	//la lecture du message reçu
	recu = in.readUTF();
	if (recu.contains("bye")) {
		System.out.println("Le serveur a quitté");
		out.writeUTF("bye");
		break;
		}
	//l'affichage du message reçu
	System.out.print(recu + "\n Vous : ");
	//la saisie de la réponse
	emis = javain.nextLine();
	//la transmission de la réponse
	out.writeUTF("Votre Nom : " + emis);
	//le vidage de la fonction d'émission
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
