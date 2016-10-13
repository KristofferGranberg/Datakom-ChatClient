import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kristoffer on 2016-10-13.
 * TODO: test, javadoc!
 */
public class PduParticipants extends Pdu{

    private List<String> participantsList;
    private int nrOfIdentites;

    public PduParticipants(byte[] byteArray){
        super();
        participantsList = new ArrayList<>();
        sequenceBuilder.append(byteArray);
        nrOfIdentites = (int)getNrOfIdentities();
        getParticipants();
        printParticipantsList();
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
    private void printParticipantsList(){
        for (int i = 0; i < nrOfIdentites; i++) {
            System.out.println("Participant nr: "+ (i+1));
            System.out.println("Client name: "+ participantsList.get
                    (i));
        }
    }
}