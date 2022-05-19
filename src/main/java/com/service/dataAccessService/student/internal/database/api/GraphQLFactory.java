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

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Factory
public class GraphQLFactory {

    @Bean
    @Singleton
    GraphQL graphQL(ResourceResolver resourceResolver, GraphQLDataFetchers graphQLDataFetchers) {
        SchemaParser schemaParser = new SchemaParser();
        SchemaGenerator schemaGenerator = new SchemaGenerator();

        // Parse the schema.
        TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
        typeRegistry.merge(schemaParser.parse(new BufferedReader(new InputStreamReader(
                resourceResolver.getResourceAsStream("classpath:schema.graphqls").get()))));

        // Create the runtime wiring.
        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("studentById",graphQLDataFetchers.getStudentByIdDataFetcher()))
                        .build();

        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);

        return GraphQL.newGraphQL(graphQLSchema).build();
    }

}
