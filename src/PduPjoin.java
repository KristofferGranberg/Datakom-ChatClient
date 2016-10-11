
import java.util.Arrays;

/**
 * Created by kristoffer on 2016-10-10.
 */
//// TODO: 2016-10-11 test the class, unix cenverter, does it extend pdu?
public class PduPjoin extends Pdu{

    private byte op;
    private byte identityLenght;
    private int unixTime;
    private String timeStamp;
    private String clientIdentity;

    public PduPjoin(byte[] pJoinMessage){

        for (int i = 0; i < pJoinMessage.length; i++){
            if(i == 0){
                op = pJoinMessage[i];
            }
            else if(i == 1){
                identityLenght = pJoinMessage[i];
            }
            else if(i == 2 || i == 3){
                if(pJoinMessage[2] != 0 || pJoinMessage[3] != 0){
                    throw new IllegalArgumentException("there is no" +
                            " padding or the padding is in the " +
                            "wrong place, corrupted data!");
                }
            }
            else if(i == 4){
                //TODO: check this with test.
                unixTime =  Integer.parseInt(Arrays.copyOfRange(pJoinMessage,
                        4,7).toString());
            }
            else if(i == 8){
                clientIdentity = Arrays.copyOfRange(pJoinMessage,8,
                        (identityLenght + 8)).toString().trim();
            }
        }
        timeStamp = convertUnixTime(unixTime);
    }
    //TODO: implement method.
    private String convertUnixTime(int unixTime){
        throw new UnsupportedOperationException("method not yet " +
                "implemented");
    }
    public void printJoinMessage(){
        String info = clientIdentity + "has joined the chat at " +
                "time: " + timeStamp;
        System.out.println(info);
    }
    public String getTimeStamp(){
        return timeStamp;
    }
    public String getClientIdentity(){
        return clientIdentity;
    }
}
