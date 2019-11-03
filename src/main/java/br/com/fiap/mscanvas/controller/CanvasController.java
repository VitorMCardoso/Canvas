package br.com.fiap.mscanvas.controller;

import br.com.fiap.mscanvas.model.Canvas;
import br.com.fiap.mscanvas.service.CanvasService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/add")
    public void add(@Valid @RequestBody Iterable<Canvas> canvas) {
        canvasService.save(canvas);
    }
}
