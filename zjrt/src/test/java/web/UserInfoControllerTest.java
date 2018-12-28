package web;

import com.zjrt.controller.UserInfoController;
import com.zjrt.dto.CommonResult;
import com.zjrt.entity.UserInfoEntity;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;

/**
 * Created by Administrator on 2018-2-9.
 */

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@WebAppConfiguration
@ContextConfiguration({
        "classpath*:applicationContext.xml"})
public class UserInfoControllerTest extends TestCase {
    @Autowired
    private UserInfoController userInfoController;

    @Autowired
    private WebApplicationContext wac;
    @Test
    public void testAddUser() throws Exception {
        UserInfoEntity userInfoEntity = new UserInfoEntity(10,"小蓝",
                "123456",1,"15869191710",1234);
        CommonResult result = userInfoController.addUser(userInfoEntity);
        System.out.println("test:-----------"+result);
    }
    @Test
    public void ss(){
        String upload = wac.getServletContext().getRealPath("upload");
        System.err.println(upload);
        File file = new File(upload);
        if (!file.isDirectory() || !file.exists()){
            file.mkdir();
        }

    }

}