/**
 * Created by kristoffer & Viyan on 2016-09-28.
 */
public abstract class Pdu {

    //protected byte[] header;
    protected byte[] body;
    protected ByteSequenceBuilder sequenceBuilder;

    public Pdu(){
        //header = new byte[4];
        sequenceBuilder = new ByteSequenceBuilder();
    }

}
