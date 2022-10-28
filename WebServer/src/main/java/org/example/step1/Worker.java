package org.example.step1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class Worker extends Thread {
    private final Socket socket;
    private final HttpRequest request;

    public Worker(Socket socket, HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }

    @Override
    public void run() {
        // Print request that we received.
        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();

        // To send response back to the client.
        try{
        PrintWriter output = new PrintWriter(socket.getOutputStream());

        // We are returning a simple web page now.
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();

        
// Java program to find all the
// prime numbers from 1 to N
                // Declaring the variables

                int N = 200000;
                int x, y, flg;
                int k = 0;
        
                // Printing display message
                System.out.println(
                    "All the Prime numbers within 1 and " + N
                    + " are:");
        
                // Using for loop for traversing all
                // the numbers from 1 to N
                for (x = 1; x <= N; x++) {
        
                    // Omit 0 and 1 as they are
                    // neither prime nor composite
                    if (x == 1 || x == 0)
                        continue;
        
                    // Using flag variable to check
                    // if x is prime or not
                    flg = 1;
        
                    for (y = 2; y <= x / 2; ++y) {
                        if (x % y == 0) {
                            flg = 0;
                            break;
                        }
                    }
        
                    // If flag is 1 then x is prime but
                    // if flag is 0 then x is not prime
                    if (flg == 1)
                        k = k+1;
                }
                output.println(k + " ");
        
        

        output.println("<html>");
        output.println("<head><title>Hello</title></head>");
        output.println("<body><p>Hello, world!</p></body>");
        output.println("</html>");
        output.flush();

        socket.close();
    }catch (IOException io){

    }

    }
}
