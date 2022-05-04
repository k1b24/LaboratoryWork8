package kib.lab8.server.utils;

import kib.lab8.common.abstractions.RequestInterface;
import kib.lab8.common.util.client_server_communication.Serializer;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.function.Supplier;

public class ByteBufferDeserializerSupplier implements Supplier<RequestInterface> {

    private final ByteBuffer buffer;

    public ByteBufferDeserializerSupplier(ByteBuffer buffer) {

        this.buffer = buffer;
    }
    @Override
    public RequestInterface get() {
        Serializer serializer = new Serializer();
        ((Buffer) buffer).flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        try {
            return serializer.deserializeRequest(bytes);
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
