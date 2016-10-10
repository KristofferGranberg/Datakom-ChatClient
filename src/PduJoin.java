import java.io.UnsupportedEncodingException;

/**
 * Created by kristoffer & Viyan on 2016-09-28.
 */
public class PduJoin extends Pdu{

    private final byte op = 12;

    public PduJoin(String identity) throws UnsupportedEncodingException {
        super();
        /**
         * creates the header of the pdu
         */
        sequenceBuilder.appendInt(op);

        sequenceBuilder.appendInt(new Integer(identity.getBytes("UTF-8")
                .length).byteValue());
        sequenceBuilder.pad();
        //header = sequenceBuilder.toByteArray();

        /**
         * creates the body of the pdu.
         */
        //sequenceBuilder = new ByteSequenceBuilder();  //empties
        // previous info, not useful anymore 10/10.

        sequenceBuilder.append(identity.getBytes("UTF-8"));
        sequenceBuilder.pad();

        body = sequenceBuilder.toByteArray();

    }
}
