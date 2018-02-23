package cn.downrice.graduation.library.utils;

import cn.downrice.graduation.library.model.ReturnResponse;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.io.File;
import java.util.logging.Logger;

/**
 * Office文档转换工具类
 * @author 下饭
 */
public class OfficeConvertUtils {

    org.slf4j.Logger logger = LoggerFactory.getLogger(OfficeConvertUtils.class);

    @Autowired
    ConverterUtils converterUtils;

    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000,multiplier = 1.5))
    public ReturnResponse<String> officeConvert(String inputFilePath, String outputFilePath) throws Exception {
        ReturnResponse<String> returnResponse = new ReturnResponse<String>();
        returnResponse.setContent(null);
        OfficeDocumentConverter converter = converterUtils.getDocumentConverter();
        if(null != inputFilePath && !"".equals(inputFilePath)){
            File inputFile = new File(inputFilePath);
            StringBuffer outputPath = new StringBuffer();
            //源文件存在
            if(inputFile.exists()){
                //目标路径为空，则默认为源文件同目录下并自动判断转换格式
                if(null == outputFilePath || "".equals(outputFilePath)){
                    String inputPostfix = inputFilePath.substring(inputFilePath.lastIndexOf(".") + 1);
                    if("xls".equals(inputPostfix) || "xlsx".equals(inputPostfix)){
                        outputPath.append( inputFilePath.substring(0, inputFilePath.lastIndexOf(".")+1) + "html");
                    }else{
                        outputPath.append( inputFilePath.substring(0, inputFilePath.lastIndexOf(".")+1) + "pdf");
                    }
                }else{
                    outputPath.append(outputFilePath);
                }

                File outputFile = new File(outputPath.toString());
                //目标路径实际不存在，则创建路径
                if(!outputFile.getParentFile().exists()){
                    outputFile.getParentFile().mkdir();
                }

                //转换
                try {
                    converter.convert(inputFile, outputFile);
                }catch (Exception e){
                    //转换出错则抛出异常出发重试机制，最大次数3
                    throw e;
                }
            }
        }
        returnResponse.setCode(ReturnResponseUtils.BASE_SUCCESS_CODE);
        returnResponse.setMsg(ReturnResponseUtils.BASE_SUCCESS_MSG);
        return returnResponse;
    }

    @Recover()
    public void recover(Exception e){
        logger.info("Office转换异常："+e);
    }
}
