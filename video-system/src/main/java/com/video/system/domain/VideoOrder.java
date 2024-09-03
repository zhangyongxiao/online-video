package com.video.system.domain;

import lombok.Data;

@Data
public class VideoOrder {

    private String id;

    private String videoName;

    private String videoPatch;

    private String videoMemo;

    private String videoSaveType;

    private String status;

}
