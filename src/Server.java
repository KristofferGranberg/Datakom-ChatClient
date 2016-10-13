/**
 * Class that represents a server.
 * Created by kristoffer on 2016-10-13.
 */
public class Server {

    private int address;
    private int port;
    private int nrOfClients;
    private int serverNameLength;
    private String serverName;

    public Server(int address, int port, int nrOfClients,
                  int serverNameLength, String serverName){
        this.address = address;
        this.port = port;
        this.nrOfClients = nrOfClients;
        this.serverNameLength = serverNameLength;
        this.serverName = serverName;
    }

    public String getServerName() {
        return serverName;
    }

    public int getServerNameLength() {
        return serverNameLength;
    }

    public int getNrOfClients() {
        return nrOfClients;
    }

    public int getPort() {
        return port;
    }

    public int getAddress() {
        return address;
    }
}
