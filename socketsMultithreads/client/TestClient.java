import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TestClient {
    public static void main(String[] args) {
        String serverHost = "localhost";
        int serverPort = 2001;

        realizarTeste(serverHost, serverPort, "123456789012", 100.0);
        realizarTeste(serverHost, serverPort, "123456789012", 1500.0);
        realizarTeste(serverHost, serverPort, "987654321098", 300.0);
    }

    private static void realizarTeste(String serverHost, int serverPort, String numeroCartao, double valorTransacao) {
        try (Socket socket = new Socket(serverHost, serverPort);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream())) {

            String mensagem = numeroCartao + "|" + valorTransacao;
            out.writeUTF(mensagem);

            String resposta = in.readUTF();
            System.out.println("Cartão: " + numeroCartao + ", Valor: " + valorTransacao + " -> Resposta: " + resposta);

        } catch (IOException e) {
            System.err.println("Erro ao realizar teste com o cartão " + numeroCartao + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
