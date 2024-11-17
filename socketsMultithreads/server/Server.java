import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static Map<String, Card> cardDatabase = new HashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(2001)) {
            System.out.println("Servidor iniciado. Aguardando conexões...");

            while (true) {
                Socket connection = serverSocket.accept();
                System.out.println("Cliente conectado: " + connection.getInetAddress());
                new Thread(new ClientHandler(connection)).start();
            }

        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static {
        cardDatabase.put("401231021845", new Card("401231021845", 1000.0));
        cardDatabase.put("501231021846", new Card("501231021846", 500.0));
    }

    public static Card getCard(String cardNumber) {
        return cardDatabase.get(cardNumber);
    }
}
