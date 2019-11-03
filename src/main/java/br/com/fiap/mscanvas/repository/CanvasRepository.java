package br.com.fiap.mscanvas.repository;

import br.com.fiap.mscanvas.model.Canvas;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CanvasRepository extends MongoRepository<Canvas, String> {
}
