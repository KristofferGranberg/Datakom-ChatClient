/**
 * Created by kristoffer on 2016-10-11.
 */
//// TODO: 2016-10-11 test the class, comments 
public class PduPleave extends PduPjoin{

    public PduPleave(byte[] byteArray){
        super(byteArray);
    }
    public void printLeaveMessage(){
        String info = getClientIdentity() + "has joined the chat at " +
                "time: " + getTimeStamp();
        System.out.println(info);
    }
}
