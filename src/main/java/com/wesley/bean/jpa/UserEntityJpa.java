package com.wesley.bean.jpa;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.wesley.bean.pojo.UserEntity;

@Transactional
public interface UserEntityJpa extends JpaRepository<UserEntity, Long>,JpaSpecificationExecutor<UserEntity>{
	  @Query(value="select * From t_user u where u.t_age >?1",nativeQuery=true)
		 public List<UserEntity> nativeQuery(int age);
	  /**
	   * 根据用户密码删除一条数据
	   */
	  @Modifying
	  @Query(value="delete from t_user where t_name=?1 and t_pwd=?2",nativeQuery=true)
	  public void deleteByNameAndPassword(String name,String password);
}
