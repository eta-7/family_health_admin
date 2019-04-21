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
import zkt.health.dao.ArchiveDao;
import zkt.health.domain.Archive;
import java.util.Map;

@Controller
public class ArchiveController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ArchiveDao archiveDao;
    /**
     * 添加档案
     * @param archive
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/archive/add", method = {RequestMethod.POST})
    public BizResult addArchive(@RequestBody Archive archive) {
        //暂定为一个人只对应一个健康档案
        Archive byUserId = archiveDao.findByUserId(archive.getUserId());
        if(byUserId!=null){
            BizResult bizResult = new BizResult();
            bizResult.setMessage(HttpRspCode.DATA_EXIST.getDesc());
            bizResult.setRetCode(HttpRspCode.DATA_EXIST.getCode());
            return bizResult;
        }
        archiveDao.save(archive);
        BizResult bizResult = new BizResult();
        bizResult.setRetCode(HttpRspCode.SUCCESS.getCode());
        bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
        return bizResult;
    }

    /**
     * 修改档案
     * @param archive
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/archive/update", method = {RequestMethod.POST})
    public BizResult updateArchive(@RequestBody Archive archive) {
        //暂定为一个人只对应一个健康档案
        Archive byUserId = archiveDao.findByUserId(archive.getUserId());
        archive.setInfoId(byUserId.getInfoId());
        archiveDao.save(archive);
        BizResult bizResult = new BizResult();
        bizResult.setRetCode(HttpRspCode.SUCCESS.getCode());
        bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
        return bizResult;
    }

    /**
     * 修改档案
     * @param reqBody
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/archive/query", method = {RequestMethod.POST})
    public BizResult updateArchive(@RequestBody Map<String,Object> reqBody) {
        //暂定为一个人只对应一个健康档案
        if(!reqBody.containsKey("userId")){
            BizResult bizResult = new BizResult();
            bizResult.setMessage(HttpRspCode.REQ_PARAM_IS_NULL.getDesc());
            bizResult.setRetCode(HttpRspCode.REQ_PARAM_IS_NULL.getCode());
            return bizResult;
        }
        Archive userId = archiveDao.findByUserId((Integer) reqBody.get("userId"));
        BizResult bizResult = new BizResult();
        bizResult.setMessage(HttpRspCode.SUCCESS.getDesc());
        bizResult.setRetCode(HttpRspCode.SUCCESS.getCode());
        bizResult.setData(userId);
        return bizResult;
    }

}
