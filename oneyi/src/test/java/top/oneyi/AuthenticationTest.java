package top.oneyi;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

import java.beans.Transient;

public class AuthenticationTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    /**
     * 在方法开始前添加一个用户
     */
    @Before
    public void addUser() {
        // param1 用户名  param2密码 param3 角色名
        simpleAccountRealm.addAccount("wanyi", "123456","admin","user");
    }

    @Test
    public void testAuthentication() {

        // 1 构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);
        // 2主题提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        // 3 创建用户 token对象
        UsernamePasswordToken token = new UsernamePasswordToken("wanyi", "123456");
        subject.login(token);
        // 4 验证用户
/*          System.out.println("subject.isAuthenticated() = " + subject.isAuthenticated());  // true
      subject.logout(); // 登出
        System.out.println("subject.isAuthenticated() = " + subject.isAuthenticated());  // false*/
        // 判断该用户是否认证成功
        System.out.println("subject.isAuthenticated() = " + subject.isAuthenticated());
        // 看当前用户是否有这俩个角色
        subject.checkRoles("admin","user");

    }
}
