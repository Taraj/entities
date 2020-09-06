package tk.tarajki.entities.swagger.plugins;

import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

import java.math.BigDecimal;

import static springfox.bean.validators.plugins.Validators.annotationFromBean;
import static springfox.bean.validators.plugins.Validators.extractAnnotation;

@Component
public class NumberAnnotationPlugin implements ModelPropertyBuilderPlugin {
    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }

    @Override
    public void apply(ModelPropertyContext context) {
        extractAnnotation(context, Range.class).ifPresent(annotation ->
                context.getSpecificationBuilder()
                        .numericFacet(n ->{
                            n.minimum(BigDecimal.valueOf(annotation.min()));
                            n.maximum(BigDecimal.valueOf(annotation.max()));
                        }).build()
        );
    }
}