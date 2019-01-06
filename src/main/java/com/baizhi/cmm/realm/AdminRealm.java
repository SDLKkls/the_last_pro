package com.baizhi.cmm.realm;

import com.baizhi.cmm.entity.Admin;
import com.baizhi.cmm.entity.Power;
import com.baizhi.cmm.entity.Role;
import com.baizhi.cmm.mapper.AdminMapper;
import com.baizhi.cmm.mapper.PowerMapper;
import com.baizhi.cmm.mapper.RoleMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AdminRealm extends AuthorizingRealm {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PowerMapper powerMapper;

    /*授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        List<Admin> admins = adminMapper.select(new Admin(primaryPrincipal));
        Admin admin = admins.get(0);
        List<Role> roleList = roleMapper.selectAllRole(admin.getId());
        List<String> roles = new CopyOnWriteArrayList<>();
        for (Role role : roleList) {
            roles.add(role.getName());
        }
        List<Power> powerList = powerMapper.selectAllPower(admin.getId());
        List<String> powers = new CopyOnWriteArrayList<>();
        for (Power power : powerList) {
            powers.add(power.getName());
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(powers);
        return authorizationInfo;
    }

    /*认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        List<Admin> admins = adminMapper.select(new Admin(principal));
        Admin admin = admins.get(0);
        AuthenticationInfo authenticationInfo = null;
        if (admin != null) {
            authenticationInfo = new SimpleAuthenticationInfo(admin.getUsername(), admin.getPassword(), this.getName());
        }
        return authenticationInfo;
    }
}
