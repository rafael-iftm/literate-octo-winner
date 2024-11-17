import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            String request = in.readUTF();
            String response = processRequest(request);
            out.writeUTF(response);

        } catch (IOException e) {
            System.err.println("Erro no cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String processRequest(String request) {
        String[] parts = request.split("\\|");
        if (parts.length != 2) {
            return "Formato inválido. Use: <Número do cartão>|<Valor>";
        }

        String cardNumber = parts[0];
        double amount;

        try {
            amount = Double.parseDouble(parts[1]);
        } catch (NumberFormatException e) {
            return "Valor inválido.";
        }

        Card card = Server.getCard(cardNumber);
        if (card == null) {
            return "Cartão não encontrado.";
        }

        boolean success = card.deductAmount(amount);
        return success ? "Transação aprovada. Saldo atual: " + card.getBalance() :
                         "Saldo insuficiente.";
    }
}
