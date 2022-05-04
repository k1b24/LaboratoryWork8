package kib.lab8.common.abstractions;

import java.io.Serializable;

public interface ResponseInterface extends Serializable {

    AbstractMessage getMessage();

    Class<?> getType();
}
