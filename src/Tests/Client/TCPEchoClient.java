package Tests.Client;

import java.net.*;
import java.io.*;
import java.util.*;

public class TCPEchoClient
{
    private static InetAddress host;

    public static void main(String[] args)
    {
        try
        {
            host = InetAddress.getLocalHost();
        }
        catch(UnknownHostException UHe)
        {
            System.out.println("Unable to find host!");
            System.exit(1);
        }
        accesServer();
    }

    public static void accesServer()
    {
        Socket link = null;

        try
        {
            link = new Socket(host,1234);

            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(),true);

            //Userentry scanner
            Scanner userEntry = new Scanner(System.in);

            String message, response;
            do
            {
                System.out.println("Enter message: ");
                message = userEntry.nextLine();
                output.println(message);
                response = input.nextLine();
                System.out.println("\nServer response: " + response);
            } while(!message.equals("***CLOSE***"));

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
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}
