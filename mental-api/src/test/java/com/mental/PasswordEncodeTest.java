package com.mental;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordEncodeTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testPassword(){
        //加密
        String teacher = passwordEncoder.encode("admin");
        System.out.println(teacher);

        //校验密码
//        String str = "$2a$10$9c2dryKUCYyF6KSoxQPXq.Et10mMPCPobNnPOW1.qBpenb8WAwqji";
//        boolean matches = passwordEncoder.matches("teacher", str);
//        System.out.println(matches);
    }
}
