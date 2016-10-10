//package datakom.ht16.given;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class PDUInputStream {

    Scanner pduScanner;

    public PDUInputStream(InputStream inputStream) {
        pduScanner = new Scanner(inputStream);
    }

    /**
     * @return The next PDU in the stream.
     *
     * @throws java.io.EOFException If the stream closed without an error.
     * @throws java.io.IOException If the stream closed with an error.
     */
    public Pdu readPdu() throws EOFException, IOException {
/*
        while(pduScanner.hasNext()){
           byte operationNumber = pduScanner.nextByte();

            switch (operationNumber){
                case 4: PduSlist listMessage = new PduSlist();
                case 10: PduMess inMessage = new PduMess("not " +
                        "implemented yet!");
                    break;
                case 11: PduQuit quitMessage = new PduQuit("not yet" +
                        " implemented!");
                    break;
                case 16: PduPjoin joinMessage = new PduPjoin();
                    break;
                case 17: PduPleave leaveMessage = new PduPleave();
                    break;
                case 19: PduParticipants participantsMessage = new
                        PduParticipants;
                    break;
                default:
                    System.out.println("operation nr: "
                            + operationNumber +
                            "is not a valid operation code!");
            }
        }*/
        return null;
    }
}
