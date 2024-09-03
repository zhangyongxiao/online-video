package com.video.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.video.system.domain.VideoOrder;

import java.util.List;

public interface VideoOderService extends IService<VideoOrder> {

    public List<VideoOrder> listVideOrder();
}
