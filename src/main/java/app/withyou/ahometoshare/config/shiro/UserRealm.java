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

import java.util.*;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Autowired
    PermissionService permissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String email = (String)principals.getPrimaryPrincipal();
        User user = userService.findUserByEmail(email);
        Set<String> roles = new HashSet<>();
        if (user != null){
            if (user.getUserType()==Constants.USER_TYPE_HOST){
                roles.add("host");
            }else if(user.getUserType()==Constants.USER_TYPE_RENTER){
                roles.add("renter");
            }else if(user.getUserType()==Constants.USER_TYPE_ADMIN){
                roles.add("admin");
            }
        }
        authorizationInfo.setRoles(roles);
        Collection<String> permissionList = Collections.emptySet();
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
        SecurityUtils.getSubject().getSession().setAttribute(Constants.USER_TYPE_STRING, user.getUserType());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getEmail(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getEmail()), // email as salt
                getName()
        );
        SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER, user);
        return authenticationInfo;
    }

    public static void main(String args[]){
        String str = "ahometoshare";
        System.out.println(str.getBytes());
        Md5Hash md5Hash = new Md5Hash(str,"prayleee@gmail.com") ;  // 加密，加盐
        System.out.println(md5Hash.toString());
    }
}
