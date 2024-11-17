import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 2001);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream())) {

            out.writeUTF("Solicitação inicial");
            String response = in.readUTF();
            System.out.println("Resposta recebida: " + response);
        } catch (IOException e) {
            System.err.println("Erro ao se conectar ao servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
