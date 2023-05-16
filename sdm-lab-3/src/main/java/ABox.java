import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.ModelFactory;

import java.io.IOException;


public class ABox {

    public static void main(String[] args){

        // Create an empty OntModel
        OntModel publication_ontmodel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        String ontologyFilePath = "final-ontology.owl";

        // Read the TBox (ontology) from the OWL file
        publication_ontmodel.read(ontologyFilePath, "OWL");

        String baseURI = "http://www.publicationprocess.com/ontology#";

        // Create classes
        OntClass academicClass = publication_ontmodel.getOntClass(baseURI + "Academic");
        OntClass authorClass = publication_ontmodel.getOntClass(baseURI + "Author");
        OntClass leaderClass = publication_ontmodel.getOntClass(baseURI + "Leader");
        OntClass chairmanClass = publication_ontmodel.getOntClass(baseURI + "Chairman");
        OntClass editorClass = publication_ontmodel.getOntClass(baseURI + "Editor");
        OntClass reviewerClass = publication_ontmodel.getOntClass(baseURI + "Reviewer");

        OntClass paperClass = publication_ontmodel.getOntClass(baseURI + "Paper");
        OntClass fullpaperClass = publication_ontmodel.getOntClass(baseURI + "FullPaper");
        OntClass shortpaperClass = publication_ontmodel.getOntClass(baseURI + "ShortPaper");
        OntClass demopaperClass = publication_ontmodel.getOntClass(baseURI + "DemoPaper");
        OntClass posterClass = publication_ontmodel.getOntClass(baseURI + "Poster");

        OntClass venueClass = publication_ontmodel.getOntClass(baseURI + "Venue");
        OntClass conferenceClass = publication_ontmodel.getOntClass(baseURI + "Conference");
        OntClass journalClass = publication_ontmodel.getOntClass(baseURI + "Journal");
        OntClass regularconfClass = publication_ontmodel.getOntClass(baseURI + "RegularConference");
        OntClass symposiumClass = publication_ontmodel.getOntClass(baseURI + "Symposium");
        OntClass workshopClass = publication_ontmodel.getOntClass(baseURI + "Workshop");
        OntClass expertgroupClass = publication_ontmodel.getOntClass(baseURI + "ExpertGroup");

        OntClass publistClass = publication_ontmodel.getOntClass(baseURI + "PublicationList");
        OntClass journalvolClass = publication_ontmodel.getOntClass(baseURI + "JournalVolume");
        OntClass confproceedingClass = publication_ontmodel.getOntClass(baseURI + "ConferenceProceeding");

        OntClass areaClass = publication_ontmodel.getOntClass(baseURI + "Area");
        OntClass reviewClass = publication_ontmodel.getOntClass(baseURI + "Review");
        OntClass publicationClass = publication_ontmodel.getOntClass(baseURI + "Publication");

        // Object Properties
        ObjectProperty assignedBy = publication_ontmodel.getObjectProperty(baseURI + "assigned-by");
        ObjectProperty presents = publication_ontmodel.getObjectProperty(baseURI + "presents");
        ObjectProperty approves = publication_ontmodel.getObjectProperty(baseURI + "approves");
        ObjectProperty compiledFor = publication_ontmodel.getObjectProperty(baseURI + "compiled-for");
        ObjectProperty demoedAt = publication_ontmodel.getObjectProperty(baseURI + "demoed-at");
        ObjectProperty describes = publication_ontmodel.getObjectProperty(baseURI + "describes");
        ObjectProperty designs = publication_ontmodel.getObjectProperty(baseURI + "designs");
        ObjectProperty editedBy = publication_ontmodel.getObjectProperty(baseURI + "edited-by");
        ObjectProperty embodies = publication_ontmodel.getObjectProperty(baseURI + "embodies");
        ObjectProperty evaluates = publication_ontmodel.getObjectProperty(baseURI + "evaluates");
        ObjectProperty ledBy = publication_ontmodel.getObjectProperty(baseURI + "led-by");
        ObjectProperty performs = publication_ontmodel.getObjectProperty(baseURI + "performs");
        ObjectProperty publishedAt = publication_ontmodel.getObjectProperty(baseURI + "published-at");
        ObjectProperty resumes = publication_ontmodel.getObjectProperty(baseURI + "resumes");
        ObjectProperty submittedTo = publication_ontmodel.getObjectProperty(baseURI + "submitted-to");
        ObjectProperty writes = publication_ontmodel.getObjectProperty(baseURI + "writes");

        // Data Properties
        DatatypeProperty abstractProperty = publication_ontmodel.getDatatypeProperty(baseURI + "abstract");
        DatatypeProperty affiliationProperty = publication_ontmodel.getDatatypeProperty(baseURI + "affiliation");
        DatatypeProperty keywordProperty = publication_ontmodel.getDatatypeProperty(baseURI + "keyword");
        DatatypeProperty decisionProperty = publication_ontmodel.getDatatypeProperty(baseURI + "decision");
        DatatypeProperty editionProperty = publication_ontmodel.getDatatypeProperty(baseURI + "edition");
        DatatypeProperty hindexProperty = publication_ontmodel.getDatatypeProperty(baseURI + "h-index");
        DatatypeProperty impfactorProperty = publication_ontmodel.getDatatypeProperty(baseURI + "impact-factor");
        DatatypeProperty justificationProperty = publication_ontmodel.getDatatypeProperty(baseURI + "justification");
        DatatypeProperty nameProperty = publication_ontmodel.getDatatypeProperty(baseURI + "person-name");
        DatatypeProperty posterpubyearProperty = publication_ontmodel.getDatatypeProperty(baseURI + "poster-publication-year");
        DatatypeProperty pubyearProperty = publication_ontmodel.getDatatypeProperty(baseURI + "publication-year");
        DatatypeProperty titleProperty = publication_ontmodel.getDatatypeProperty(baseURI + "title");
        DatatypeProperty venuedateProperty = publication_ontmodel.getDatatypeProperty(baseURI + "venue-date");
        DatatypeProperty venuenameProperty = publication_ontmodel.getDatatypeProperty(baseURI + "venue-name");
        
        try {
            String csvFilePath = "/Users/alfredo.leon/Google Drive/Mi unidad/BDMA/2nd Semester/Semantic Data Management/labs/lab3/Lab3SDM/triplets_csv_parsed.csv";
            PaperIterator paperIterator = new PaperIterator(csvFilePath);
            while (paperIterator.hasNext()) {
                PaperRow paper = paperIterator.next();
                System.out.println(paper);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
