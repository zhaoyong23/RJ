package com.zy.rj.controller;

import com.zy.rj.common.contants.Contants;
import com.zy.rj.common.domain.ReturnObject;
import com.zy.rj.common.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/common")
public class FileUploadDownloadController {
    @Value("${reggie.path}")
    private String basePath;


    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Object fileUploads(MultipartFile file){
        ReturnObject returnObject = new ReturnObject();
        log.info(file.toString()+"开始文件上传");

        //原始文件名
        String originalFilename = file.getOriginalFilename();//abc.jpg
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        String uuidFileName = UUIDUtils.getUUID() + suffix;

        //创建一个目录对象
        File dir = new File(basePath);
        //判断当前目录是否存在
        if(!dir.exists()){
            //目录不存在，需要创建
            dir.mkdirs();
        }

        try {
            file.transferTo(new File(basePath + uuidFileName));
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setMessage("文件上传失败");
            returnObject.setData(uuidFileName);
            log.info(file.toString()+"文件上传成功");
        }catch (IOException e){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("文件上传失败");
            log.info(file.toString()+"文件上传失败");
        }
        return returnObject;
    }


    /**
     * 文件下载
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void fileDownload(String name , HttpServletResponse response){
        //log.info("开始下载文件");
        try {
            //输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
            //输出流，通过输出流将文件写回浏览器
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }

            //关闭资源
            outputStream.close();
            fileInputStream.close();
            log.info("下载文件成功");
        } catch (Exception e) {
            //log.info("下载文件失败");
        }
    }


}
