package zkt.health.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zkt.health.basic.result.BizResult;
import zkt.health.constant.HttpRspCode;
import zkt.health.dao.FatDao;
import zkt.health.dao.FatDao;
import zkt.health.domain.Fat;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class FatController {
    private Logger logger = LoggerFactory.getLogger(FatController.class);

    @Autowired
    private FatDao fatDao;

    @ResponseBody
    @RequestMapping(value = "/fat/add", method = {RequestMethod.POST})
    public BizResult addFat(@RequestBody Fat fat) {
//    public BizResult addFat(@RequestBody String fat) {
//        整理根据用户和开始时间作为唯一标识吧
        List<Fat> fats = fatDao.findByFatDateAndUseId(fat.getFatDate(), fat.getUseId());
        if(fats.size()>0){
            BizResult success = BizResult.success();
            success.setRetCode(HttpRspCode.DATA_EXIST.getCode());
            success.setMessage(HttpRspCode.DATA_EXIST.getDesc());
            return success;
        }
        fatDao.save(fat);
        BizResult success = BizResult.success();
        success.setRetCode(HttpRspCode.SUCCESS.getCode());
        return success;
    }

    @ResponseBody
    @RequestMapping(value = "/fat/delete", method = {RequestMethod.POST})
    public BizResult delteFat(@RequestBody Map<String, Object> reqBody) {
        BizResult bizResult = new BizResult();
        if (!reqBody.containsKey("fatDate;") ||
                null == reqBody.get("fatDate;")) {
            bizResult.setMessage(HttpRspCode.REQ_PARAM_IS_NULL.getDesc());
            bizResult.setRetCode(HttpRspCode.REQ_PARAM_IS_NULL.getCode());
        }
        fatDao.deleteByFatDateAndUseId((String) reqBody.get("fatDate"), (Integer) reqBody.get("useId;"));
        bizResult.setRetCode(HttpRspCode.SUCCESS.getCode());
        bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
        return new BizResult();
    }

    @ResponseBody
    @RequestMapping(value = "/fat/update", method = {RequestMethod.POST})
    public BizResult updateFat(@RequestBody Fat fat) {
        //根据startTime和userID确定唯一，在进行更新
        List<Fat> fats = fatDao.findByFatDateAndUseId(fat.getFatDate(), fat.getUseId());
        if(fats.size()==0){
            BizResult bizResult = new BizResult();
            bizResult.setMessage(HttpRspCode.DATA_NOT_EXIST.getDesc());
            bizResult.setRetCode(HttpRspCode.DATA_NOT_EXIST.getCode());
            return bizResult;
        }
        fat.setFatId(fats.get(0).getFatId());
        fatDao.save(fat);
        BizResult success = BizResult.success();
        success.setRetCode(HttpRspCode.SUCCESS.getCode());
        return success;
    }

    @ResponseBody
    @RequestMapping(value = "/fat/query", method = {RequestMethod.POST})
    public BizResult queryFat(@RequestBody Map<String, Object> reqBody) {
        Set<String> keySet = reqBody.keySet();
        //如果什么都没有传，默认查询全部
        if(keySet.size()==0){
            List<Fat> all = fatDao.findAll();
            BizResult bizResult = new BizResult();
            bizResult.setData(HttpRspCode.SUCCESS.getCode());
            bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
            bizResult.setData(all);
        }
        if(reqBody.containsKey("useId")
        &&keySet.size()==1){
            List<Fat> all = fatDao.findByUseId((Integer) reqBody.get("useId"));
            BizResult bizResult = new BizResult();
            bizResult.setData(HttpRspCode.SUCCESS.getCode());
            bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
            bizResult.setData(all);
        }
        List<Fat> all = fatDao.findByFatDateAndUseId((String) reqBody.get("fatDate"),(Integer) reqBody.get("useId"));
        BizResult bizResult = new BizResult();
        bizResult.setData(HttpRspCode.SUCCESS.getCode());
        bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
        bizResult.setData(all);
        return bizResult;
    }

}
