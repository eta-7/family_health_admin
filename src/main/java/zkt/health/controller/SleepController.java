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
import zkt.health.dao.SleepDao;
import zkt.health.domain.Sleep;

import java.util.List;
import java.util.Map;

@Controller
public class SleepController {
    private Logger logger = LoggerFactory.getLogger(SleepController.class);

    @Autowired
    private SleepDao sleepDao;

    @ResponseBody
    @RequestMapping(value = "/sleep/add", method = {RequestMethod.POST})
    public BizResult addSleep(@RequestBody Sleep sleep) {
        //整理根据用户和开始时间作为唯一标识吧
        List<Sleep> byStartTimeAndUserId = sleepDao.findByStartTimeAndUserId(sleep.getStartTime(), sleep.getUserId());
        if(byStartTimeAndUserId.size()>0){
            BizResult success = BizResult.success();
            success.setRetCode(HttpRspCode.DATA_EXIST.getCode());
            success.setMessage(HttpRspCode.DATA_EXIST.getDesc());
            return success;
        }
        sleepDao.save(sleep);
        BizResult success = BizResult.success();
        success.setRetCode(HttpRspCode.SUCCESS.getCode());
        return success;
    }

    @ResponseBody
    @RequestMapping(value = "/sleep/delete", method = {RequestMethod.POST})
    public BizResult delteSleep(@RequestBody Map<String, Object> reqBody) {
        BizResult bizResult = new BizResult();
        if (!reqBody.containsKey("startTime") ||
                null == reqBody.get("startTime")) {
            bizResult.setMessage(HttpRspCode.REQ_PARAM_IS_NULL.getDesc());
            bizResult.setRetCode(HttpRspCode.REQ_PARAM_IS_NULL.getCode());
        }
        sleepDao.deleteAllByStartTime((String) reqBody.get("startTime"));
        bizResult.setRetCode(HttpRspCode.SUCCESS.getCode());
        bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
        return new BizResult();
    }

    @ResponseBody
    @RequestMapping(value = "/sleep/update", method = {RequestMethod.POST})
    public BizResult updateSleep(@RequestBody Sleep sleep) {
        //根据startTime和userID确定唯一，在进行更新
        List<Sleep> sleeps = sleepDao.findByStartTimeAndUserId(sleep.getStartTime(), sleep.getUserId());
        if(sleeps.size()==0){
            BizResult bizResult = new BizResult();
            bizResult.setMessage(HttpRspCode.DATA_NOT_EXIST.getDesc());
            bizResult.setRetCode(HttpRspCode.DATA_NOT_EXIST.getCode());
            return bizResult;
        }
        sleep.setSleepId(sleeps.get(0).getSleepId());
        sleepDao.save(sleep);
        BizResult success = BizResult.success();
        success.setRetCode(HttpRspCode.SUCCESS.getCode());
        return success;
    }

    @ResponseBody
    @RequestMapping(value = "/sleep/query", method = {RequestMethod.POST})
    public BizResult querySleep(@RequestBody Map<String, Object> reqBody) {
        BizResult bizResult = new BizResult();
        if (!reqBody.containsKey("startTime") ||
                null == reqBody.get("startTime")) {
            bizResult.setMessage(HttpRspCode.REQ_PARAM_IS_NULL.getDesc());
            bizResult.setRetCode(HttpRspCode.REQ_PARAM_IS_NULL.getCode());
        }
        List<Sleep> startTime = sleepDao.findByStartTime((String) reqBody.get("startTime"));
        BizResult success = BizResult.success();
        success.setRetCode(HttpRspCode.SUCCESS.getCode());
        success.setData(startTime);
        return success;
    }

}
