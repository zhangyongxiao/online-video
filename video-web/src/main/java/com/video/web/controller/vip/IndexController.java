package com.video.web.controller.vip;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.video.common.core.controller.BaseController;

/**
 * 首页 业务处理
 * 
 * @author ruoyi
 */
@Controller
public class IndexController extends BaseController
{

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {

        return "index";
    }
}
