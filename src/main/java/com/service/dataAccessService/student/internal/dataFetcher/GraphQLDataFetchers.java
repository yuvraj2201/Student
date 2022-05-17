package com.service.dataAccessService.student.internal.dataFetcher;

import com.service.dataAccessService.student.internal.Student;
import com.service.dataAccessService.student.internal.database.internal.StudentRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import jakarta.inject.Inject;

import java.util.List;

public class GraphQLDataFetchers {

    @Inject
    StudentRepository studentRepository;

    DataFetcher productsDataFetcher = new DataFetcher<List<Student>>(){

        @Override
        public List<Student> get(DataFetchingEnvironment environment) throws Exception {
            List<Student> students;
            return null;
        }
    };


}
