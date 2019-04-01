package Tests.Server;

import java.io.*;
import java.net.*;

public class UDPEchoServer
{
    private static DatagramSocket datagramSocket;
    private static DatagramPacket inPacket, outPacket;
    private static byte[] buffer;

    public static void main(String[] args)
    {
        System.out.println("Opening port...");
        try
        {
            datagramSocket = new DatagramSocket(12345);
        }
        catch (SocketException Se)
        {
            System.out.println("Unable to open port!");
            System.exit(1);
        }
            handleClient();
    }

    private static void handleClient()
    {
        try
        {
            String messageIn, messageOut;
            int numMessages = 0;
            InetAddress clientAddress = null;
            int clientPort;

            do
            {
                buffer = new byte[256];
                inPacket = new DatagramPacket(buffer,buffer.length);

                datagramSocket.receive(inPacket);
                clientAddress = inPacket.getAddress();
                clientPort = inPacket.getPort();

                messageIn = new String(inPacket.getData(),
                        0,inPacket.getLength());

                System.out.println("Message recieved.");
                numMessages++;
                messageOut = "Message " + numMessages +
                        ": " + messageIn.toUpperCase();
                outPacket = new DatagramPacket(messageOut.getBytes(),
                        messageOut.length(), clientAddress,clientPort);

                datagramSocket.send(outPacket);

            } while(true);
        }
        catch (IOException IOe)
        {
            IOe.printStackTrace();
        }
        finally
        {
            System.out.println("\nClosing connection...");
            datagramSocket.close();
        }
    }
}
