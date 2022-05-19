package com.service.dataAccessService.student.internal.dataFetcher;

import com.service.dataAccessService.student.internal.Identity;
import com.service.dataAccessService.student.internal.Student;
import com.service.dataAccessService.student.internal.database.constant.Committee;
import com.service.dataAccessService.student.internal.database.constant.Course;
import graphql.com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import io.micronaut.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Bean
public class GraphQLDataFetchers {

    private static List<Map<String, Student>> students = Arrays.asList(
            ImmutableMap.of("1", new Student("1", "Yuvraj",
                    new Identity("101", 26, Course.SCIENCE, Committee.TRAINING))),
            ImmutableMap.of("2", new Student("1", "Ashwin",
                    new Identity("102", 25, Course.COMMERCE, Committee.EVENT))),
            ImmutableMap.of("3", new Student("3", "Kapil",
                    new Identity("103", 27, Course.BIOLOGY, Committee.FINANCE)))
    );

    public DataFetcher getStudentByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String studentId = dataFetchingEnvironment.getArgument("id");
            return students
                    .stream()
                    .filter(student -> student.get("id").equals(studentId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getAllStudentListDataFetcher() {
        return DataFetchingEnvironment -> {
            return students;
        };
    }
}
