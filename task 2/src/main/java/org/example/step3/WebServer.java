package org.example.step3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Simple web server.
 */
public class WebServer {
    public static void main(String[] args) {
        // Port number for http request
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 8080;
        // The maximum queue length for incoming connection
        int queueLength = args.length > 2 ? Integer.parseInt(args[2]) : 50;;

        try (ServerSocket serverSocket = new ServerSocket(port, queueLength)) {
            System.out.println("Web Server is starting up, listening at port " + port + ".");
            System.out.println("You can access http://localhost:" + port + " now.");
            
            ThreadSafeQueue<Socket> queueS = new ThreadSafeQueue<>();
            ThreadSafeQueue<HttpRequest> queueR = new ThreadSafeQueue<>();

            for (int i = 0; i < 11; i++) {
                Worker worker = new Worker(queueS, queueR);
                worker.start();
            }


            while (true) {
                // Make the server socket wait for the next client request
                Socket socket = serverSocket.accept();

                System.out.println("Got connection!");

                // To read input from the client
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

                try {
                    // Get request
                    HttpRequest request = HttpRequest.parse(input);
                    queueS.add(socket);
                    queueR.add(request);

                    // Process request
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            System.out.println("Server has been shutdown!");
        }
    }
}
