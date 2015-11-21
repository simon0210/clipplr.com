package com.clipplr.platform.persistence.mybatis.domain.user;

/**
 * Created by simon on 11/9/15.
 */
public class FacebookUserProfileVO {

    String id;
    String name;
    String firstName;
    String lastName;

    public FacebookUserProfileVO(String id, String name, String firstName, String lastName) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
