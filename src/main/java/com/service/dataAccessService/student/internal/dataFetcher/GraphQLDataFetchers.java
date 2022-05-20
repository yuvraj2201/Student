package com.service.dataAccessService.student.internal.dataFetcher;

import com.service.dataAccessService.student.internal.Identity;
import com.service.dataAccessService.student.internal.Student;
import com.service.dataAccessService.student.internal.database.constant.Committee;
import com.service.dataAccessService.student.internal.database.constant.Course;
import graphql.com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import io.micronaut.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Bean
public class GraphQLDataFetchers {

    private static final Logger logger = LoggerFactory.getLogger(GraphQLDataFetchers.class);

    private static Map<String, Student> studentMap = new HashMap<>() {
        {
            put("1", new Student("1", "Yuvraj", new Identity("101", 26, Course.SCIENCE, Committee.TRAINING)));
            put("2", new Student("2", "Yuvraj", new Identity("102", 26, Course.SCIENCE, Committee.TRAINING)));
        }
    };

    public DataFetcher getStudentByIdDataFetcher() {
        logger.info("get StudentByIdDataFetcher");
        return dataFetchingEnvironment -> {
            String studentId = dataFetchingEnvironment.getArgument("id");
            logger.info("Fetched student id : " + studentId);
            return studentMap.get(studentId);
        };
    }
}
