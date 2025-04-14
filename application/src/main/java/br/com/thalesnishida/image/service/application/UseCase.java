package br.com.thalesnishida.image.service.application;

import br.com.thalesnishida.image.service.domain.image.Image;

import java.util.List;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN anIn);
}