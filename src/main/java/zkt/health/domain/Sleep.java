package zkt.health.domain;

import javax.persistence.*;

@Entity
@Table(name = "TB_SLEEP")
public class Sleep {
    @Id
    //设置主键并且设置主键为自增
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SLEEP_ID")
    private Integer sleepId;

    @Column(name = "SLEEP_START_TIME")
    private String startTime;

    @Column(name = "SLEEP_END_TIME")
    private String endtTime;

    @Column(name = "SLEEP_TOTAL_TIME")
    private String totalTime;

    @Column(name = "SLEEP_CONDITION")
    private String sleepCondition;



    @Column(name = "SLEEP_USER_ID")
    private Integer userId;

    public Integer getSleepId() {
        return sleepId;
    }

    public void setSleepId(Integer sleepId) {
        this.sleepId = sleepId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndtTime() {
        return endtTime;
    }

    public void setEndtTime(String endtTime) {
        this.endtTime = endtTime;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getSleepCondition() {
        return sleepCondition;
    }

    public void setSleepCondition(String sleepCondition) {
        this.sleepCondition = sleepCondition;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
