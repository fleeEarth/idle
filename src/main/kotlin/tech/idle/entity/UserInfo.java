package tech.idle.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String uid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 用户注册时间
     */
    private Timestamp regTime;

    /**
     * 加密密钥
     */
    private String publicKey;

    /**
     * 是否已经登录
     */
    private Boolean hasLogin = false;

    /**
     * 是否通过验证码校验
     */
    private Boolean hasCheckCaptcha = false;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getRegTime() {
        return regTime;
    }

    public void setRegTime(Timestamp regTime) {
        this.regTime = regTime;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public Boolean getHasLogin() {
        return hasLogin;
    }

    public void setHasLogin(Boolean hasLogin) {
        this.hasLogin = hasLogin;
    }

    public Boolean getHasCheckCaptcha() {
        return hasCheckCaptcha;
    }

    public void setHasCheckCaptcha(Boolean hasCheckCaptcha) {
        this.hasCheckCaptcha = hasCheckCaptcha;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", regTime=" + regTime +
                ", publicKey='" + publicKey + '\'' +
                ", hasLogin=" + hasLogin +
                ", hasCheckCaptcha=" + hasCheckCaptcha +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return this.uid.equals(userInfo.uid);
    }


}