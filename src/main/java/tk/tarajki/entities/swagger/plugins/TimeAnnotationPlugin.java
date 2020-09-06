package tk.tarajki.entities.swagger.plugins;

import org.springframework.stereotype.Component;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

import javax.validation.constraints.*;

import java.util.List;

import static springfox.bean.validators.plugins.Validators.annotationFromBean;
import static springfox.bean.validators.plugins.Validators.extractAnnotation;

@Component
public class TimeAnnotationPlugin implements ModelPropertyBuilderPlugin {
    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }

    @Override
    public void apply(ModelPropertyContext context) {
        extractAnnotation(context, Future.class).ifPresent(annotation ->
                context.getSpecificationBuilder().vendorExtensions(List.of(new StringVendorExtension("allowedDate", "Future")))
        );
        extractAnnotation(context, Past.class).ifPresent(annotation ->
                context.getSpecificationBuilder().vendorExtensions(List.of(new StringVendorExtension("allowedDate","Past")))
        );
        extractAnnotation(context, FutureOrPresent.class).ifPresent(annotation ->
                context.getSpecificationBuilder().vendorExtensions(List.of(new StringVendorExtension("allowedDate","FutureOrPresent")))
        );
        extractAnnotation(context, PastOrPresent.class).ifPresent(annotation ->
                context.getSpecificationBuilder().vendorExtensions(List.of(new StringVendorExtension("allowedDate","PastOrPresent")))
        );
    }
}