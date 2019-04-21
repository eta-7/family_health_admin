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
import zkt.health.dao.PressureDao;
import zkt.health.domain.Pressure;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class PressureController {
    private Logger logger = LoggerFactory.getLogger(PressureController.class);

    @Autowired
    private PressureDao pressureDao;

    @ResponseBody
    @RequestMapping(value = "/pressure/add", method = {RequestMethod.POST})
    public BizResult addPressure(@RequestBody Pressure pressure) {
        //整理根据用户和开始时间作为唯一标识吧
        List<Pressure> pressures = pressureDao.findByPressureDateAndUserId(pressure.getPressureDate(), pressure.getUserId());
        if(pressures.size()>0){
            BizResult success = BizResult.success();
            success.setRetCode(HttpRspCode.DATA_EXIST.getCode());
            success.setMessage(HttpRspCode.DATA_EXIST.getDesc());
            return success;
        }
        pressureDao.save(pressure);
        BizResult success = BizResult.success();
        success.setRetCode(HttpRspCode.SUCCESS.getCode());
        return success;
    }

    @ResponseBody
    @RequestMapping(value = "/pressure/delete", method = {RequestMethod.POST})
    public BizResult deltePressure(@RequestBody Map<String, Object> reqBody) {
        BizResult bizResult = new BizResult();
        if (!reqBody.containsKey("pressureDate;") ||
                null == reqBody.get("pressureDate;")) {
            bizResult.setMessage(HttpRspCode.REQ_PARAM_IS_NULL.getDesc());
            bizResult.setRetCode(HttpRspCode.REQ_PARAM_IS_NULL.getCode());
        }
        pressureDao.deleteByPressureDateAndUserId((String) reqBody.get("pressureDate"), (Integer) reqBody.get("useId;"));
        bizResult.setRetCode(HttpRspCode.SUCCESS.getCode());
        bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
        return new BizResult();
    }

    @ResponseBody
    @RequestMapping(value = "/pressure/update", method = {RequestMethod.POST})
    public BizResult updatePressure(@RequestBody Pressure pressure) {
        //根据startTime和userID确定唯一，在进行更新
        List<Pressure> pressures = pressureDao.findByPressureDateAndUserId(pressure.getPressureDate(), pressure.getUserId());
        if(pressures.size()==0){
            BizResult bizResult = new BizResult();
            bizResult.setMessage(HttpRspCode.DATA_NOT_EXIST.getDesc());
            bizResult.setRetCode(HttpRspCode.DATA_NOT_EXIST.getCode());
            return bizResult;
        }
        pressure.setPressureId(pressures.get(0).getPressureId());
        pressureDao.save(pressure);
        BizResult success = BizResult.success();
        success.setRetCode(HttpRspCode.SUCCESS.getCode());
        return success;
    }

    @ResponseBody
    @RequestMapping(value = "/pressure/query", method = {RequestMethod.POST})
    public BizResult queryPressure(@RequestBody Map<String, Object> reqBody) {
        Set<String> keySet = reqBody.keySet();
        //如果什么都没有传，默认查询全部
        if(keySet.size()==0){
            List<Pressure> all = pressureDao.findAll();
            BizResult bizResult = new BizResult();
            bizResult.setData(HttpRspCode.SUCCESS.getCode());
            bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
            bizResult.setData(all);
        }
        if(reqBody.containsKey("useId")
        &&keySet.size()==1){
            List<Pressure> all = pressureDao.findByUserId((Integer) reqBody.get("useId"));
            BizResult bizResult = new BizResult();
            bizResult.setData(HttpRspCode.SUCCESS.getCode());
            bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
            bizResult.setData(all);
        }
        List<Pressure> all = pressureDao.findByPressureDateAndUserId((String) reqBody.get("pressureDate"),(Integer) reqBody.get("useId"));
        BizResult bizResult = new BizResult();
        bizResult.setData(HttpRspCode.SUCCESS.getCode());
        bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
        bizResult.setData(all);
        return bizResult;
    }

}
