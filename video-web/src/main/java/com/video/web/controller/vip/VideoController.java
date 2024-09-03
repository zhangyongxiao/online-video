package com.video.web.controller.vip;

import com.video.common.utils.StringUtils;
import com.video.system.domain.VideoOrder;
import com.video.system.service.VideoOderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.List;

@Controller
@Log4j2
public class VideoController {



    @GetMapping("detail")
    public String detail(){

        return "detail";
    }

    /**
     * 分块读取
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("blockReading")
    public void BlockReading(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.reset();
        File file = new File("F:\\video\\雷米奇遇记-2018_蓝光国法双语中字.mp4");
        long fileLength = file.length();
// 随机读文件
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
//获取从那个字节开始读取文件
        String rangeString = request.getHeader("Range");
        long range=0;
        if (StringUtils.isNotBlank(rangeString)) {
            range = Long.valueOf(rangeString.substring(rangeString.indexOf("=") + 1, rangeString.indexOf("-")));
        }
//获取响应的输出流
        OutputStream outputStream = response.getOutputStream();
//设置内容类型
        response.setHeader("Content-Type", "video/mp4");
//返回码需要为206，代表只处理了部分请求，响应了部分数据
        response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
// 移动访问指针到指定位置
        randomAccessFile.seek(range);
// 每次请求只返回1MB的视频流
        byte[] bytes = new byte[1024 * 1024];
        int len = randomAccessFile.read(bytes);
//设置此次相应返回的数据长度
        response.setContentLength(len);
//设置此次相应返回的数据范围
        response.setHeader("Content-Range", "bytes "+range+"-"+(fileLength-1)+"/"+fileLength);
// 将这1MB的视频流响应给客户端
        outputStream.write(bytes, 0, len);
        outputStream.close();
        randomAccessFile.close();
        log.info("返回数据区间:【"+range+"-"+(range+len)+"】");
    }

}
