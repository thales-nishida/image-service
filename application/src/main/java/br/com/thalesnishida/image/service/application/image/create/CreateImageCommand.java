package br.com.thalesnishida.image.service.application.image.create;

import java.util.List;

public record CreateImageCommand(
        String identifierName,
        List<String> imageList
) {
    public static CreateImageCommand with(
            final String aIdentifierName,
            final List<String> aImageList
    ) {
        return new CreateImageCommand(aIdentifierName, aImageList);
    }

}
