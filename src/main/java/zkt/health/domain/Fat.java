package zkt.health.domain;

import javax.persistence.*;

@Entity
@Table(name = "TB_FAT")
public class Fat {
    @Id
    //设置主键并且设置主键为自增
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FAT_ID")
    private Integer fatId;
    @Column(name = "FAT_NUM")
    private Double fatNum;
    @Column(name = "FAT_DATE")
    private String fatDate;
    @Column(name = "FAT_FAT_CONDITION")
    private String fatCondition;
    @Column(name = "FAT_USER_ID")
    private Integer useId;

    public Integer getFatId() {
        return fatId;
    }

    public void setFatId(Integer fatId) {
        this.fatId = fatId;
    }

    public Double getFatNum() {
        return fatNum;
    }

    public void setFatNum(Double fatNum) {
        this.fatNum = fatNum;
    }

    public String getFatDate() {
        return fatDate;
    }

    public void setFatDate(String fatDate) {
        this.fatDate = fatDate;
    }

    public String getFatCondition() {
        return fatCondition;
    }

    public void setFatCondition(String fatCondition) {
        this.fatCondition = fatCondition;
    }

    public Integer getUseId() {
        return useId;
    }

    public void setUseId(Integer useId) {
        this.useId = useId;
    }
}
