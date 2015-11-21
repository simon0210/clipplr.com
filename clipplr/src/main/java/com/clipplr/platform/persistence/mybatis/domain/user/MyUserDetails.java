package com.clipplr.platform.persistence.mybatis.domain.user;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

/**
 * Created by simon on 15. 6. 25.
 */
public interface MyUserDetails extends UserDetails {

    @com.fasterxml.jackson.annotation.JsonIgnore
    public Long getId();

    // UserDetails begins

    @com.fasterxml.jackson.annotation.JsonIgnore
    public String getPassword();

    @com.fasterxml.jackson.annotation.JsonIgnore
    public boolean isAccountNonExpired();

    @com.fasterxml.jackson.annotation.JsonIgnore
    public boolean isAccountNonLocked();

    @com.fasterxml.jackson.annotation.JsonIgnore
    public boolean isCredentialsNonExpired();

    @com.fasterxml.jackson.annotation.JsonIgnore
    public boolean isEnabled();

    // UserDetails ends

    public Integer getHierarchyId();

    public Short getMaxLoginAttempts();

    public Short getLoginAttempts();

    public String getScreenName();

    public String getPhoneNumber();

    public String getEmailAddress();

    public Date getCreatedAt();

    public void setPassword(String password);

    public Integer getSessionTimeOut();

}
