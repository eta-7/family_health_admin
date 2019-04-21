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
import zkt.health.dao.WeightDao;
import zkt.health.domain.Weight;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class WeightController {
    private Logger logger = LoggerFactory.getLogger(WeightController.class);

    @Autowired
    private WeightDao weightDao;

    @ResponseBody
    @RequestMapping(value = "/weight/add", method = {RequestMethod.POST})
    public BizResult addWeight(@RequestBody Weight weight) {
        //整理根据用户和开始时间作为唯一标识吧
        List<Weight> weights = weightDao.findByWeightDateAndUserId(weight.getWeightDate(), weight.getUserId());
        if(weights.size()>0){
            BizResult success = BizResult.success();
            success.setRetCode(HttpRspCode.DATA_EXIST.getCode());
            success.setMessage(HttpRspCode.DATA_EXIST.getDesc());
            return success;
        }
        weightDao.save(weight);
        BizResult success = BizResult.success();
        success.setRetCode(HttpRspCode.SUCCESS.getCode());
        return success;
    }

    @ResponseBody
    @RequestMapping(value = "/weight/delete", method = {RequestMethod.POST})
    public BizResult delteWeight(@RequestBody Map<String, Object> reqBody) {
        BizResult bizResult = new BizResult();
        if (!reqBody.containsKey("weightDate;") ||
                null == reqBody.get("weightDate;")) {
            bizResult.setMessage(HttpRspCode.REQ_PARAM_IS_NULL.getDesc());
            bizResult.setRetCode(HttpRspCode.REQ_PARAM_IS_NULL.getCode());
        }
        weightDao.deleteByWeightDateAndUserId((String) reqBody.get("weightDate"), (Integer) reqBody.get("useId;"));
        bizResult.setRetCode(HttpRspCode.SUCCESS.getCode());
        bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
        return new BizResult();
    }

    @ResponseBody
    @RequestMapping(value = "/weight/update", method = {RequestMethod.POST})
    public BizResult updateWeight(@RequestBody Weight weight) {
        //根据startTime和userID确定唯一，在进行更新
        List<Weight> weights = weightDao.findByWeightDateAndUserId(weight.getWeightDate(), weight.getUserId());
        if(weights.size()==0){
            BizResult bizResult = new BizResult();
            bizResult.setMessage(HttpRspCode.DATA_NOT_EXIST.getDesc());
            bizResult.setRetCode(HttpRspCode.DATA_NOT_EXIST.getCode());
            return bizResult;
        }
        weight.setWeightId(weights.get(0).getWeightId());
        weightDao.save(weight);
        BizResult success = BizResult.success();
        success.setRetCode(HttpRspCode.SUCCESS.getCode());
        return success;
    }

    @ResponseBody
    @RequestMapping(value = "/weight/query", method = {RequestMethod.POST})
    public BizResult queryWeight(@RequestBody Map<String, Object> reqBody) {
        Set<String> keySet = reqBody.keySet();
        //如果什么都没有传，默认查询全部
        if(keySet.size()==0){
            List<Weight> all = weightDao.findAll();
            BizResult bizResult = new BizResult();
            bizResult.setData(HttpRspCode.SUCCESS.getCode());
            bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
            bizResult.setData(all);
        }
        if(reqBody.containsKey("useId")
        &&keySet.size()==1){
            List<Weight> all = weightDao.findByUserId((Integer) reqBody.get("useId"));
            BizResult bizResult = new BizResult();
            bizResult.setData(HttpRspCode.SUCCESS.getCode());
            bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
            bizResult.setData(all);
        }
        List<Weight> all = weightDao.findByWeightDateAndUserId((String) reqBody.get("weightDate"),(Integer) reqBody.get("useId"));
        BizResult bizResult = new BizResult();
        bizResult.setData(HttpRspCode.SUCCESS.getCode());
        bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
        bizResult.setData(all);
        return bizResult;
    }

}
