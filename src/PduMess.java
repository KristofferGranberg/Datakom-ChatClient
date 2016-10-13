import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by kristoffer & Viyan on 2016-09-28.
 */
//// TODO: 2016-10-11 test the class, time converting
public class PduMess extends Pdu {

    private final byte op = 10;

    public PduMess(String clientIdentity, String message) throws UnsupportedEncodingException {
        super();
        /**
         * Creates the header of the pdu.
         */
        sequenceBuilder.append(op);
        sequenceBuilder.pad();

        /* should be only 1 byte padding
        sequenceBuilder.append((byte)0);

        sequenceBuilder.append(new Integer(clientIdentity.getBytes("UTF-8")
                .length).byteValue());

        sequenceBuilder.append((byte)0);
        //header = sequenceBuilder.toByteArray();*/

        /**
         * creates the body of the pdu.
         */
        //sequenceBuilder = new ByteSequenceBuilder(); //clear the
        // previous data; not useful anymore.

        short messageLength = new Integer(message.getBytes("UTF-8").length)
                .shortValue();
        sequenceBuilder.appendShort(messageLength);
        sequenceBuilder.pad();

        //puts in timestamp, should be zero.
        sequenceBuilder.appendInt(0);

        //puts in our message.
        sequenceBuilder.append(message.getBytes("UTF-8"));
        sequenceBuilder.pad();

        //puts in our identity.
        sequenceBuilder.append(clientIdentity.getBytes("UTF-8"));
        sequenceBuilder.pad();

        body = sequenceBuilder.toByteArray();

        /**
         * create the checksum.
         */
        byte checksum = Checksum.computeChecksum(body);

        body[3] = checksum;
    }

    /**
     * Constructor for receiving a PDU message.
     * @param byteArray
     */
    public PduMess(byte[] byteArray){
        super();
        if(Checksum.computeChecksum(byteArray) != -1){
            throw new IllegalArgumentException("the checksum is not" +
                    " correct: message is corrupt!");
        }
        if(byteArray[1] != 0 || byteArray[6] != 0|| byteArray[7] != 0){
            throw new IllegalArgumentException("the padding is " +
                    "wrong: message corrupted");
        }
        sequenceBuilder.append(byteArray);
    }

    private byte getIdentityLength(){
        return sequenceBuilder.toByteArray()[2];
    }

    private short getMessageLength(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(2);
        byteBuffer.put(sequenceBuilder.toByteArray()[4]);
        byteBuffer.put(sequenceBuilder.toByteArray()[5]);
        return byteBuffer.getShort();
    }

    private int getUnixTime(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        for(int i = 8;i < 11;i++){
            byteBuffer.put(sequenceBuilder.toByteArray()[i]);
        }
        return byteBuffer.getInt();

    }

    private String getMessage(){
        int messageLength = getMessageLength();
        ByteBuffer byteBuffer = ByteBuffer.allocate
                (messageLength);
        for (int i = 12;i < (messageLength + 12);i++)
        byteBuffer.put(sequenceBuilder.toByteArray()[i]);
        return new String(byteBuffer.array(), Charset.forName
                ("UTF-8"));
        //last line is from http://stackoverflow.com/questions/5049524/is-java-utf-8-charset-exception-possible
        //seems that it should work. could just ignore exception
        //since utf 8 should always be supported in the protocol.
    }

    private String getClientId(){
        int clientIdLength = getIdentityLength();
        int startOfLoop = getMessageLength() + 12;

        //makes sure the loop dosent start at the padding of message.
        while(!dividableByFour(startOfLoop)){
            startOfLoop = startOfLoop + 1;
        }

        ByteBuffer byteBuffer = ByteBuffer.allocate
                (clientIdLength);
        for (int i = startOfLoop;i < (startOfLoop + clientIdLength);i++)
            byteBuffer.put(sequenceBuilder.toByteArray()[i]);
        return new String(byteBuffer.array(), Charset.forName
                ("UTF-8"));
    }

    //not sure about modulus operator...
    private boolean dividableByFour(int value){
        return (value%4 == 0);
    }

    public void printMessage(){
        String message = getMessage();
        String client = getClientId();
        //TODO convert the time stamp to correct version.
        int timeStamp = getUnixTime();

        System.out.println(client + "wrote:");
        System.out.println(message);
        System.out.println("at "+ timeStamp);
    }
}