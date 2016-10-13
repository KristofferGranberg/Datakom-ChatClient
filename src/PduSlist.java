import java.nio.ByteBuffer;

/**
 * Created by kristoffer on 2016-10-11.
 */

//// TODO: 2016-10-11 implement the class and testing
public class PduSlist extends Pdu{

    short nrOfServers;

    public PduSlist(byte[] byteArray){
        super();
        if (byteArray[0] != 4){
            throw new IllegalArgumentException("OP:code is wrong! " +
                    "corrupted message.");
        }
        sequenceBuilder.append(byteArray);

    }

    private short getNrOfServers(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(2);
        byteBuffer.put(sequenceBuilder.toByteArray()[2]);
        byteBuffer.put(sequenceBuilder.toByteArray()[3]);
        return byteBuffer.getShort();
    }




}
