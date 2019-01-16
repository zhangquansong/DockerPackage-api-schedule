package com.clt.api.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.web.util.NestedServletException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

/**
 * 自定义hessian服务发布,可用于自定义验证服务
 *
 * @author xqlee
 */
@Slf4j
public class HessianServerProxyExporter extends HessianServiceExporter {


    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String authorization = request.getHeader("authorization");
        if (StringUtils.isEmpty(authorization)) {
            throw new NestedServletException("Auth Is Empty!");
        }
        log.info(authorization);
        String[] authArr = authorization.trim().split(" ");
        String auth = authArr[1];
        auth = new String(Base64.getDecoder().decode(auth));
        String[] namePwdArr = auth.split(":");
        String pwd = namePwdArr[1];
        // 验证密码
        if (!"schedule.ibrjf".equals(pwd)) {
            throw new NestedServletException("Auth Fail!");
        }
        super.handleRequest(request, response);

    }

}