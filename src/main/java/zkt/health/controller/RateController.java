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
import zkt.health.dao.RateDao;
import zkt.health.domain.Rate;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class RateController {
    private Logger logger = LoggerFactory.getLogger(RateController.class);

    @Autowired
    private RateDao rateDao;

    @ResponseBody
    @RequestMapping(value = "/rate/add", method = {RequestMethod.POST})
    public BizResult addRate(@RequestBody Rate rate) {
        //整理根据用户和开始时间作为唯一标识吧
        List<Rate> rates = rateDao.findByRateDateAndUserId(rate.getRateDate(), rate.getUserId());
        if(rates.size()>0){
            BizResult success = BizResult.success();
            success.setRetCode(HttpRspCode.DATA_EXIST.getCode());
            success.setMessage(HttpRspCode.DATA_EXIST.getDesc());
            return success;
        }
        rateDao.save(rate);
        BizResult success = BizResult.success();
        success.setRetCode(HttpRspCode.SUCCESS.getCode());
        return success;
    }

    @ResponseBody
    @RequestMapping(value = "/rate/delete", method = {RequestMethod.POST})
    public BizResult delteRate(@RequestBody Map<String, Object> reqBody) {
        BizResult bizResult = new BizResult();
        if (!reqBody.containsKey("rateDate;") ||
                null == reqBody.get("rateDate;")) {
            bizResult.setMessage(HttpRspCode.REQ_PARAM_IS_NULL.getDesc());
            bizResult.setRetCode(HttpRspCode.REQ_PARAM_IS_NULL.getCode());
        }
        rateDao.deleteByRateDateAndUserId((String) reqBody.get("rateDate"), (Integer) reqBody.get("useId;"));
        bizResult.setRetCode(HttpRspCode.SUCCESS.getCode());
        bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
        return new BizResult();
    }

    @ResponseBody
    @RequestMapping(value = "/rate/update", method = {RequestMethod.POST})
    public BizResult updateRate(@RequestBody Rate rate) {
        //根据startTime和userID确定唯一，在进行更新
        List<Rate> rates = rateDao.findByRateDateAndUserId(rate.getRateDate(), rate.getUserId());
        if(rates.size()==0){
            BizResult bizResult = new BizResult();
            bizResult.setMessage(HttpRspCode.DATA_NOT_EXIST.getDesc());
            bizResult.setRetCode(HttpRspCode.DATA_NOT_EXIST.getCode());
            return bizResult;
        }
        rate.setRateId(rates.get(0).getRateId());
        rateDao.save(rate);
        BizResult success = BizResult.success();
        success.setRetCode(HttpRspCode.SUCCESS.getCode());
        return success;
    }

    @ResponseBody
    @RequestMapping(value = "/rate/query", method = {RequestMethod.POST})
    public BizResult queryRate(@RequestBody Map<String, Object> reqBody) {
        Set<String> keySet = reqBody.keySet();
        //如果什么都没有传，默认查询全部
        if(keySet.size()==0){
            List<Rate> all = rateDao.findAll();
            BizResult bizResult = new BizResult();
            bizResult.setData(HttpRspCode.SUCCESS.getCode());
            bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
            bizResult.setData(all);
        }
        if(reqBody.containsKey("useId")
        &&keySet.size()==1){
            List<Rate> all = rateDao.findByUserId((Integer) reqBody.get("useId"));
            BizResult bizResult = new BizResult();
            bizResult.setData(HttpRspCode.SUCCESS.getCode());
            bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
            bizResult.setData(all);
        }
        List<Rate> all = rateDao.findByRateDateAndUserId((String) reqBody.get("rateDate"),(Integer) reqBody.get("useId"));
        BizResult bizResult = new BizResult();
        bizResult.setData(HttpRspCode.SUCCESS.getCode());
        bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
        bizResult.setData(all);
        return bizResult;
    }

}
