package app.withyou.ahometoshare.config.shiro;

import app.withyou.ahometoshare.model.User;
import app.withyou.ahometoshare.service.PermissionService;
import app.withyou.ahometoshare.service.UserService;
import app.withyou.ahometoshare.utils.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Collections;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Autowired
    PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String email = (String)principals.getPrimaryPrincipal();
        User user = userService.findUserByEmail(email);
        Collection<String> permissionList = Collections.emptySet();
        if (user != null){
             permissionList = permissionService.getPermissionsByUser(user);
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissionList);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken =(UsernamePasswordToken)token;
        String email = usernamePasswordToken.getUsername();
        User user = userService.findUserByEmail(email);
        if (user==null){
            throw new AuthenticationException("User not found");
        }
        SecurityUtils.getSubject().getSession().setAttribute("userType", user.getUserType());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getEmail(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getEmail()), // email as salt
                getName()
        );
        SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO, user);
        return authenticationInfo;
    }

    public static void main(String args[]){
        String str = "ahometoshare";
        System.out.println(str.getBytes());
        Md5Hash md5Hash = new Md5Hash(str,"prayleee@gmail.com") ;  // 加密，加盐
        System.out.println(md5Hash.toString());
    }
}
