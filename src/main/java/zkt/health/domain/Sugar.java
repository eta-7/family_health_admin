package zkt.health.domain;

import javax.persistence.*;

@Entity
@Table(name = "TB_SUGAR")
public class Sugar {

    @Id
    //设置主键并且设置主键为自增
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SUGAR_ID")
    private Integer sugarId;
    @Column(name = "SUGAR_DATE")
    private String sugarDate;

    @Column(name = "SUGAR_MEAL")
    private String sugarMeal;

    @Column(name = "SUGAR_NUM")
    private Double sugarNum;

    @Column(name = "SUGAR_CONDITION")
    private String sugarCondition;

    @Column(name = "SUGAR_USER_ID")
    private Integer userId;

    public Integer getSugarId() {
        return sugarId;
    }

    public void setSugarId(Integer sugarId) {
        this.sugarId = sugarId;
    }

    public String getSugarDate() {
        return sugarDate;
    }

    public void setSugarDate(String sugarDate) {
        this.sugarDate = sugarDate;
    }

    public String getSugarMeal() {
        return sugarMeal;
    }

    public void setSugarMeal(String sugarMeal) {
        this.sugarMeal = sugarMeal;
    }

    public Double getSugarNum() {
        return sugarNum;
    }

    public void setSugarNum(Double sugarNum) {
        this.sugarNum = sugarNum;
    }

    public String getSugarCondition() {
        return sugarCondition;
    }

    public void setSugarCondition(String sugarCondition) {
        this.sugarCondition = sugarCondition;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
