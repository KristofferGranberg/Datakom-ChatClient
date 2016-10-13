import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kristoffer on 2016-10-11.
 */

//// TODO: 2016-10-11 testing, remove old code.
public class PduSlist extends Pdu{

    private short nrOfServers;
    private List<Server> serverList;

    public PduSlist(byte[] byteArray){
        super();
        if (byteArray[0] != 4){
            throw new IllegalArgumentException("OP:code is wrong! " +
                    "corrupted message.");
        }
        serverList = new LinkedList<>();
        sequenceBuilder.append(byteArray);
        nrOfServers = findNrOfServers();
        findServers();
        printServerList(serverList);
    }

    private short findNrOfServers(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(2);
        byteBuffer.put(sequenceBuilder.toByteArray()[2]);
        byteBuffer.put(sequenceBuilder.toByteArray()[3]);
        return byteBuffer.getShort();
    }

    private void findServers(){

        ByteBuffer sListBuffer =ByteBuffer.allocate
                (sequenceBuilder.size());
        sListBuffer.put(sequenceBuilder.toByteArray());

        //removes the header
        for(int i = 0; i < 4;i++){
            sListBuffer.get();
        }

        for (int i = 0; i < nrOfServers; i++){
            int padTracking = 0;

            // finds the ip address
            int serverAddress = sListBuffer.getInt();
            padTracking = padTracking + 4;

            // finds the port nr.
            short serverPort = sListBuffer.getShort();
            padTracking = padTracking + 2;

            // gets the nr of clients the server has.
            byte nrOfServerClients = sListBuffer.get();
            padTracking++;

            // gets the server name length.
            byte serverNameLength = sListBuffer.get();
            padTracking++;

            // gets the server name
            byte[] serverNameArray = new byte[(int)serverNameLength];
            for(int j = 0; j < serverNameLength;j++){
                serverNameArray[j] = sListBuffer.get();
                padTracking++;
            }
            String serverName = new String(serverNameArray, Charset
                    .forName("UTF-8"));

            // creates a server object and adds it to the list.
            serverList.add(new Server(serverAddress,serverPort,
                    nrOfServerClients,serverNameLength,serverName));

            // removes the padding from the buffer after the server
            // name in the Pdu.
            while(padTracking%4 != 0){
                sListBuffer.get();
            }
        }

/* old code. not sure if i should get rid of it yet.

            // finds the ip address
            for(int j = 0; j <4;j++){
                byteBuffer.put(sequenceBuilder.toByteArray()[j]);
            }
            int serverAddress = byteBuffer.getInt();

            // finds the port nr.
            byteBuffer.put(sequenceBuilder.toByteArray()[8]);
            byteBuffer.put(sequenceBuilder.toByteArray()[9]);
            short serverPort = byteBuffer.getShort();

            //gets the nr of clients the server has.
            byte nrOfServerClients = sequenceBuilder.toByteArray()[10];

            //gets the server name length.
            byte serverNameLength = sequenceBuilder.toByteArray()[11];

            //gets the server name
            for(int k = 12; k < (12 + serverNameLength);k++){
                byteBuffer.put(sequenceBuilder.toByteArray()[k]);
            }
            String serverName = new String(byteBuffer.array(), Charset
                    .forName("UTF-8"));

            //creates a server object and adds it to the list.
            serverList.add(new Server(serverAddress,serverPort,
                    nrOfServerClients,serverNameLength,serverName));
        */
    }

    private void printServerList(List<Server> serverList){
        for (int i = 0; i < nrOfServers; i++){
            Server currentServer = serverList.get(i);

            System.out.println("Server nr: " + (i+1));
            System.out.println("Server Name: "+ currentServer
                    .getServerName());
            System.out.println("Server IP: "+ currentServer.getAddress());
            System.out.println("Server Port: "+currentServer
                    .getPort()+"\n");
        }
    }
}
