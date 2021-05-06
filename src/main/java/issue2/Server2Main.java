package issue2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2Main implements Sendable{
    private static String myName = "Server";

    @Override
    public void sendResp(String response, PrintWriter out) {
        out.println(response);
        System.out.println(String.format("%s: %s", myName, response));
    }

    public void startServer(){
        try (ServerSocket serverSocket = new ServerSocket(8080);
             Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))){

            System.out.println("New connection accepted");
            String resp = "Hi,Client! Please, enter your name";
            this.sendResp(resp, out);
            final String clientsName = in.readLine();
            System.out.println(String.format("Client: %s", clientsName));

            resp = String.format("Nice to meet you, %s! Tell me, please, how old are you?", clientsName);
            this.sendResp(resp, out);
            String clientsAge = in.readLine();
            System.out.println(String.format("%s: %s" , clientsName, clientsAge));
            int age = Integer.parseInt(clientsAge);
            String clientsContent = null;
            if (age < 14) {
                clientsContent = "games";
                resp = String.format("Dear kiddo, welcome to the children area. How about some %s? yes/no", clientsContent);
            }
            if (age >= 14 && age < 18) {
                clientsContent = "series";
                resp = String.format("Yo, welcome to the teens area. Some %s then? yes/no", clientsContent);
            }
            if (age >= 18) {
                clientsContent = "music";
                resp = String.format("Welcome to the adult zone, %s! Would you like some %s for a good chill, or a good working day? yes/now", clientsName, clientsContent);
            }
            this.sendResp(resp, out);

            String wouldsSomeContent = in.readLine();
            System.out.println(String.format("%s: %s" , clientsName, wouldsSomeContent));

            switch (wouldsSomeContent) {
                case ("yes"):
                    resp = String.format("Enjoy your %s, please!", clientsContent);
                    break;
                default:
                    resp = "Ok, thank you, have a good time!";
            }
            this.sendResp(resp, out);
        } catch ( IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Server2Main server = new Server2Main();
        server.startServer();
    }
}
