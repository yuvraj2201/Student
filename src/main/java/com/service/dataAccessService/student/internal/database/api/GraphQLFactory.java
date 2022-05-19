package com.service.dataAccessService.student.internal.database.api;


import com.service.dataAccessService.student.internal.dataFetcher.GraphQLDataFetchers;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.core.io.ResourceResolver;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Factory
public class GraphQLFactory {
    private static final Logger logger = LoggerFactory.getLogger(GraphQLFactory.class);
    @Bean
    @Singleton
    GraphQL graphQL(ResourceResolver resourceResolver, GraphQLDataFetchers graphQLDataFetchers) {
        SchemaParser schemaParser = new SchemaParser();
        SchemaGenerator schemaGenerator = new SchemaGenerator();

        logger.info("Parse the Schema");
        TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
        typeRegistry.merge(schemaParser.parse(new BufferedReader(new InputStreamReader(
                resourceResolver.getResourceAsStream("classpath:schema.graphqls").get()))));

        logger.info("Creating the runtime wiring");
        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("studentById", graphQLDataFetchers.getStudentByIdDataFetcher()))
                .build();

        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);

        logger.info("runtime wiring completed");
        return GraphQL.newGraphQL(graphQLSchema).build();
    }

}
