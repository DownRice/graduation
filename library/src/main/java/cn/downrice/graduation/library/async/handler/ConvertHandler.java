package cn.downrice.graduation.library.async.handler;

import cn.downrice.graduation.library.async.EventHandler;
import cn.downrice.graduation.library.async.EventModel;
import cn.downrice.graduation.library.async.EventType;
import cn.downrice.graduation.library.dao.DocumentDAO;
import cn.downrice.graduation.library.model.ReturnResponse;
import cn.downrice.graduation.library.model.library.Document;
import cn.downrice.graduation.library.utils.OfficeConvertUtils;
import cn.downrice.graduation.library.utils.ReturnResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class ConvertHandler implements EventHandler{

    Logger logger = LoggerFactory.getLogger(ConvertHandler.class);

    @Autowired
    OfficeConvertUtils officeConvertUtils;

    @Autowired
    DocumentDAO documentDAO;

    @Override
    public void doHandle(EventModel model){
        String inputFilePath = model.getExt("inputFilePath");
        String outputFilePath = model.getExt("outputFilePath");
        try {
            ReturnResponse returnResponse = officeConvertUtils.officeConvert(inputFilePath, outputFilePath);
            //转换成功，更新数据库状态
            if(ReturnResponseUtils.BASE_SUCCESS_CODE == returnResponse.getCode()){
                String realId = model.getExt("realId");
                Document doc = documentDAO.getDocumentByRealId(realId);
                doc.setState(true);
                documentDAO.updateDocument(doc);
            }
        }catch (Exception e){
            logger.info("文件:\"" + inputFilePath + "\" 转换出错，重试已超最大次数，反馈错误信息：" + e);
        }
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.CONVERT);
    }
}
