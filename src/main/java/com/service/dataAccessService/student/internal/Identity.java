package com.service.dataAccessService.student.internal;

import com.service.dataAccessService.student.internal.database.constant.Committee;
import com.service.dataAccessService.student.internal.database.constant.Course;
import io.micronaut.core.annotation.Introspected;

@Introspected
public class Identity {
    private String id;
    private int birthday;
    private Course course;
    private Committee committee;

    public Identity(String id, int birthday, Course course, Committee committee) {
        this.id = id;
        this.birthday = birthday;
        this.course = course;
        this.committee = committee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Committee getCommittee() {
        return committee;
    }

    public void setCommittee(Committee committee) {
        this.committee = committee;
    }
}
