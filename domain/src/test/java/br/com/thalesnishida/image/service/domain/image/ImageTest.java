package br.com.thalesnishida.image.service.domain.image;

import br.com.thalesnishida.image.service.domain.exceptions.DomainException;
import br.com.thalesnishida.image.service.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ImageTest {

    @Test
    public void givenAValidParams_whenCallNewImage_thenInstantiateAImage() {
        final var expectedIdentifierName = "123456";
        final var expectedListImage = List.of("teste.img", "test2.img");

        final var actualImage =
                Image.newImage(expectedIdentifierName, expectedListImage);

        Assertions.assertNotNull(actualImage);
        Assertions.assertNotNull(actualImage.getId());
        Assertions.assertEquals(expectedIdentifierName, actualImage.getIdentifierName());
        Assertions.assertEquals(expectedListImage, actualImage.getImageList());
        Assertions.assertNotNull(actualImage.getCreatedAt());
        Assertions.assertNotNull(actualImage.getUpdatedAt());
    }


    @Test
    public void givenAInvalidNullIdentifierName_whenCallNewImage_thenShouldReturnError() {
        final String expectedIdentifierName = null;
        final var expectedListImage = List.of("teste.img", "test2.img");
        final var expectErrorMessage = "'name' should not be null";
        final var expectErrorCount = 1;

        final var actualImage =
                Image.newImage(expectedIdentifierName, expectedListImage);


        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualImage.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidEmptyOrBlankIdentifierName_whenCallNewImage_thenShouldReturnDomainException() {
        final String expectedIdentifierName = "";
        final var expectedListImage = List.of("teste.img", "test2.img");
        final var expectErrorMessage = "'name' should not be empty";
        final var expectErrorCount = 1;

        final var actualImage =
                Image.newImage(expectedIdentifierName, expectedListImage);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualImage.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidIdentifierNameLess2Length_whenCallNewImage_thenShouldReturnDomainException() {
        final String expectedIdentifierName = "2";
        final var expectedListImage = List.of("teste.img", "test2.img");
        final var expectErrorMessage = "'name' should be between 2 and 255 characters";
        final var expectErrorCount = 1;

        final var actualImage =
                Image.newImage(expectedIdentifierName, expectedListImage);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualImage.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidNullImageList_whenCallNewImage_thenShouldReturnDomainException() {
        final List<String> expectedListImage = new ArrayList<>();
        expectedListImage.add(null);
        final String expectedIdentifierName = "123456";
        final var expectErrorMessage = "'imageList' should not contain null";
        final var expectErrorCount = 1;

        final var actualImage =
                Image.newImage(expectedIdentifierName, expectedListImage);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualImage.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidEmptyItemToImageList_whenCallNewImage_thenShouldReturnDomainException() {
        final String expectedIdentifierName = "123456";
        final var expectedListImage = List.of("teste.img", "");
        final var expectErrorMessage = "'imageList' should not contain empty item";
        final var expectErrorCount = 1;

        final var actualImage =
                Image.newImage(expectedIdentifierName, expectedListImage);

        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualImage.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectErrorMessage, actualException.getErrors().get(0).message());
    }


}
