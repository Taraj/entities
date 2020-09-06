package tk.tarajki.entities.swagger.plugins;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

import static springfox.bean.validators.plugins.Validators.annotationFromBean;
import static springfox.bean.validators.plugins.Validators.extractAnnotation;


@Component
public class StringAnnotationPlugin implements ModelPropertyBuilderPlugin {
    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }

    @Override
    public void apply(ModelPropertyContext context) {
        extractAnnotation(context, NotBlank.class).ifPresent(annotation ->
                context.getSpecificationBuilder()
                        .required(true)
                        .allowEmptyValue(false)
                        .vendorExtensions(List.of(
                                new StringVendorExtension("allowBlankValue", "false")
                        ))
        );

        extractAnnotation(context, NotEmpty.class).ifPresent(annotation ->
                context.getSpecificationBuilder()
                        .required(true)
                        .allowEmptyValue(false)
        );

        extractAnnotation(context, Length.class).ifPresent(annotation ->{
                System.out.println("context.getAnnotatedElement()");
                context.getSpecificationBuilder()
                        .stringFacet(s -> {
                            s.minLength(annotation.min());
                            s.maxLength(annotation.max());
                        });}
        );

        extractAnnotation(context, Email.class).ifPresent(annotation ->
                context.getSpecificationBuilder()
                        .example("mail@mail.com")
                        .stringFacet(s ->
                                s.pattern("email")
                        )
        );
    }
}