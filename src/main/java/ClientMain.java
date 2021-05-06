import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("localhost", 8080);
             PrintWriter out = new
                     PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new
                     InputStreamReader(clientSocket.getInputStream()))) {
            String myName = "Elyne";
            out.println(myName);
            String resp = in.readLine();
            System.out.println(resp);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
