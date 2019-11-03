package br.com.fiap.mscanvas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "canvas")
@Builder
public class Canvas {
    @MongoId
    private String id;
    private String keyPartners;
    private String keyActivities;
    private String keyResources;
    private String costStructure;
    private String valuePropositions;
    private String customerRelationships;
    private String customerSegments;
    private String channels;
    private String revenueStreams;

}
