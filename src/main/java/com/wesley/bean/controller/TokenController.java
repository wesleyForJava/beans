package com.wesley.bean.controller;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wesley.bean.TokenResult;
import com.wesley.bean.jpa.TokenInfoJpa;
import com.wesley.bean.jpa.UserInfoJpa;
import com.wesley.bean.pojo.TokenInfoEntity;
import com.wesley.bean.pojo.UserInfoEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping(value="/jwt")
public class TokenController {
	
	    @Autowired
	    private TokenInfoJpa tokenJPA;

	    @Autowired
	    private UserInfoJpa userInfoJPA;
   @RequestMapping(value="/token")
  public TokenResult token(@RequestParam String appId,@RequestParam String appSecret) {
	  TokenResult token =new TokenResult();
	  if(StringHelper.isEmpty(appId)) {
		 token.setFlag(false);
         token.setMsg("appId is not found!");
	  }else if(StringHelper.isEmpty(appSecret)) {
			 token.setFlag(false);
			 token.setMsg("appSecret is not found!");
	  }else {
		  UserInfoEntity userDbInfo=userInfoJPA.findOne(new Specification<UserInfoEntity>() {
			@Override
			public Predicate toPredicate(Root<UserInfoEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.where(cb.equal(root.get("appId"), appId));
				return null;
			}
		});
		  if(null == userDbInfo) {
			     token.setFlag(false);
		         token.setMsg("appId is not found!");
		         //验证appSecret是否存在
		  }else if(new String(userDbInfo.getAppSecret().toString()).equals(appSecret.replace(" ", "+"))) {
			  //!new String(userDbInfo.getAppSecret()).equals(appSecret.replace(" ","+"))
			    token.setFlag(false);
			    token.setMsg("appSecret is not effective!");
		  }else {
			  TokenInfoEntity tokenInfoDbEntity=tokenJPA.findOne(new Specification<TokenInfoEntity>() {

				@Override
				public Predicate toPredicate(Root<TokenInfoEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					query.where(cb.equal(root.get("appId"),appId));
					return null;
				}
			});
	           String newToken=null;
				if(tokenInfoDbEntity==null){
					newToken= createNewToken(appId);
					tokenInfoDbEntity=new TokenInfoEntity();
					tokenInfoDbEntity.setAppId(userDbInfo.getAppId());
					tokenInfoDbEntity.setBuildTime(String.valueOf(System.currentTimeMillis()));
					tokenInfoDbEntity.setToken(newToken.getBytes());
					tokenJPA.save(tokenInfoDbEntity);
				    //tokenDBEntity != null -> 验证是否超时 ->
	                //不超时 -> 直接返回dbToken
	                //超时 -> 生成newToken -> 更新dbToken -> 更新内存Token -> 返回newToken
				}else {
					//判断数据库中token是否过期，如果没有过期不需要更新直接返回数据库中的token即可
                    //数据库中生成时间
					Long buildTime =Long.valueOf(tokenInfoDbEntity.getBuildTime());
					//当前时间
					Long timeNow=System.currentTimeMillis();
					  //如果当前时间 - 数据库中生成时间 < 7200 证明可以正常使用
                    long second = TimeUnit.MILLISECONDS.toSeconds(buildTime - timeNow);
                    if(second>0 && second<7200) {
                    	newToken=new String(tokenInfoDbEntity.getToken());
                    }else {
                    	//生成Token
                    	 newToken = new String(tokenInfoDbEntity.getToken());
                        //更新Token
                    	tokenInfoDbEntity.setToken(newToken.getBytes());
                    	//更新生成的时间
                    	tokenInfoDbEntity.setBuildTime(String.valueOf(System.currentTimeMillis()));
                    	//执行更新
                    	tokenJPA.save(tokenInfoDbEntity);
                    }
				}
				token.setToken(newToken);
		  }
		  
	  }
	return token;
  }
  /**
   * 创建新token
   * @param appId
   * @return
   */
  private String createNewToken(String appId) {
	  //获取当前时间
	  Date now = new Date(System.currentTimeMillis());
	  //过期时间
	  Date expiration=new Date(now.getTime()+7200000);
	return Jwts.builder()
			.setSubject(appId)
			.setIssuedAt(now)
			.setIssuer("Online YAuth Builder")
			.setExpiration(expiration)
			.signWith(SignatureAlgorithm.HS256, "HengYuAuthv1.0.0") //签名算法枚举类，自定义BASE64key
			.compact();
	}
}
