package kib.lab8.common.util.client_server_communication.requests;

import kib.lab8.common.abstractions.RequestInterface;

public class SignUpRequest implements RequestInterface {

    private String login;
    private String password;
    private String clientInfo;
    private long requestId;

    @Override
    public void setRequestId(long id) {
        requestId = id;
    }

    @Override
    public long getRequestId() {
        return requestId;
    }

    @Override
    public void setClientInfo(String clientInfoToSet) {
        this.clientInfo = clientInfoToSet;
    }

    @Override
    public String getClientInfo() {
        return clientInfo;
    }

    @Override
    public void setUserLogin(String loginToSet) {
        this.login = loginToSet;
    }

    @Override
    public String getUserLogin() {
        return login;
    }

    @Override
    public void setUserPassword(String passwordToSet) {
        this.password = passwordToSet;
    }

    @Override
    public String getUserPassword() {
        return password;
    }

    @Override
    public Class<?> getType() {
        return this.getClass();
    }
}
