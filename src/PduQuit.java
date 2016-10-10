/**
 * Created by kristoffer & Viyan on 2016-09-28.
 */
public class PduQuit extends Pdu {

    private final byte op = 11;

    public PduQuit(){
        super();
        sequenceBuilder.appendInt(op);
        sequenceBuilder.pad();
        body = sequenceBuilder.toByteArray();
     }
}
