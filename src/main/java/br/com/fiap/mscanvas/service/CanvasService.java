package br.com.fiap.mscanvas.service;

import br.com.fiap.mscanvas.model.Canvas;
import br.com.fiap.mscanvas.repository.CanvasRepository;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@EnableMongoRepositories(basePackageClasses = CanvasRepository.class)
public class CanvasService {

    private final CanvasRepository canvasRepository;

    public CanvasService(CanvasRepository canvasRepository) {
        this.canvasRepository = canvasRepository;
    }

    public void save(Iterable<Canvas> canvas) {
        canvasRepository.saveAll(canvas);
    }

    public Iterable<Canvas> list() {
        return canvasRepository.findAll();
    }

    public void delete(String id) {
        canvasRepository.deleteById(id);
    }

    public Iterable<Canvas> findById(Iterable<String> ids) {
        return canvasRepository.findAllById(ids);
    }

    public Iterable<Canvas> update(Iterable<Canvas> canvas) {
        Optional<Canvas> canvasOptional = canvasRepository.findById(canvas.iterator().next().getId());
        if ((canvasOptional.isPresent())) {
            delete(canvasOptional.get().getId());
            //canvasUpdate = canvasOptional.map(Collections::singleton).orElseGet(Collections::emptySet);
            save(canvas);
        }
        return canvas;
    }
}
