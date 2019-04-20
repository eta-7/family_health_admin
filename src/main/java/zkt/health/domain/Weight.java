package zkt.health.domain;

import javax.persistence.*;

@Entity
@Table(name = "TB_WEIGHT")
public class Weight {
    @Id
    //设置主键并且设置主键为自增
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "WEIGHT_ID")
    private Integer weightId;

    @Column(name = "WEIGHT_NUM")
    private Double weightNum;

    @Column(name = "WEIGHT_DATE")
    private String weightDate;

    @Column(name = "WEIGHT_USER_ID")
    private Integer userId;

    public Integer getWeightId() {
        return weightId;
    }

    public void setWeightId(Integer weightId) {
        this.weightId = weightId;
    }

    public Double getWeightNum() {
        return weightNum;
    }

    public void setWeightNum(Double weightNum) {
        this.weightNum = weightNum;
    }

    public String getWeightDate() {
        return weightDate;
    }

    public void setWeightDate(String weightDate) {
        this.weightDate = weightDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
