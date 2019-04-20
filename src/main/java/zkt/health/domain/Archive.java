package zkt.health.domain;

import javax.persistence.*;

/**
 *  档案
 */
@Entity
@Table(name = "TB_ARCHIVE")
public class Archive {
    @Id
    //设置主键并且设置主键为自增
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARCHIVE_ID")
    private Integer infoId;    //信息id
    @Column(name = "ARCHIVE_BIRTHDAY")
    private String birthday;    //出生年月
    @Column(name = "ARCHIVE_HEIGHT")
    private Integer height;    //身高
    @Column(name = "ARCHIVE_WEIGHT")
    private Double weight;    //体重
    @Column(name = "ARCHIVE_BLOOD")
    private String blood;      //血型
    @Column(name = "ARCHIVE_WAIST_LINE")
    private Integer waistline;   //腰围
    @Column(name = "ARCHIVE_HIP")
    private Integer hip;       //臀围
    @Column(name = "ARCHIVE_CURRENT_MEDICINE")
    private String currentMedicine;   //当前用药
    @Column(name = "ARCHIVE_INTOLERANCE")
    private String intolerance;      //过敏反应
    @Column(name = "ARCHIVE_PAST_ILLNESS")
    private String pastIllness;      //既往病史
    @Column(name = "ARCHIVE_FAMILY_ILLNESS")
    private String familyIllness;     //家族病史
    @Column(name = "ARCHIVE_DISABILITY")
    private String disability;        //残疾状况
    @Column(name = "ARCHIVE_MEAT_VEGETABLE")
    private String meatVegetable;      //荤素偏号
    @Column(name = "ARCHIVE_TASTE_PREFERENCE")
    private String tastePreference;    //口味偏号
    @Column(name = "ARCHIVE_MEAL_RULE")
    private String mealRule;           //三餐规律
    @Column(name = "ARCHIVE_CIGRAETTE")
    private String cigarette;          //烟酒情况
    @Column(name = "ARCHIVE_WINE_CONDITION")
    private String wineCondition;      //饮酒情况
    @Column(name = "ARCHIVE_USER_ID")
    private Integer userId;            //用户id，是外键

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public Integer getWaistline() {
        return waistline;
    }

    public void setWaistline(Integer waistline) {
        this.waistline = waistline;
    }

    public Integer getHip() {
        return hip;
    }

    public void setHip(Integer hip) {
        this.hip = hip;
    }

    public String getCurrentMedicine() {
        return currentMedicine;
    }

    public void setCurrentMedicine(String currentMedicine) {
        this.currentMedicine = currentMedicine;
    }

    public String getIntolerance() {
        return intolerance;
    }

    public void setIntolerance(String intolerance) {
        this.intolerance = intolerance;
    }

    public String getPastIllness() {
        return pastIllness;
    }

    public void setPastIllness(String pastIllness) {
        this.pastIllness = pastIllness;
    }

    public String getFamilyIllness() {
        return familyIllness;
    }

    public void setFamilyIllness(String familyIllness) {
        this.familyIllness = familyIllness;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public String getMeatVegetable() {
        return meatVegetable;
    }

    public void setMeatVegetable(String meatVegetable) {
        this.meatVegetable = meatVegetable;
    }

    public String getTastePreference() {
        return tastePreference;
    }

    public void setTastePreference(String tastePreference) {
        this.tastePreference = tastePreference;
    }

    public String getMealRule() {
        return mealRule;
    }

    public void setMealRule(String mealRule) {
        this.mealRule = mealRule;
    }

    public String getCigarette() {
        return cigarette;
    }

    public void setCigarette(String cigarette) {
        this.cigarette = cigarette;
    }

    public String getWineCondition() {
        return wineCondition;
    }

    public void setWineCondition(String wineCondition) {
        this.wineCondition = wineCondition;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
