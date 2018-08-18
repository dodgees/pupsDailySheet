package com.mindcanary.domain.pups;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Erik on 8/18/2018.
 */

@Table
public class Pup {

    private Long id;
    private String name;
    private Date birthDate;
    private Long classroomId;
    private ArrayList<Long> guardianIds;
    private ArrayList<Long> teacherIds;

    public Pup(Long id, String name, Date birthDate, Long classroomId, ArrayList<Long> guardianIds, ArrayList<Long> teacherIds) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.classroomId = classroomId;
        this.guardianIds = guardianIds;
        this.teacherIds = teacherIds;
    }

    public Pup() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public ArrayList<Long> getGuardianIds() {
        return guardianIds;
    }

    public void setGuardianIds(ArrayList<Long> guardianIds) {
        this.guardianIds = guardianIds;
    }

    public ArrayList<Long> getTeacherIds() {
        return teacherIds;
    }

    public void setTeacherIds(ArrayList<Long> teacherIds) {
        this.teacherIds = teacherIds;
    }

    @Override
    public String toString() {
        return "Pup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", classroomId=" + classroomId +
                ", guardianIds=" + guardianIds +
                ", teacherIds=" + teacherIds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pup pup = (Pup) o;

        if (id != null ? !id.equals(pup.id) : pup.id != null) return false;
        if (name != null ? !name.equals(pup.name) : pup.name != null) return false;
        if (birthDate != null ? !birthDate.equals(pup.birthDate) : pup.birthDate != null) return false;
        if (classroomId != null ? !classroomId.equals(pup.classroomId) : pup.classroomId != null) return false;
        if (guardianIds != null ? !guardianIds.equals(pup.guardianIds) : pup.guardianIds != null) return false;
        return teacherIds != null ? teacherIds.equals(pup.teacherIds) : pup.teacherIds == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (classroomId != null ? classroomId.hashCode() : 0);
        result = 31 * result + (guardianIds != null ? guardianIds.hashCode() : 0);
        result = 31 * result + (teacherIds != null ? teacherIds.hashCode() : 0);
        return result;
    }
}

