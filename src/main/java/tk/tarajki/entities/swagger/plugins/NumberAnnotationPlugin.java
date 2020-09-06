package tk.tarajki.entities.swagger.plugins;

import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

import javax.validation.constraints.Negative;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

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
                        .numericFacet(n -> {
                            n.minimum(BigDecimal.valueOf(annotation.min()));
                            n.maximum(BigDecimal.valueOf(annotation.max()));
                        })
        );
        extractAnnotation(context, PositiveOrZero.class).ifPresent(annotation ->
                context.getSpecificationBuilder()
                        .numericFacet(n -> {
                            n.minimum(BigDecimal.valueOf(0));
                            n.exclusiveMinimum(false);
                        })
        );
        extractAnnotation(context, Positive.class).ifPresent(annotation ->
                context.getSpecificationBuilder()
                        .numericFacet(n -> {
                            n.minimum(BigDecimal.valueOf(0));
                            n.exclusiveMinimum(true);
                        })
        );
        extractAnnotation(context, Negative.class).ifPresent(annotation ->
                context.getSpecificationBuilder()
                        .numericFacet(n -> {
                            n.maximum(BigDecimal.valueOf(0));
                            n.exclusiveMinimum(true);
                        })
        );
        extractAnnotation(context, NegativeOrZero.class).ifPresent(annotation ->
                context.getSpecificationBuilder()
                        .numericFacet(n -> {
                            n.maximum(BigDecimal.valueOf(0));
                            n.exclusiveMinimum(false);
                        })
        );
    }
}