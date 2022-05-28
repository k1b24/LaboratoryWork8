package kib.lab8.client.utils;

import kib.lab8.common.abstractions.RequestInterface;
import kib.lab8.common.abstractions.ResponseInterface;
import kib.lab8.common.util.client_server_communication.Serializer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConnectionHandlerClient {

    private static final int RESPONSE_TIMER = 2000;
    private int serverPort;
    private final DatagramSocket datagramSocket;
    private final InetAddress serverAddress;
    private final Serializer serializer = new Serializer();

    public ConnectionHandlerClient(String address) throws UnknownHostException, SocketException {
        datagramSocket = new DatagramSocket();
        serverAddress = InetAddress.getByName(address);
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public synchronized ResponseInterface sendRequest(RequestInterface request) throws IOException, ClassNotFoundException {
        request.setClientInfo(InetAddress.getLocalHost().toString() + ":" + datagramSocket.getLocalPort());
        ByteBuffer byteBuffer = serializer.serializeRequest(request);
        byte[] bufferToSend = byteBuffer.array();
        DatagramPacket datagramPacket = new DatagramPacket(bufferToSend, bufferToSend.length, serverAddress, serverPort);
        datagramSocket.send(datagramPacket);
        datagramSocket.setSoTimeout(RESPONSE_TIMER);
        int byteBufSize = datagramSocket.getReceiveBufferSize();
        byte[] byteBuf = new byte[byteBufSize];
        DatagramPacket dpFromServer = new DatagramPacket(byteBuf, byteBuf.length);
        datagramSocket.receive(dpFromServer);
        byte[] bytesFromServer = dpFromServer.getData();
        return serializer.deserializeResponse(bytesFromServer);
    }

    public void closeConnection() {
        datagramSocket.close();
    }
}
