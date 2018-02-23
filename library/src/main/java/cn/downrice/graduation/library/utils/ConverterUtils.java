package cn.downrice.graduation.library.utils;

import cn.downrice.graduation.library.extend.ControlDocumentFormatRegistry;
import com.sun.star.document.UpdateDocMode;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * OpenOffice转换工具类
 * @author 下饭
 */
@Component
public class ConverterUtils {

    @Value("${open.office.home}")
    String officeHome;

    OfficeManager officeManager;

    /**
     * 初始化OfficeManager，仅初始化一次
     */
    @PostConstruct
    public  void initOfficeManager(){
        DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
        configuration.setOfficeHome(officeHome);
        configuration.setPortNumber(8100);
        officeManager = configuration.buildOfficeManager();
        officeManager.start();
    }

    /**
     * OpenOffice转化配置
     * @return
     */
    private Map<String, ?> getLoadProperties(){
        Map<String,Object> loadProperties = new HashMap<String, Object>(10);
        loadProperties.put("Hidden", true);
        loadProperties.put("ReadOnly", true);
        loadProperties.put("UpdateDocMode", UpdateDocMode.QUIET_UPDATE);
        loadProperties.put("CharacterSet", Charset.forName("UTF-8").name());
        return loadProperties;
    }

    /**
     * 构建OfficeDocumentConverter
     * @return
     */
    public OfficeDocumentConverter getDocumentConverter() {
        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager, new ControlDocumentFormatRegistry());
        converter.setDefaultLoadProperties(getLoadProperties());
        return converter;
    }

    /**
     * 停止OfficeManager
     */
    @PreDestroy
    public void stopOfficeManager(){
        if(null != officeManager && officeManager.isRunning()){
            officeManager.stop();
        }
    }


}
