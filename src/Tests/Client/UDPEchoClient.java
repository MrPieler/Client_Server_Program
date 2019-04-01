package Tests.Client;

import java.io.*;
import java.net.*;
import java.util.*;

public class UDPEchoClient
{
    private static InetAddress host;
    private static DatagramSocket datagramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static byte[] buffer;

    public static void main(String[] args)
    {
        try
        {
            host = InetAddress.getLocalHost();
        }
        catch (UnknownHostException UHe)
        {
            System.out.println("Host ID not found!");
            System.exit(1);
        }
        accesServer();
    }
    private static void accesServer()
    {
        try
        {
            datagramSocket = new DatagramSocket();
            Scanner userEntry = new Scanner(System.in);

            String message = "", response = "";
            do
            {
                System.out.print("Enter message: ");
                message = userEntry.nextLine();

                if(!message.equals("***CLOSE***"))
                {
                    outPacket = new DatagramPacket(message.getBytes(), message.length(),host,12345);

                    datagramSocket.send(outPacket);

                    buffer = new byte[1024];
                    inPacket = new DatagramPacket(buffer,buffer.length);
                    datagramSocket.receive(inPacket);

                    response = new String(inPacket.getData(),
                            0,inPacket.getLength());

                    System.out.println("\nSERVER > " + response);
                }
            } while (!message.equals("***CLOSE***"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.out.println("\nClosing connection...");
            datagramSocket.close();
        }
    }
}
