package com.example.Category.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

//
//@OpenAPIDefinition(
//        info = @Info(
//                contact = @Contact(
//                        name = "User1234",
//                        email="contact@usercoding.com",
//                        url="https://userCoding.com/course"
//                ),
//                description = "OpenApi documention for Spring Security",
//                title = "OpenApi specification - User1234",
//                version = "1.0",
//                license = @License(
//                        name = "Licence name",
//                        url = "http://some-url.com"
//                ),
//                termsOfService = "Terms of service"
//        ),
//        servers= {
//                @Server(
//                        description = "Local ENW",
//                        url = "http://localhost:8080"
//                ),
//                @Server(
//                        description = "PROD ENW",
//                        url = "https://userCoding.com/course"
//                )
//        },
//        security={
//                @SecurityRequirement(
//                        name="bearerAuth"
//                )
//        }
//)
@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenAipConfig {

}
