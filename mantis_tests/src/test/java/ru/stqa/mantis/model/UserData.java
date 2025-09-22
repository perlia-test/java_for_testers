package ru.stqa.mantis.model;

public record UserData (String username, String realName, String email, String password){
    public UserData (){
        this ("","","","");
    }

    public UserData withUsername (String username) {
        return new UserData(username, this.realName, this.email, this.password);
    }

    public UserData withRealName (String realName) {
        return new UserData(this.username, realName, this.email, this.password);
    }

    public UserData withEmail (String email) {
        return new UserData(this.username, this.realName, email, this.password);
    }

    public UserData withPassword (String password) {
        return new UserData(this.username, this.realName, this.email, password);
    }
}
