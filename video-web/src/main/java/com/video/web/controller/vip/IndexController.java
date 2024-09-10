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

    @GetMapping("single-video")
    public ModelAndView singleVideo(){
        List<VideoOrder> list = videoOderService.listVideOrder();
        ModelAndView modelAndView = new ModelAndView("single-video");
        modelAndView.addObject("videoList", list);
        return modelAndView;

    }
    @GetMapping("login-register")
    public ModelAndView loginRegister(){
        List<VideoOrder> list = videoOderService.listVideOrder();
        ModelAndView modelAndView = new ModelAndView("login-register");
        modelAndView.addObject("videoList", list);
        return modelAndView;
    }
    @GetMapping("login-video")
    public ModelAndView loginVideo(){
        List<VideoOrder> list = videoOderService.listVideOrder();
        ModelAndView modelAndView = new ModelAndView("login-video");
        modelAndView.addObject("videoList", list);
        return modelAndView;
    }
    @GetMapping("about-us")
    public ModelAndView aboutUs(){
        List<VideoOrder> list = videoOderService.listVideOrder();
        ModelAndView modelAndView = new ModelAndView("about-us");
        modelAndView.addObject("videoList", list);
        return modelAndView;
    }
    @GetMapping("contact-us")
    public ModelAndView contactUs(){
        List<VideoOrder> list = videoOderService.listVideOrder();
        ModelAndView modelAndView = new ModelAndView("contact-us");
        modelAndView.addObject("videoList", list);
        return modelAndView;
    }

    @GetMapping("profile-settings")
    public ModelAndView profileSettings(){
        List<VideoOrder> list = videoOderService.listVideOrder();
        ModelAndView modelAndView = new ModelAndView("profile-settings");
        modelAndView.addObject("videoList", list);
        return modelAndView;
    }

    @GetMapping("profile-video")
    public ModelAndView profileVideo(){
        List<VideoOrder> list = videoOderService.listVideOrder();
        ModelAndView modelAndView = new ModelAndView("profile-video");
        modelAndView.addObject("videoList", list);
        return modelAndView;
    }

    @GetMapping("submit-post")
    public ModelAndView submitPost(){
        List<VideoOrder> list = videoOderService.listVideOrder();
        ModelAndView modelAndView = new ModelAndView("submit-post");
        modelAndView.addObject("videoList", list);
        return modelAndView;
    }
}
