package br.com.fiap.mscanvas;

import br.com.fiap.mscanvas.model.Canvas;
import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@Profile(value = "sandbox")
@DataMongoTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CanvasEmbededTest {

    private MongodExecutable mongodExecutable;
    private MongoTemplate mongoTemplate;

    @AfterEach
    void clean() {
        mongodExecutable.stop();
    }

    @BeforeEach
    void setup() throws Exception {
        String ip = "localhost";
        int port = 27017;

        IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
                .net(new Net(ip, port, Network.localhostIsIPv6()))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
        mongoTemplate = new MongoTemplate(new MongoClient(ip, port), "test");
    }

    @DisplayName("given object to save"
            + " when save object using MongoDB template"
            + " then object is saved")
    @Test
    void test() {

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
        // when
        mongoTemplate.save(canvasArrayList.iterator().next(), "collection");
        Canvas objectRetrieved = mongoTemplate.findAll(Canvas.class, "collection").get(0);
        // then
        assertThat(objectRetrieved).extracting("keyPartners").as("Academy");
    }
}
