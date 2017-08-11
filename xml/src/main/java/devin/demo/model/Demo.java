package devin.demo.model;

public class Demo {
    private Long id;

    private String loginName;

    private String loginPwd;

    private String nickName;

    private String realName;

    private String gender;

    private String phone;

    private String address;

    public Demo(Long id, String loginName, String loginPwd, String nickName, String realName, String gender, String phone, String address) {
        this.id = id;
        this.loginName = loginName;
        this.loginPwd = loginPwd;
        this.nickName = nickName;
        this.realName = realName;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
    }

    public Demo() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}