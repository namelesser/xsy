package cn.namelesser.xsy;

public class Account {
    private String account;
    private String password;
    private int treadNumb;
    private int aimTime;

    public Account(String account, String password, int treadNumb, int aimTime) {
        this.account = account;
        this.password = password;
        this.treadNumb = treadNumb;
        this.aimTime = aimTime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTreadNumb() {
        return treadNumb;
    }

    public void setTreadNumb(int treadNumb) {
        this.treadNumb = treadNumb;
    }

    public int getAimTime() {
        return aimTime;
    }

    public void setAimTime(int aimTime) {
        this.aimTime = aimTime;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", treadNumb=" + treadNumb +
                ", aimTime=" + aimTime +
                '}';
    }
}
