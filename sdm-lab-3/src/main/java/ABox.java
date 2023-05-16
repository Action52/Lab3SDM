import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.reasoner.ValidityReport;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.function.Consumer;


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
//        ObjectProperty demoedAt = publication_ontmodel.getObjectProperty(baseURI + "demoed-at");
        ObjectProperty describes = publication_ontmodel.getObjectProperty(baseURI + "describes");
//        ObjectProperty designs = publication_ontmodel.getObjectProperty(baseURI + "designs");
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
            String csvFilePath = "triplets_csv_parsed.csv";
            PaperIterator paperIterator = new PaperIterator(csvFilePath);
            while (paperIterator.hasNext()) {
                PaperRow paperInfo = paperIterator.next();

                Individual paper;

                if(paperInfo.paperType.equalsIgnoreCase("full paper")){
                    paper = publication_ontmodel.createIndividual(URLEncoder.encode(baseURI+paperInfo.title), fullpaperClass);
                } else if (paperInfo.paperType.equalsIgnoreCase("short paper")) {
                    paper = publication_ontmodel.createIndividual(URLEncoder.encode(baseURI+paperInfo.title), shortpaperClass);
                }else if (paperInfo.paperType.equalsIgnoreCase("demo paper")) {
                    paper = publication_ontmodel.createIndividual(URLEncoder.encode(baseURI+paperInfo.title), demopaperClass);
                }else{
                    paper = publication_ontmodel.createIndividual(URLEncoder.encode(baseURI+paperInfo.title), posterClass);
                }

                paper.addProperty(titleProperty, paperInfo.title);
                paper.addProperty(abstractProperty, paperInfo.abstractContent);

                int index = Math.min(Math.min(paperInfo.authors.length, paperInfo.indexKeywords.length), paperInfo.affiliations.length);

                Individual area = publication_ontmodel.createIndividual(URLEncoder.encode(baseURI+paperInfo.authorKeywords[0]), areaClass);

                for (int i = 0; i<index; i++){

                    Individual author = publication_ontmodel.createIndividual(URLEncoder.encode(baseURI+paperInfo.authorIds), authorClass);

                    author.addProperty(nameProperty, paperInfo.authors[i]);
                    author.addProperty(affiliationProperty, paperInfo.affiliations[i]);
                    author.addProperty(writes, paper);
                    area.addProperty(keywordProperty, paperInfo.indexKeywords[i]);

                }

                paper.addProperty(embodies, area);

                Individual first_reviewer = publication_ontmodel.createIndividual(URLEncoder.encode(baseURI+paperInfo.reviewersIds[0]), reviewerClass);
                Individual second_reviewer = publication_ontmodel.createIndividual(URLEncoder.encode(baseURI+paperInfo.reviewersIds[1]), reviewerClass);

                first_reviewer.addProperty(nameProperty, paperInfo.reviewers[0]);
                second_reviewer.addProperty(nameProperty, paperInfo.reviewers[1]);

                Individual first_review = publication_ontmodel.createIndividual(URLEncoder.encode(baseURI+paperInfo.title.concat("#review0")), reviewClass);
                Individual second_review = publication_ontmodel.createIndividual(URLEncoder.encode(baseURI+paperInfo.title.concat("#review1")), reviewClass);

                String first_decision = URLEncoder.encode(paperInfo.reviewDecisions[0].replace("[","").replace("'",""));
                String second_decision = URLEncoder.encode(paperInfo.reviewDecisions[0].replace("[","").replace("'",""));

                first_review.addProperty(decisionProperty, first_decision);
                first_review.addProperty(justificationProperty, paperInfo.reviewComments[0]);
                second_review.addProperty(decisionProperty, second_decision);
                second_review.addProperty(justificationProperty, paperInfo.reviewComments[1]);

                first_reviewer.addProperty(performs, first_review);
                second_reviewer.addProperty(performs, second_review);

                first_review.addProperty(evaluates, paper);
                second_review.addProperty(evaluates, paper);


                if (first_decision.equalsIgnoreCase("accepted") && second_decision.equalsIgnoreCase("accepted")){

                    Individual publication = publication_ontmodel.createIndividual(URLEncoder.encode(baseURI+paperInfo.title.concat("#pub")), publicationClass);

                    first_reviewer.addProperty(approves, publication);
                    second_review.addProperty(approves, publication);

//                    System.out.println(paperInfo.documentType);
                    if (paperInfo.documentType.equalsIgnoreCase("journal") && !paperInfo.paperType.equalsIgnoreCase("poster")){
                        Individual venue = publication_ontmodel.createIndividual(URLEncoder.encode(baseURI+paperInfo.generalConferenceName+paperInfo.year), journalClass);
                        Individual publicationList = publication_ontmodel.createIndividual(URLEncoder.encode(baseURI+paperInfo.generalConferenceName.concat("#volume#")+paperInfo.year), journalvolClass);
                        Individual leader = publication_ontmodel.createIndividual(URLEncoder.encode(baseURI+paperInfo.leader), editorClass);


//                        venue.addProperty(impfactorProperty, paperInfo.impfactor);
                        publicationList.addProperty(compiledFor, venue);
                        venue.addProperty(editedBy, leader);

                        paper.addProperty(submittedTo, venue);
                        publication.addProperty(publishedAt, publicationList);
                        paper.addProperty(submittedTo, venue);

                        first_reviewer.addProperty(assignedBy, leader);
                        second_reviewer.addProperty(assignedBy, leader);

                        venue.addProperty(venuenameProperty, paperInfo.generalConferenceName.toString());
                        venue.addProperty(presents, area);
//                        venue.addProperty(venuedateProperty, paperInfo.date);


                    }
                    else {
                        Individual publicationList = publication_ontmodel.createIndividual(URLEncoder.encode(baseURI+paperInfo.generalConferenceName.concat("#proceeding#")+paperInfo.year), confproceedingClass);
                        Individual leader = publication_ontmodel.createIndividual(URLEncoder.encode(baseURI+paperInfo.leader), chairmanClass);
                        if (paperInfo.conferenceType.equalsIgnoreCase("regular conference")){
                            Individual venue = publication_ontmodel.createIndividual(URLEncoder.encode(baseURI+paperInfo.generalConferenceName+paperInfo.year), regularconfClass);
//                            venue.addProperty(editionProperty, paperInfo.edition);
                            publicationList.addProperty(resumes, venue);
                            venue.addProperty(ledBy, chairmanClass);
                            paper.addProperty(submittedTo, venue);
                            paper.addProperty(submittedTo, venue);
                            venue.addProperty(venuenameProperty, paperInfo.generalConferenceName.toString());
                            venue.addProperty(presents, area);
//                        venue.addProperty(venuedateProperty, paperInfo.date);
                        } else if (paperInfo.conferenceType.equalsIgnoreCase("expert group")) {
                            Individual venue = publication_ontmodel.createIndividual(URLEncoder.encode(baseURI+paperInfo.generalConferenceName+paperInfo.year), expertgroupClass);
                            publicationList.addProperty(resumes, venue);
                            venue.addProperty(ledBy, chairmanClass);
                            paper.addProperty(submittedTo, venue);
                            paper.addProperty(submittedTo, venue);
                            venue.addProperty(venuenameProperty, paperInfo.generalConferenceName.toString());
                            venue.addProperty(presents, area);
//                        venue.addProperty(venuedateProperty, paperInfo.date);
                        } else if (paperInfo.conferenceType.equalsIgnoreCase("symposium")) {
                            Individual venue = publication_ontmodel.createIndividual(URLEncoder.encode(baseURI+paperInfo.generalConferenceName+paperInfo.year), symposiumClass);
                            publicationList.addProperty(resumes, venue);
                            venue.addProperty(ledBy, chairmanClass);
                            paper.addProperty(submittedTo, venue);
                            paper.addProperty(submittedTo, venue);
                            venue.addProperty(venuenameProperty, paperInfo.generalConferenceName.toString());
                            venue.addProperty(presents, area);
//                        venue.addProperty(venuedateProperty, paperInfo.date);
                        } else if (paperInfo.conferenceType.equalsIgnoreCase("workshop")) {
                            Individual venue = publication_ontmodel.createIndividual(URLEncoder.encode(baseURI+paperInfo.generalConferenceName+paperInfo.year), workshopClass);
                            publicationList.addProperty(resumes, venue);
                            venue.addProperty(ledBy, chairmanClass);
                            paper.addProperty(submittedTo, venue);
                            paper.addProperty(submittedTo, venue);
                            venue.addProperty(venuenameProperty, paperInfo.generalConferenceName.toString());
                            venue.addProperty(presents, area);
//                        venue.addProperty(venuedateProperty, paperInfo.date);
                        }

                        publication.addProperty(publishedAt, publicationList);
                        first_reviewer.addProperty(assignedBy, leader);
                        second_reviewer.addProperty(assignedBy, leader);

//                         venue.addProperty(editionProperty, paperInfo.edition);



                    }



                }

            }

            Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
            reasoner.bindSchema(publication_ontmodel);
            InfModel infModel = ModelFactory.createInfModel(reasoner, publication_ontmodel);

//            ValidityReport vr = infModel.validate();
//            vr.getReports().forEachRemaining(report -> {
//                System.out.println(report);
//            });
//            System.out.println(vr.toString());

            if(infModel.validate().isValid()){
                System.out.println("Perfect");
            }else{
                System.out.println("Nope!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
