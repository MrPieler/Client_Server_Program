package Tests;
import java.net.*;
import java.util.Scanner;


public class IPGetter
{
    public static void main(String[] args)
    {
        try
        {
            Scanner input = new Scanner(System.in);
            System.out.println("Write hostname:");
            String host = input.nextLine();
            InetAddress address = InetAddress.getByName(host);
            System.out.println(address);
        }
        catch (UnknownHostException UHe)
        {
            UHe.printStackTrace();
        }
    }
}
