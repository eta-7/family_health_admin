package zkt.health.domain;

import javax.persistence.*;

/**
 * 血压情况
 */
@Entity
@Table(name = "TB_PRESSURE")
public class Pressure {
    @Id
    //设置主键并且设置主键为自增
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRESSURE_ID")
    private Integer pressureId;
    @Column(name = "PRESSURE_DATE")
    private String pressureDate;
    @Column(name = "PRESSURE_HIGH")
    private Integer pressureHigh;
    @Column(name = "PRESSURE_LOW")
    private Integer pressureLow;
    @Column(name = "PRESSURE_CONDITION")
    private String pressureCondition;

    public Integer getPressureId() {
        return pressureId;
    }

    public void setPressureId(Integer pressureId) {
        this.pressureId = pressureId;
    }

    public String getPressureDate() {
        return pressureDate;
    }

    public void setPressureDate(String pressureDate) {
        this.pressureDate = pressureDate;
    }

    public Integer getPressureHigh() {
        return pressureHigh;
    }

    public void setPressureHigh(Integer pressureHigh) {
        this.pressureHigh = pressureHigh;
    }

    public Integer getPressureLow() {
        return pressureLow;
    }

    public void setPressureLow(Integer pressureLow) {
        this.pressureLow = pressureLow;
    }

    public String getPressureCondition() {
        return pressureCondition;
    }

    public void setPressureCondition(String pressureCondition) {
        this.pressureCondition = pressureCondition;
    }
}
