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
import zkt.health.dao.UserDao;
import zkt.health.domain.Sleep;
import zkt.health.domain.User;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserDao userDao;

    /**
     * 注册功能
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/add", method = {RequestMethod.POST})
    public BizResult addUser(@RequestBody User user) {
        //整理根据用户和开始时间作为唯一标识吧
        List<User> byUsername = userDao.findByUsername(user.getUsername());
        if(byUsername.size()>0){
            BizResult bizResult = new BizResult();
            bizResult.setMessage(HttpRspCode.DATA_EXIST.getDesc());
            bizResult.setRetCode(HttpRspCode.DATA_EXIST.getCode());
            return bizResult;
        }
        userDao.save(user);
        BizResult bizResult = new BizResult();
        bizResult.setRetCode(HttpRspCode.SUCCESS.getCode());
        bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
        return bizResult;
    }

    @ResponseBody
    @RequestMapping(value = "/user/query", method = {RequestMethod.POST})
    public BizResult queryUser(@RequestBody Map<String, Object> reqBody) {
        BizResult bizResult = new BizResult();
        if(!reqBody.containsKey("username")
        ||!reqBody.containsKey("password")){
            bizResult.setMessage(HttpRspCode.REQ_PARAM_IS_NULL.getDesc());
            bizResult.setRetCode(HttpRspCode.REQ_PARAM_IS_NULL.getCode());
            return bizResult;
        }
        List<User> users = userDao.findByUsernameAndPassword((String) reqBody.get("username"), (String) reqBody.get("password"));
        //这里应该判断下Ian，为了方便直接返回
        bizResult.setRetCode(HttpRspCode.SUCCESS.getCode());
        bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
        bizResult.setData(users.get(0));
        return bizResult;
    }




}
