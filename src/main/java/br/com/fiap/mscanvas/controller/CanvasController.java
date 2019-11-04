package br.com.fiap.mscanvas.controller;

import br.com.fiap.mscanvas.model.Canvas;
import br.com.fiap.mscanvas.service.CanvasService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/canvas")
public class CanvasController {

    private final CanvasService canvasService;

    public CanvasController(CanvasService canvasService) {
        this.canvasService = canvasService;
    }

    @GetMapping
    public Iterable<Canvas> list() {
        return canvasService.list();
    }

    @GetMapping("/find")
    public Iterable<Canvas> find(@Valid @RequestBody Iterable<String> ids) {
        return canvasService.findById(ids);
    }

    @PostMapping
    public void add(@Valid @RequestBody Iterable<Canvas> canvas) {
        canvasService.save(canvas);
    }

    @DeleteMapping
    public void delete(@Valid @RequestParam String id) {
        canvasService.delete(id);
    }

    @PutMapping
    public Iterable<Canvas> update(@Valid @RequestBody Iterable<Canvas> canvas) {
        return canvasService.update(canvas);
    }
}
