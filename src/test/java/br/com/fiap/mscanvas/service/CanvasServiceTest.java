package br.com.fiap.mscanvas.service;

import br.com.fiap.mscanvas.model.Canvas;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

@Profile(value = "sandbox")
@DataMongoTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CanvasServiceTest {

    @Autowired
    private CanvasService canvasService;

    @Test
    public void testAdd() {
        Canvas canvas = Canvas.builder()
                .keyPartners("Academy")
                .keyActivities("IT")
                .keyResources("Intellectual")
                .costStructure("Fixed Costs")
                .valuePropositions("Risk Reduction")
                .customerRelationships("Chatbot")
                .customerSegments("StartUp")
                .channels("")
                .revenueStreams("Feature")
                .build();
        Iterable<Canvas> canvasArrayList = Collections.singletonList(canvas);
        canvasService.save(canvasArrayList);
    }
}
