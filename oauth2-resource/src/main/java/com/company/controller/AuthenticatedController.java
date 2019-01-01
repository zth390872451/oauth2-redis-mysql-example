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

/**
 * 测试接口
 */
@RestController
@RequestMapping("/authenticated")
@Api("测试用户权限")
public class AuthenticatedController {

    @ApiOperation(value = "@RolesAllowed注解测试", notes = "实现的getAuthoritie里面的role都必须要有ROLE_前缀")
    @RolesAllowed({"MEMBER", "ROLE_MEMBER"})
    @GetMapping("/roleAllowed/member")
    public String roleMemberRolesAllowed() {
        return "success";
    }

    @ApiOperation(value = "@RolesAllowed注解测试", notes = "实现的getAuthoritie里面的role都必须要有ROLE_前缀")
    @RolesAllowed({"ADMIN", "ROLE_ADMIN"})
    @GetMapping("/roleAllowed/admin")
    public String roleAdminRolesAllowed() {
        return "success";
    }

    @Secured({"MEMBER", "ROLE_MEMBER"})
    @GetMapping("/secured/member")
    public String roleMember() {
        return "success";
    }


    @Secured({"ADMIN", "ROLE_ADMIN"})
    @GetMapping("/secured/admin")
    public String roleAdmin() {
        return "success";
    }

    /**
     * 调用接口，修改用户数据。
     *  条件:认证成功后，再检测 Access Token 所属用户与要修改的用户名是否相同
     * @param username
     * @return
     */
    @PreAuthorize("@webSecurity.checkUserId(authentication,#username)")
    @GetMapping("/scope/read/{username}")
    public String read(@PathVariable String username) {
        return "success";
    }

    /**
     * 调用接口所需条件: 拥有scope：write
     * @return
     */
    @PreAuthorize("#oauth2.hasScope('write')")
    @GetMapping("/scope/write")
    public String write() {
        return "success";
    }

    /**
     * 调用接口所需角色：MEMBER
     * @return
     */
    @PreAuthorize("#oauth2.clientHasAnyRole('MEMBER')")
    @GetMapping("/authorities/member")
    public String member() {
        return "success";
    }
    /**
     * 调用接口所需角色：ADMIN
     * @return
     */
    @PreAuthorize("#oauth2.clientHasAnyRole('ADMIN')")
    @GetMapping("/authorities/admin")
    public String admin() {
        return "success";
    }
}
