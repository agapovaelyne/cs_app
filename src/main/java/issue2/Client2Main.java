package issue2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client2Main implements Sendable {
    private static String myName = "Elyne";
    private static String myAge = "7";
    private static String wouldSomeContent = "yes";


    public void sendResp(String response, PrintWriter out) {
        out.println(response);
        System.out.println(String.format("%s: %s", myName, response));
    }

    public void start() {
        try (Socket clientSocket = new Socket("netology.homework", 8080);
             PrintWriter out = new
                     PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new
                     InputStreamReader(clientSocket.getInputStream()))) {
            String resp = in.readLine();
            System.out.println(String.format("Server: %s", resp));
            this.sendResp(myName, out);

            resp = in.readLine();
            System.out.println(String.format("Server: %s", resp));
            this.sendResp(myAge, out);

            resp = in.readLine();
            System.out.println(String.format("Server: %s", resp));
            this.sendResp(wouldSomeContent, out);
            resp = in.readLine();
            System.out.println(String.format("Server: %s", resp));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Client2Main client = new Client2Main();
        client.start();
    }
}
