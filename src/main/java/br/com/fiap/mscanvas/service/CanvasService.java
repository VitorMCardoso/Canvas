package br.com.fiap.mscanvas.service;

import br.com.fiap.mscanvas.model.Canvas;
import br.com.fiap.mscanvas.repository.CanvasRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

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

    public Iterable<Canvas> list(){
        return canvasRepository.findAll();
    }
}
