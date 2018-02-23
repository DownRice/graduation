package cn.downrice.graduation.library.controller;

import cn.downrice.graduation.library.model.ReturnResponse;
import cn.downrice.graduation.library.model.library.Document;
import cn.downrice.graduation.library.service.library.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统内所有上传相关功能的控制器
 * @author 下饭
 */
@Controller
public class UploadController {

    @Autowired
    UploadService uploadService;

    @RequestMapping(path = "documentUpload", method = RequestMethod.POST)
    @ResponseBody
    public ReturnResponse<String> documentUpload(HttpServletRequest request, MultipartFile file, @ModelAttribute Document doc){
        //上传文件夹
        String uploadDir = request.getSession().getServletContext().getRealPath("/")+"upload/";
        return  uploadService.documentUpload(uploadDir, file, doc);
    }
}
