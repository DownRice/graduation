package cn.downrice.graduation.library.service.preview;

import cn.downrice.graduation.library.model.preview.FileAttribute;
import cn.downrice.graduation.library.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 文件预览代理工厂
 * @author 下饭
 */
@Service
public class FilePreviewFactory {
    @Autowired
    ApplicationContext context;

    @Autowired
    FileUtils fileUtils;

    /**
     * url为文件地址（位置）
     * @param url
     * @return
     */
    public FilePreview get(String url){
        Map<String, FilePreview> filePreviewMap = context.getBeansOfType(FilePreview.class);
        FileAttribute fileAttribute = fileUtils.getFileAttribute(url);
        if(null != fileAttribute){
            return filePreviewMap.get(fileAttribute.getType().getInstanceName());
        }
        return null;
    }


}
