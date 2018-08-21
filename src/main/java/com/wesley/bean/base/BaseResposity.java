package com.wesley.bean.base;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
/**
 * Spring开源程序猿在命名规则上应该是比较严格的，从名字上我们几乎就可以判断出用途，
 * 这个注解如果配置在继承了JpaRepository接口以及其他SpringDataJpa内部的接口的子接口时，
 * 子接口不被作为一个Repository创建代理实现类。
 * @author pc
 *
 * @param <T>
 * @param <AA>
 */
@NoRepositoryBean
public interface BaseResposity<T, AA extends Serializable> extends JpaRepository<T, AA> {

}
