package com.yumyapps.jwt.config;

import graphql.scalars.ExtendedScalars;
import graphql.scalars.java.JavaPrimitives;
import graphql.schema.idl.RuntimeWiring;
import graphql.validation.rules.OnValidationErrorStrategy;
import graphql.validation.rules.ValidationRules;
import graphql.validation.schemawiring.ValidationSchemaWiring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import javax.validation.constraints.NotNull;

@Configuration
public class ScalarConfig implements RuntimeWiringConfigurer {


    @Override
    public void configure(RuntimeWiring.Builder builder) {
        builder.scalar(JavaPrimitives.GraphQLLong)
                .scalar(ExtendedScalars.Date)
                .directive("@NotEmpty", schemaWiring())
                .directive("@NotBlank", schemaWiring())
                .build();
    }


    @Bean
    public ValidationSchemaWiring schemaWiring() {
        var validationRules = ValidationRules.newValidationRules()
                .onValidationErrorStrategy(OnValidationErrorStrategy.RETURN_NULL)
                .build();
        return new ValidationSchemaWiring(validationRules);

    }
}

