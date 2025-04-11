package br.com.thalesnishida.image.service.domain.image;

import br.com.thalesnishida.image.service.domain.validation.Error;
import br.com.thalesnishida.image.service.domain.validation.ValidationHandler;
import br.com.thalesnishida.image.service.domain.validation.Validator;

public class ImageValidator extends Validator {

    public static final int MIN_LENGTH = 2;
    public static final int MAX_LENGTH = 255;
    private final Image image;

    public ImageValidator(final Image aImage, final ValidationHandler aHandler) {
        super(aHandler);
        this.image = aImage;
    }

    @Override
    public void validate() {
        checkIdentifierName();
        checkImageList();
    }

    private void checkIdentifierName() {
        final var identifierName = this.image.getIdentifierName();

        if (identifierName == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (identifierName.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        if (identifierName.length() <= MIN_LENGTH || identifierName.length() > MAX_LENGTH) {
            this.validationHandler().append(new Error("'name' should be between 2 and 255 characters"));
        }
    }

    private void checkImageList() {
        final var list = this.image.getImageList();

        if (list == null) {
            this.validationHandler().append(new Error("'imageList' should not contain null"));
            return;
        }

        if (list.isEmpty()) {
            this.validationHandler().append(new Error("'imageList' should not be empty"));
            return;
        }

        for (String item: this.image.getImageList()) {
            if(item.isEmpty()) {
                this.validationHandler().append(new Error("'imageList' should not contain empty item"));
                return;
            }
        }
    }
}
