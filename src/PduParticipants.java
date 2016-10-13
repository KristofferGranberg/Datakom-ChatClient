import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kristoffer on 2016-10-13.
 * TODO: fix some details with the print and test!
 */
public class PduParticipants extends Pdu{

    private List<String> participantsList;

    public PduParticipants(byte[] byteArray){
        super();
        participantsList = new ArrayList<>();
        sequenceBuilder.append(byteArray);
    }

    private byte getNrOfIdentities(){
        return sequenceBuilder.toByteArray()[1];
    }
    private short getLengthOfParticipants(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(2);
        byteBuffer.put(sequenceBuilder.toByteArray()[2]);
        byteBuffer.put(sequenceBuilder.toByteArray()[3]);
        return byteBuffer.getShort();
    }
    private void getParticipants(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(257);

        int i = 4;
        while (i < (getLengthOfParticipants() + 4)){
            if(sequenceBuilder.toByteArray()[i] == 0){
                 participantsList.add(new String (byteBuffer.array
                        (),Charset.forName("UTF-8")));
            }else{
                byteBuffer.put(sequenceBuilder.toByteArray()[i]);
            }
            i++;
        }
    }
    public void printParticipantsList(){
        for (String participant:participantsList) {
            System.out.println(participant);
        }
    }
}