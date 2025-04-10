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
        if (this.image.getIdentifierName() == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
        }

        if (this.image.getIdentifierName().isEmpty() || this.image.getIdentifierName().isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
        }

        if (this.image.getIdentifierName().length() <= MIN_LENGTH || this.image.getIdentifierName().length() > MAX_LENGTH) {
            this.validationHandler().append(new Error("'name' should be between 2 and 255 characters"));
        }
    }

    private void checkImageList() {
        final var list = this.image.getImageList();

        if (list != null && list.contains(null)) {
            this.validationHandler().append(new Error("'imageList' should not contain null"));
        }

        if (list != null && list.isEmpty()) {
            this.validationHandler().append(new Error("'imageList' should not be empty"));
        }

        for (String item: this.image.getImageList()) {
            if(item.isEmpty() && list != null) {
                this.validationHandler().append(new Error("'imageList' should not contain empty item"));
            }
        }
    }
}
