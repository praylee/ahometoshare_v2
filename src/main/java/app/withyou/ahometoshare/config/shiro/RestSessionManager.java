package app.withyou.ahometoshare.config.shiro;

import app.withyou.ahometoshare.utils.EncryptionUtil;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

public class RestSessionManager extends DefaultWebSessionManager {
    private static final String TOKEN = "token";
    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";
    private static final Logger logger = LoggerFactory.getLogger(RestSessionManager.class);

    public RestSessionManager(){
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {

        String token = WebUtils.toHttp(request).getHeader(TOKEN);
        if (!StringUtils.isEmpty(token)){
            String sessionId = null;
            try{
                sessionId = EncryptionUtil.DecryptAES(token);
            }catch (Exception e){
                logger.debug("Fail to Decrypt this token"+ token +" with key, token might be invalid", e);
            }
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sessionId;
        }else {
            return super.getSessionId(request, response);
        }
    }
}
