package br.com.thalesnishida.image.service.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImageTest {

    @Test
    public void testNewImage() {
        Assertions.assertNotNull(new Image());
    }
}
