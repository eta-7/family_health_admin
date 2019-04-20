package zkt.health.domain;

import javax.persistence.*;

@Entity
@Table(name = "TB_USER")
public class User {
    @Id
    //设置主键并且设置主键为自增
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Integer id;//用户编号
    @Column(name = "USER_USERNAME")
    private String username;//用户名
    @Column(name = "USER_PASSWORD")
    private String password;
    @Column(name = "USER_GENDER")//密码
    private String gender;  //性别
    @Column(name = "USER_TEL")
    private String tel;  //联系方式
    @Column(name = "USER_EMAIL")
    private String email; //电子邮箱

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
