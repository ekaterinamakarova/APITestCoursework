package models.dto;

public class AccountBanDTO {

    private String id;
    private String login;
    private boolean isBanned;

    public AccountBanDTO(){
    }

    public AccountBanDTO(String id, String login, boolean isBanned){
        this.id=id;
        this.login=login;
        this.isBanned=isBanned;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }
}
