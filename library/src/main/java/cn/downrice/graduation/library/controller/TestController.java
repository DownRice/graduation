package cn.downrice.graduation.library.controller;

import cn.downrice.graduation.library.extend.ControlDocumentFormatRegistry;
import cn.downrice.graduation.library.service.TestService;
import com.sun.star.document.UpdateDocMode;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    OfficeManager officeManager;

    @Autowired
    TestService testService;

    @RequestMapping(path = "test", method = RequestMethod.GET)
    @ResponseBody
    public String test(){
//        DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
//        configuration.setOfficeHome("D:\\work\\OpenOffice 4\\");
//        configuration.setPortNumber(8100);
//        officeManager = configuration.buildOfficeManager();
//        officeManager.start();
//
//        Map<String,Object> loadProperties = new HashMap<String, Object>(10);
//        loadProperties.put("Hidden", true);
//        loadProperties.put("ReadOnly", true);
//        loadProperties.put("UpdateDocMode", UpdateDocMode.QUIET_UPDATE);
//        loadProperties.put("CharacterSet", Charset.forName("UTF-8").name());
//
//
//
//        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager, new ControlDocumentFormatRegistry());
//        converter.setDefaultLoadProperties(loadProperties);
//        converter.convert(new File("D:\\dev\\水务科目余额表2017年.xlsx"), new File("D:\\dev\\test.html"));
//        officeManager.stop();
//        return "success";
        try {
            testService.retryDemo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";

    }



    public String officeConvertDemo(){

        return null;
    }
}
