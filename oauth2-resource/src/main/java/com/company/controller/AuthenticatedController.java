package com.company.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/authenticated")
@Api(value = "AccessToken测试接口",tags = "测试用户权限，请使用从oauth2-server获取到的Access Token加在swagger2头信息中,Authorization=Bear " +
        "AccessTokenValue")
public class AuthenticatedController {

    /**
     * 【@RolesAllowed】注解权限控制：ROLE_MEMBER 访问该接口
     **/
    @ApiOperation(value = "@RolesAllowed 注解测试 ROLE_MEMBER 权限",
            notes = "需开启注解：@EnableGlobalMethodSecurity" +"(jsr250Enabled=true)")
    @RolesAllowed({"ROLE_MEMBER"})
    @GetMapping("/roleAllowed/member")
    public String roleMemberRolesAllowed() {
        return "success";
    }

    @ApiOperation(value = "@RolesAllowed 注解测试 ROLE_ADMIN 权限",
            notes = "需开启注解：@EnableGlobalMethodSecurity(jsr250Enabled=true)")
    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("/roleAllowed/admin")
    public String roleAdminRolesAllowed() {
        return "success";
    }

    /**
     * 【@Secured】注解权限控制：ROLE_MEMBER访问该接口
     **/
    @ApiOperation(value = "@Secured 注解测试 ROLE_MEMBER 权限",
            notes = "需开启注解： @EnableGlobalMethodSecurity(securedEnabled =true) 且 @Secured对应的角色必须要有ROLE_前缀!")
    @Secured({"ROLE_MEMBER"})
    @GetMapping("/secured/member")
    public String roleMember() {
        return "success";
    }

    /**
     * 【@Secured】注解权限控制：ROLE_ADMIN 访问该接口
     **/
    @ApiOperation(value = "@Secured 注解测试 ROLE_MEMBER 权限",
            notes = "需开启注解： @EnableGlobalMethodSecurity(securedEnabled =true) 且 @Secured对应的角色必须要有ROLE_前缀!")
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/secured/admin")
    public String roleAdmin() {
        return "success";
    }


    /**
     * 【@PreAuthorize】注解权限控制：自定义方法控制权限
     **/
    @ApiOperation(value = "@PreAuthorize 自定义方法控制权限",
            notes = "需开启注解： @EnableGlobalMethodSecurity(prePostEnabled =true) ")
    @PreAuthorize("@webSecurity.checkUserId(authentication,#username)")
    @GetMapping("/scope/read/{username}")
    public String scopeRead(@PathVariable String username) {
        return "success";
    }

    @ApiOperation(value = "@PreAuthorize 根据【oauth_client_details】表记录的scope范围进行权限控制",
            notes = "需开启注解：@EnableGlobalMethodSecurity" + "(prePostEnabled = true) ")
    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping("/scope/read")
    public String scopeRead() {
        return "success";
    }
    /**
     * 调用接口所需条件: 拥有scope：write
     *
     * @return
     */
    @ApiOperation(value = "@PreAuthorize 根据【oauth_client_details】表记录的scope范围进行权限控制", notes = "需开启注解： " +
            "@EnableGlobalMethodSecurity" + "(prePostEnabled = true) ")
    @PreAuthorize("#oauth2.hasScope('write')")
    @GetMapping("/scope/write")
    public String scopeWrite() {
        return "success";
    }


    @ApiOperation(value = "@PreAuthorize 使用spring-security-oauth2的注解控制权限", notes = "需开启注解： " +
            "@EnableGlobalMethodSecurity" + "(prePostEnabled =  true) ")
    @PreAuthorize("#oauth2.clientHasAnyRole('ROLE_MEMBER')")
    @GetMapping("/authorities/member")
    public String authoritiesMember() {
        return "success";
    }


    @ApiOperation(value = "@PreAuthorize 使用spring-security-oauth2的注解控制权限",
            notes = "需开启注解： @EnableGlobalMethodSecurity(prePostEnabled = true) ")
    @PreAuthorize("#oauth2.clientHasAnyRole('ROLE_ADMIN')")
    @GetMapping("/authorities/admin")
    public String authoritiesAdmin() {
        return "success";
    }
}
