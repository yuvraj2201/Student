package com.service.dataAccessService.student.internal.database.internal;

import com.service.dataAccessService.student.internal.Student;
import io.micronaut.data.annotation.Repository;


import java.util.List;
@Repository
public interface StudentRepository  {

     List<Student> findAllStudents();

     Student findStudentById(String id);




}
