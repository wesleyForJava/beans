package com.wesley.bean.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
@Entity
@Table(name = "users")
@Data
public class UserAuthor implements Serializable,UserDetails{
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "u_id")
    private Long id;
    @Column(name = "u_username")
    private String username;
    @Column(name = "u_password")
    private String password;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
    		name="user_roles",
    		joinColumns= {
    			@JoinColumn(name="ur_user_id")
    		},
    		inverseJoinColumns={
    			@JoinColumn(name="ur_role_id")
    		})
    private List<RoleEntity> roles;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();
		List<RoleEntity> roles = getRoles();
		for (RoleEntity role : roles) {
			auths.add(new SimpleGrantedAuthority(role.getFlag()));
		}
		return auths;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
