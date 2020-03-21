package dev.cse.imageannotatorbackend.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomAnnotatorDetails implements UserDetails {

	private Annotators annotator;

	public CustomAnnotatorDetails(Annotators annotator) {
		this.annotator = annotator;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("ANNOTATOR"));
	}

	@Override
	public String getPassword() {
		return annotator.getPassword();
	}

	@Override
	public String getUsername() {
		return annotator.getUsername();
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
