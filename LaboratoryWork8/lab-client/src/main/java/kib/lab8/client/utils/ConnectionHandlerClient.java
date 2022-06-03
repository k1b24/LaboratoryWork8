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

public class ConnectionHandlerClient {

    private static final int RESPONSE_TIMER = 5000;
    private int serverPort;
    private final DatagramSocket datagramSocket;
    private final InetAddress serverAddress;
    private final Serializer serializer = new Serializer();
    private volatile long idCounter = 0;

    public ConnectionHandlerClient(String address) throws UnknownHostException, SocketException {
        datagramSocket = new DatagramSocket();
        datagramSocket.setSoTimeout(RESPONSE_TIMER);
        serverAddress = InetAddress.getByName(address);
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    private void send(RequestInterface request) throws IOException {
        ByteBuffer byteBuffer = serializer.serializeRequest(request);
        byte[] bufferToSend = byteBuffer.array();
        DatagramPacket datagramPacket = new DatagramPacket(bufferToSend, bufferToSend.length, serverAddress, serverPort);
        datagramSocket.send(datagramPacket);
    }

    private ResponseInterface recieve() throws IOException, ClassNotFoundException {
        int byteBufSize = datagramSocket.getReceiveBufferSize();
        byte[] byteBuf = new byte[byteBufSize];
        DatagramPacket dpFromServer = new DatagramPacket(byteBuf, byteBuf.length);
        datagramSocket.receive(dpFromServer);
        byte[] bytesFromServer = dpFromServer.getData();
        return serializer.deserializeResponse(bytesFromServer);
    }

    public synchronized ResponseInterface sendRequest(RequestInterface request) throws IOException, ClassNotFoundException, RequestResponseMismatchException {
        request.setRequestId(idCounter++);
        request.setClientInfo(InetAddress.getLocalHost().toString() + ":" + datagramSocket.getLocalPort());
        send(request);
        ResponseInterface response = recieve();
        if (response.getResponseId() != idCounter - 1) {
            for (int i = 0; i < 5; i++) {
                ResponseInterface newResponse = recieve();
                if (newResponse.getResponseId() == idCounter - 1) {
                    return newResponse;
                }
            }
        } else {
            return response;
        }
        idCounter++;
        throw new RequestResponseMismatchException();
    }

    public void closeConnection() {
        datagramSocket.close();
    }
}
