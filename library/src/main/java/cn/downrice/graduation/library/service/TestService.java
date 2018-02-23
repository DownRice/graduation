package cn.downrice.graduation.library.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    Logger logger = LoggerFactory.getLogger(TestService.class);
    /**
     * 在minGoodsnum方法上加上@Retryable注解，
     * value值表示当哪些异常的时候触发重试，
     * maxAttempts表示最大重试次数默认为3，
     * delay表示重试的延迟时间，
     * multiplier表示上一次延时时间是这一次的倍数。
     */
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000,multiplier = 1.5))
    public void retryDemo() throws Exception {
        try{
            logger.info("尝试中");
            testRetry();
        }catch (Exception e){
            throw e;
        }
        logger.info("不应该出现");
    }


    public void testRetry(){
        int a = 0;
        int b = 1;
        int c = b/a;
    }
}
