package com.video.web.controller.vip;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.video.system.domain.VideoOrder;
import com.video.system.service.VideoOderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.video.common.core.controller.BaseController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 首页 业务处理
 * 
 * @author ruoyi
 */
@Controller
public class IndexController extends BaseController
{


    @Autowired
    private VideoOderService videoOderService;
    /**
     * 清单列表
     */
    @GetMapping("index")
    public ModelAndView videoList(){
        List<VideoOrder> list = videoOderService.listVideOrder();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("videoList", list);
        return modelAndView;

    }
}
