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
    //// TODO: 2016-10-11 much more...
    public Pdu readPdu() throws EOFException, IOException {
/*
        byte operationNumber = pduScanner.nextByte();
        switch (operationNumber){
            case 4: byte[] sListMessage = new byte["size of pdu"];
                PduSlist pduSlist = new PduSlist(sListMessage);
            case 10: byte[] inMess= new byte["size of pdu"];
                PduMess pduMess = new PduMess(inMess);
                break;
            case 11: //we recieved a quit message.
                //terminate connection.
                break;
            case 16: byte[] pJoinMessage = new byte["size of pdu"];
                PduPjoin pduPjoin = new PduPjoin(pJoinMessage);
                break;
            case 17: byte[] pLeaveMessage = new byte["size of pdu"];
                PduPleave pduPleave= new PduPleave(pLeaveMessage);
                break;
            case 19: byte[] participantsMessage = new byte["size of pdu"];
                PduParticipants pduParticipants = new
                        PduParticipants(participantsMessage);
                break;
            default:
                System.out.println("operation nr: "
                        + operationNumber +
                        "is not a valid operation code!");
        }*/
        return null;
    }
}

