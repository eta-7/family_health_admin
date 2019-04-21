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
import zkt.health.dao.SugarDao;
import zkt.health.domain.Sugar;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class SugarController {
    private Logger logger = LoggerFactory.getLogger(SugarController.class);

    @Autowired
    private SugarDao sugarDao;

    @ResponseBody
    @RequestMapping(value = "/sugar/add", method = {RequestMethod.POST})
    public BizResult addSugar(@RequestBody Sugar sugar) {
        //整理根据用户和开始时间作为唯一标识吧
        List<Sugar> sugars = sugarDao.findBySugarDateAndUserId(sugar.getSugarDate(), sugar.getUserId());
        if(sugars.size()>0){
            BizResult success = BizResult.success();
            success.setRetCode(HttpRspCode.DATA_EXIST.getCode());
            success.setMessage(HttpRspCode.DATA_EXIST.getDesc());
            return success;
        }
        sugarDao.save(sugar);
        BizResult success = BizResult.success();
        success.setRetCode(HttpRspCode.SUCCESS.getCode());
        return success;
    }

    @ResponseBody
    @RequestMapping(value = "/sugar/delete", method = {RequestMethod.POST})
    public BizResult delteSugar(@RequestBody Map<String, Object> reqBody) {
        BizResult bizResult = new BizResult();
        if (!reqBody.containsKey("sugarDate;") ||
                null == reqBody.get("sugarDate;")) {
            bizResult.setMessage(HttpRspCode.REQ_PARAM_IS_NULL.getDesc());
            bizResult.setRetCode(HttpRspCode.REQ_PARAM_IS_NULL.getCode());
        }
        sugarDao.deleteBySugarDateAndUserId((String) reqBody.get("sugarDate"), (Integer) reqBody.get("useId;"));
        bizResult.setRetCode(HttpRspCode.SUCCESS.getCode());
        bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
        return new BizResult();
    }

    @ResponseBody
    @RequestMapping(value = "/sugar/update", method = {RequestMethod.POST})
    public BizResult updateSugar(@RequestBody Sugar sugar) {
        //根据startTime和userID确定唯一，在进行更新
        List<Sugar> sugars = sugarDao.findBySugarDateAndUserId(sugar.getSugarDate(), sugar.getUserId());
        if(sugars.size()==0){
            BizResult bizResult = new BizResult();
            bizResult.setMessage(HttpRspCode.DATA_NOT_EXIST.getDesc());
            bizResult.setRetCode(HttpRspCode.DATA_NOT_EXIST.getCode());
            return bizResult;
        }
        sugar.setSugarId(sugars.get(0).getSugarId());
        sugarDao.save(sugar);
        BizResult success = BizResult.success();
        success.setRetCode(HttpRspCode.SUCCESS.getCode());
        return success;
    }

    @ResponseBody
    @RequestMapping(value = "/sugar/query", method = {RequestMethod.POST})
    public BizResult querySugar(@RequestBody Map<String, Object> reqBody) {
        Set<String> keySet = reqBody.keySet();
        //如果什么都没有传，默认查询全部
        if(keySet.size()==0){
            List<Sugar> all = sugarDao.findAll();
            BizResult bizResult = new BizResult();
            bizResult.setData(HttpRspCode.SUCCESS.getCode());
            bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
            bizResult.setData(all);
        }
        if(reqBody.containsKey("useId")
        &&keySet.size()==1){
            List<Sugar> all = sugarDao.findByUserId((Integer) reqBody.get("useId"));
            BizResult bizResult = new BizResult();
            bizResult.setData(HttpRspCode.SUCCESS.getCode());
            bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
            bizResult.setData(all);
        }
        List<Sugar> all = sugarDao.findBySugarDateAndUserId((String) reqBody.get("sugarDate"),(Integer) reqBody.get("useId"));
        BizResult bizResult = new BizResult();
        bizResult.setData(HttpRspCode.SUCCESS.getCode());
        bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
        bizResult.setData(all);
        return bizResult;
    }

}
