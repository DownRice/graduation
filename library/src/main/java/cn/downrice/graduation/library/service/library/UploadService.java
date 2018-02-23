package cn.downrice.graduation.library.service.library;

import cn.downrice.graduation.library.async.EventModel;
import cn.downrice.graduation.library.async.EventProducer;
import cn.downrice.graduation.library.async.EventType;
import cn.downrice.graduation.library.dao.DocumentDAO;
import cn.downrice.graduation.library.model.ReturnResponse;
import cn.downrice.graduation.library.model.library.Document;
import cn.downrice.graduation.library.model.library.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * 系统内所有上传相关服务
 * @author 下饭
 */
@Service
public class UploadService {

    @Autowired
    DocumentDAO documentDAO;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    EventProducer eventProducer;

    public ReturnResponse<String> documentUpload(String uploadDir, MultipartFile file, Document doc){
        File dir = new File(uploadDir);
        //文件路径不存在的创建
        if (!dir.exists()){
            dir.mkdir();
        }


        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String realId = UUID.randomUUID().toString();
        String fileName = realId + "." + suffix;

        File serverFile = new File(uploadDir+fileName);
        try {
            file.transferTo(serverFile);
            doc.setRealId(realId);
            doc.setLoc(uploadDir + fileName);
            doc.setUploader(hostHolder.getUser().getId());
            doc.setType(suffix);
            doc.setState(false);
            doc.setUploadTime(new Date());
            if(documentDAO.insertDocument(doc) > 0){
                //插入成功,加入事件队列进行异步转换
                eventProducer.fireEvent(new EventModel(EventType.CONVERT)
                        .setExt("inputFilePath", doc.getLoc()).setExt("outputFilePath", "")
                        .setExt("realId", doc.getRealId())
                        .setActorId(hostHolder.getUser().getId())
                );
            }else{
                //插入失败
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

}
