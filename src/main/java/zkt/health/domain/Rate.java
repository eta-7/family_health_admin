package zkt.health.domain;

import javax.persistence.*;

@Entity
@Table(name = "TB_RATE")
public class Rate {
    @Id
    //设置主键并且设置主键为自增
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RATE_ID")
    private Integer rateId;
    @Column(name = "RATE_DATE")
    private String rateDate;
    @Column(name = "RATE_NUM")
    private Integer rateNum;
    @Column(name = "RATE_CONDITION")
    private String rateCondition;
    @Column(name = "RATE_USER_ID")
    private Integer userId;

    public Integer getRateId() {
        return rateId;
    }

    public void setRateId(Integer rateId) {
        this.rateId = rateId;
    }

    public String getRateDate() {
        return rateDate;
    }

    public void setRateDate(String rateDate) {
        this.rateDate = rateDate;
    }

    public Integer getRateNum() {
        return rateNum;
    }

    public void setRateNum(Integer rateNum) {
        this.rateNum = rateNum;
    }

    public String getRateCondition() {
        return rateCondition;
    }

    public void setRateCondition(String rateCondition) {
        this.rateCondition = rateCondition;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
