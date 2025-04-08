package br.com.thalesnishida.image.service.domain.image;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ImageTest {

    @Test
    public void givenAValidParams_whenCallNewImage_thenInstantiateAImage() {
        final var expectedIdentifierName  = "123456";
        final var expectedListImage = List.of("teste.img", "test2.img");

        final var actualImage =
                Image.newImage(expectedIdentifierName,expectedListImage);

        Assertions.assertNotNull(actualImage);
        Assertions.assertNotNull(actualImage.getId());
        Assertions.assertEquals(expectedIdentifierName, actualImage.getIdentifierName());
        Assertions.assertEquals(expectedListImage, actualImage.getListImage());
        Assertions.assertNotNull(actualImage.getCreatedAt());
        Assertions.assertNotNull(actualImage.getUpdatedAt());
    }
}
