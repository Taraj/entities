package tk.tarajki.entities.swagger.plugins;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

import static springfox.bean.validators.plugins.Validators.extractAnnotation;

@Component
public class ApiModelPropertyAnnotationPlugin implements ModelPropertyBuilderPlugin {
    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }

    @Override
    public void apply(ModelPropertyContext context) {
        extractAnnotation(context, ApiModelProperty.class).ifPresent(annotation ->
                context.getSpecificationBuilder().readOnly(annotation.accessMode() == ApiModelProperty.AccessMode.READ_ONLY)
        );
    }
}