/**
 * Class that implements the abstract class Pdu
 * creates an pdu operation called GetList.
 *
 * Created by kristoffer on 2016-09-28.
 */
public class GetList extends Pdu {

    private final byte op = 3;

    public GetList(){
        super();
        ByteSequenceBuilder getListSequence = new ByteSequenceBuilder();
        getListSequence.appendInt(op).pad();
        body = getListSequence.toByteArray();
    }
}
