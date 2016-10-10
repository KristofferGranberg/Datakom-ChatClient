import java.io.UnsupportedEncodingException;

/**
 * Created by kristoffer & Viyan on 2016-09-28.
 */
public class PduMess extends Pdu {

    private final byte op = 10;

    public PduMess(String clientIdentity, String message) throws UnsupportedEncodingException {

        /**
         * Creates the header of the pdu.
         */
        sequenceBuilder.append(op);
        // should be only 1 byte padding
        sequenceBuilder.append((byte)0);

        sequenceBuilder.append(new Integer(clientIdentity.getBytes("UTF-8")
                .length).byteValue());

        sequenceBuilder.append((byte)0);
        //header = sequenceBuilder.toByteArray();

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
}
