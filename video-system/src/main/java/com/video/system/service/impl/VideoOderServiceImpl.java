package com.video.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.video.system.domain.VideoOrder;
import com.video.system.mapper.VideoOrderMapper;
import com.video.system.service.VideoOderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Log4j2
@Service
public class VideoOderServiceImpl extends ServiceImpl<VideoOrderMapper, VideoOrder> implements VideoOderService {
    @Autowired
    VideoOrderMapper videoOrderMapper;
    @Override
    public List<VideoOrder> listVideOrder() {
        log.info("-----");
        List<VideoOrder> list =  this.lambdaQuery().eq(VideoOrder::getStatus,5)
                .list();
        return Collections.emptyList();
    }
}
