package br.com.thalesnishida.image.service.application;

import br.com.thalesnishida.image.service.domain.image.Image;

import java.util.List;

public class UseCase {
    public Image execute() {
        return Image.newImage("dsadasd", List.of("sdsad", "asdsad"));
    }
}