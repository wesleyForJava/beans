package com.wesley.bean.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wesley.bean.domain.SysUser;

import net.bytebuddy.asm.Advice.This;

/**
 * 1.配置Realms 
 * Realm是一个Dao，通过它来验证用户身份和权限。这里Shiro不做权限的管理工作，
 * 需要我们自己管理用户权限，只需要从我们的数据源中把用户和用户的角色权限信息取出来交给Shiro即可。
 * Shiro就会自动的进行权限判断。在项目包下建一个ShiroRealm类，继承AuthorizingRealm抽象类。
 * @author pc
 *
 */
public class ShiroRealm extends AuthorizingRealm{
	
	private static Logger logger = LoggerFactory.getLogger(This.class);
    /**
     * 配置权限注入权限
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("===进入权限配置=====");
		SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo();
		SysUser  sysUser = (SysUser) principals.getPrimaryPrincipal();
		//查询所有角色注入控制器
		
		return null;
	}

	/**
	 * 用户验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

}
