package com.clipplr.platform.persistence.mybatis.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by simon on 7/5/15.
 */
@AutoProperty
public class PlatformUser implements MyUserDetails, Serializable {

    private static final long serialVersionUID = -4744270092422183205L;

    @JsonIgnore
    private Long id;
    private Integer labelId;
    private String username;
    private String screenName;
    private String password;
    private Long financialAccountId;
    private Integer hierarchyId;
    private Locale locale;
    private TimeZone timeZone;
    private String phoneNumber;
    private String emailAddress;
    private Boolean emailIsVerified;
    private Boolean isEnabled;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isNeverDeposited;
    private Boolean isTutorial;
    private Short loginAttempts;
    private Short securityAnswerAttempts;
    private Date createdAt;
    private String signUpIp;
    private Integer sessionTimeOut;
    private Date lockedBegins;
    private Date lockedEnds;
    private BigDecimal rakebackRatio;
    private Long bonusCodeId;
    private String gpID;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName == null ? null : screenName.trim();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Long getFinancialAccountId() {
        return financialAccountId;
    }

    public void setFinancialAccountId(Long financialAccountId) {
        this.financialAccountId = financialAccountId;
    }

    public Integer getHierarchyId() {
        return hierarchyId;
    }

    public void setHierarchyId(Integer hierarchyId) {
        this.hierarchyId = hierarchyId;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress == null ? null : emailAddress.trim();
    }

    public Boolean getEmailIsVerified() {
        return emailIsVerified;
    }

    public void setEmailIsVerified(Boolean emailIsVerified) {
        this.emailIsVerified = emailIsVerified;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Boolean getIsAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setIsAccountNonExpired(Boolean isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
    }

    public Boolean getIsAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setIsAccountNonLocked(Boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    public Boolean getIsCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setIsCredentialsNonExpired(Boolean isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    public Boolean getIsNeverDeposited() {
        return isNeverDeposited;
    }

    public void setIsNeverDeposited(Boolean isNeverDeposited) {
        this.isNeverDeposited = isNeverDeposited;
    }

    public Boolean getIsTutorial() {
        return isTutorial;
    }

    public void setIsTutorial(Boolean isTutorial) {
        this.isTutorial = isTutorial;
    }

    public Short getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(Short loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public Short getSecurityAnswerAttempts() {
        return securityAnswerAttempts;
    }

    public void setSecurityAnswerAttempts(Short securityAnswerAttempts) {
        this.securityAnswerAttempts = securityAnswerAttempts;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getSignUpIp() {
        return signUpIp;
    }

    public void setSignUpIp(String signUpIp) {
        this.signUpIp = signUpIp == null ? null : signUpIp.trim();
    }

    public Integer getSessionTimeOut() {
        return sessionTimeOut;
    }

    public void setSessionTimeOut(Integer sessionTimeOut) {
        this.sessionTimeOut = sessionTimeOut;
    }

    public Date getLockedBegins() {
        return lockedBegins;
    }

    public void setLockedBegins(Date lockedBegins) {
        this.lockedBegins = lockedBegins;
    }

    public Date getLockedEnds() {
        return lockedEnds;
    }

    public void setLockedEnds(Date lockedEnds) {
        this.lockedEnds = lockedEnds;
    }

    public BigDecimal getRakebackRatio() {
        return rakebackRatio;
    }

    public void setRakebackRatio(BigDecimal rakebackRatio) {
        this.rakebackRatio = rakebackRatio;
    }

    public Long getBonusCodeId() {
        return bonusCodeId;
    }

    public void setBonusCodeId(Long bonusCodeId) {
        this.bonusCodeId = bonusCodeId;
    }

    public String getGpID() {
        return gpID;
    }

    public void setGpID(String gpID) {
        this.gpID = gpID;
    }

    /**
     * UserDetails implementations
     */

    public PlatformUser() {
        super();

        authorities = new ArrayList<>();
    }

    public static short MAX_LOGIN_ATTEMPTS = 10;

    @JsonIgnore
    public static short MAX_SECURITY_ANSWER_ATTEMPTS = 5;

    @JsonIgnore
    private List<GrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return getIsAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return getIsAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return getIsAccountNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return getIsEnabled();
    }

    @Override
    @JsonIgnore
    public Short getMaxLoginAttempts() {
        return MAX_LOGIN_ATTEMPTS;
    }

    public void loginFailed() {
        this.loginAttempts++;
    }

    public void setAccountNonExpired(Boolean isAccountNonExpired) {
        setIsAccountNonExpired(isAccountNonExpired);
    }

    public void setAccountNonLocked(Boolean isAccountNonLocked) {
        setIsAccountNonLocked(isAccountNonLocked);
    }

    public void setCredentialsNonExpired(Boolean isCredentialsNonExpired) {
        setIsCredentialsNonExpired(isCredentialsNonExpired);
    }

    public void setEnabled(Boolean isEnabled) {
        setIsEnabled(isEnabled);
    }

    @Override
    public boolean equals(Object o) {
        return Pojomatic.equals(this, o);
    }

    @Override
    public int hashCode() {
        return Pojomatic.hashCode(this);
    }

    @Override
    public String toString() {
        return Pojomatic.toString(this);
    }
}
