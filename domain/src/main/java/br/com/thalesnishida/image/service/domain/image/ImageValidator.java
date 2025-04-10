package br.com.thalesnishida.image.service.domain.image;

import br.com.thalesnishida.image.service.domain.validation.Error;
import br.com.thalesnishida.image.service.domain.validation.ValidationHandler;
import br.com.thalesnishida.image.service.domain.validation.Validator;

public class ImageValidator extends Validator {

    private final Image image;

    public ImageValidator(final Image aImage, final ValidationHandler aHandler) {
        super(aHandler);
        this.image = aImage;
    }

    @Override
    public void validate() {
        if (this.image.getIdentifierName() == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
        }
    }
}
