package com.service.dataAccessService.student.internal;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Student {
    private String id;
    private String name;
    private Identity identity;

    public Student(String id, String name, Identity identity) {
        this.id = id;
        this.name = name;
        this.identity = identity;
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

    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }
}
