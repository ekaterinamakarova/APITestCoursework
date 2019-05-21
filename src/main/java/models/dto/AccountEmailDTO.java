package models.dto;

public class AccountEmailDTO {

    private String email;

    public AccountEmailDTO(){
    }

    public AccountEmailDTO(String email){
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
