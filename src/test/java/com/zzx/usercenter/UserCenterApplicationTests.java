package com.zzx.usercenter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
//@RunWith(SpringRunner.class)
class UserCenterApplicationTests {

    @Test
    void contextLoads() {
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？ ]";
        Matcher matcher = Pattern.compile(validPattern).matcher("111222333");
        if (matcher.find()) {
            System.out.println(123);
        }
    }

}
