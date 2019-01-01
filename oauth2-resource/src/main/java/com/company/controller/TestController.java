package com.company.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * 测试接口
 */
@RestController
@RequestMapping("/api")
public class TestController {

    @RolesAllowed({"MEMBER", "ROLE_MEMBER"})
    @RequestMapping("/roleAllowed/member")
    public String roleMemberRolesAllowed() {
        return "success";
    }

    @RolesAllowed({"ADMIN", "ROLE_ADMIN"})
    @RequestMapping("/roleAllowed/admin")
    public String roleAdminRolesAllowed() {
        return "success";
    }

    @Secured({"MEMBER", "ROLE_MEMBER"})
    @RequestMapping("/secured/member")
    public String roleMember() {
        return "success";
    }


    @Secured({"ADMIN", "ROLE_ADMIN"})
    @RequestMapping("/secured/admin")
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
    @RequestMapping("/scope/read/{username}")
    public String read(@PathVariable String username) {
        return "success";
    }

    /**
     * 调用接口所需条件: 拥有scope：write
     * @return
     */
    @PreAuthorize("#oauth2.hasScope('write')")
    @RequestMapping("/scope/write")
    public String write() {
        return "success";
    }

    /**
     * 调用接口所需角色：MEMBER
     * @return
     */
    @PreAuthorize("#oauth2.clientHasAnyRole('MEMBER')")
    @RequestMapping("/authorities/member")
    public String member() {
        return "success";
    }
    /**
     * 调用接口所需角色：ADMIN
     * @return
     */
    @PreAuthorize("#oauth2.clientHasAnyRole('ADMIN')")
    @RequestMapping("/authorities/admin")
    public String admin() {
        return "success";
    }
}
