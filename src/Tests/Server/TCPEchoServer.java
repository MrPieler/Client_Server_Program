package Tests.Server;

import java.net.*;
import java.io.*;
import java.util.*;

public class TCPEchoServer
{
    private static ServerSocket serverSocket;

    public static void main(String[] args)
    {
        System.out.println("Opening port...\n");
        try
        {
            serverSocket = new ServerSocket(1234);
        }
        catch(IOException IOe)
        {
            System.out.println("Unable to attach to port");
            System.exit(1);
        }
        do
        {
            handleClient();
        } while(true);
    }

    private static void handleClient()
    {
        Socket link = null;

        try
        {
            link = serverSocket.accept();

            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(),true);
            int numMessages = 0;
            String message = input.nextLine();

            while(!message.equals("***CLOSE***"))
            {
                System.out.println("Message recieved.");
                numMessages++;
                output.println("Message " + numMessages + ": " + message);
                message = input.nextLine();
            }
            output.println(numMessages + " messages recieved.");
        }
        catch(IOException IOe)
        {
            IOe.printStackTrace();
        }
        finally
        {
            try
            {
                System.out.println("\nClosing connection...");
                link.close();
            }
            catch (IOException IOe)
            {
                System.out.println("Unnable to disconnect!");
                System.exit(1);
            }
        }
    }
}
