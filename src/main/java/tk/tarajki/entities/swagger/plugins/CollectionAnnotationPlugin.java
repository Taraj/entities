package tk.tarajki.entities.swagger.plugins;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

import javax.validation.constraints.Size;

import static springfox.bean.validators.plugins.Validators.extractAnnotation;

@Component
public class CollectionAnnotationPlugin implements ModelPropertyBuilderPlugin {
    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }

    @Override
    public void apply(ModelPropertyContext context) {
        extractAnnotation(context, Size.class).ifPresent(annotation -> {
            context.getSpecificationBuilder().collectionFacet(c -> {
                c.maxItems(annotation.max());
                c.minItems(annotation.min());
            });
        });
    }
}