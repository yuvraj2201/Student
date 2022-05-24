package com.service.dataAccessService.student.internal.dataFetcher;

import com.service.dataAccessService.student.internal.Identity;
import com.service.dataAccessService.student.internal.Student;
import com.service.dataAccessService.student.internal.database.constant.Committee;
import com.service.dataAccessService.student.internal.database.constant.Course;
import graphql.schema.DataFetcher;
import io.micronaut.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Bean
public class GraphQLDataFetchers {

    private static final Logger logger = LoggerFactory.getLogger(GraphQLDataFetchers.class);

    private static Map<String, Student> studentMap = new HashMap<>() ;

    public DataFetcher getStudentByIdDataFetcher() {
        logger.info("get StudentByIdDataFetcher");
        return dataFetchingEnvironment -> {
            String studentId = dataFetchingEnvironment.getArgument("id");
            logger.info("Fetched student id : " + studentId);
            return studentMap.get(studentId);
        };
    }

    public DataFetcher setStudentDataFetcher() {
        return dataFetchingEnvironment -> {
            Student student = new Student();
            student.setId(dataFetchingEnvironment.getArgument("id"));
            student.setName(dataFetchingEnvironment.getArgument("name"));
            student.setIdentity(dataFetchingEnvironment.getArgumentOrDefault("identity",null));
            Map<String, Student> studentDetail = new HashMap<>();
            studentDetail.put(student.getId(),student);
            return studentDetail;
        };
    }
}
