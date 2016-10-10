
import java.util.Arrays;

/**
 * Created by kristoffer on 2016-10-10.
 *
 * TODO test the class!!!
 */
public class PduPjoin extends Pdu{

    byte op;
    byte identityLenght;
    short numberOfPadding = 0;
    String timeStamp;
    String clientIdentity;

    public PduPjoin(byte[] pJoinMessage){

        for (int i = 0; i < pJoinMessage.length; i++){
            if(i == 0){
                op = pJoinMessage[i];
            }
            else if(i == 1){
                identityLenght = pJoinMessage[i];
            }
            else if(i == 2 || i == 3){
                numberOfPadding += 1;
            }
            else if(i == 4){
                timeStamp =  Arrays.copyOfRange(pJoinMessage,
                        4,7).toString();
            }
            else if(i == 8){
                clientIdentity = Arrays.copyOfRange(pJoinMessage,8,
                        (identityLenght + 8)).toString().trim();
            }
        }
        String info = clientIdentity + "has joined the chatt at " +
                "time: " + timeStamp;
        System.out.println(info);
    }
}
