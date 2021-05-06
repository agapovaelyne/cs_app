package issue2;

import java.io.PrintWriter;

public interface Sendable {
    void sendResp(String response, PrintWriter outPutStream);
}
