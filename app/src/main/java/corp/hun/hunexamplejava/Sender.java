package corp.hun.hunexamplejava;

/**
 * Created by Le Van Hung at 3/22/2022
 * at 15.36
 * HunExampleJava
 */

public class Sender {
    public void send(String msg)
    {
        System.out.println("Sending\t"  + msg );
        try {
            Thread.sleep(1000);
        }
        catch (Exception e) {
            System.out.println("Thread  interrupted.");
        }
        System.out.println("\n" + msg + "Sent");
    }
}

// Class for send a message using Threads
class ThreadedSend extends Thread
{
    private String msg;
    Sender  sender;

    // Receives a message object and a string
    // message to be sent
    ThreadedSend(String m,  Sender obj) {
        msg = m;
        sender = obj;
    }

    public void run() {
        // Only one thread can send a message
        // at a time.
        synchronized (sender) {
            // synchronizing the send object
            sender.send(msg);
        }
    }
}