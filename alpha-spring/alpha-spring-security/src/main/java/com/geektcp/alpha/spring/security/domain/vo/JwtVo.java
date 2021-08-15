package com.geektcp.alpha.spring.security.domain.vo;

import com.google.common.collect.Lists;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class JwtVo implements UserDetails {

	private Long id;

	private String account;

	private String password;

	private String cellphone;

	private String userName;

	private String nickName;

	private Integer type;

	private Integer isCassUser;

	private String companyId;

	private String companyName;

	private String companyShortName;

	private String tokenType;

	private String token;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Lists.newArrayList();
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
