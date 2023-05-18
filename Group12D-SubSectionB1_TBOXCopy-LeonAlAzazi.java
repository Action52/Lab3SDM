import org.apache.jena.datatypes.RDFDatatype;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.*;
import org.apache.jena.ontology.*;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class TBox {

    public static void main(String[] args) {

        // create a new Jena model
//        Model publication_model = ModelFactory.createDefaultModel();
//
//        // read the Turtle file into the model
//        publication_model.read("file:/home/zyad/Desktop/Lab3SDM/Lab3NewVersion.turtle.ttl", "TURTLE");

        OntModel publication_ontmodel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

        String baseURI = "http://www.publicationprocess.com/ontology#";
        publication_ontmodel.setNsPrefix("gf", baseURI);

        // Create classes
        OntClass academic = publication_ontmodel.createClass(baseURI + "Academic");
        OntClass author = publication_ontmodel.createClass(baseURI + "Author");
        OntClass leader = publication_ontmodel.createClass(baseURI + "Leader");
        OntClass chairman = publication_ontmodel.createClass(baseURI + "Chairman");
        OntClass editor = publication_ontmodel.createClass(baseURI + "Editor");
        OntClass reviewer = publication_ontmodel.createClass(baseURI + "Reviewer");

        OntClass paper = publication_ontmodel.createClass(baseURI + "Paper");
        OntClass fullpaper = publication_ontmodel.createClass(baseURI + "FullPaper");
        OntClass shortpaper = publication_ontmodel.createClass(baseURI + "ShortPaper");
        OntClass demopaper = publication_ontmodel.createClass(baseURI + "DemoPaper");
        OntClass poster = publication_ontmodel.createClass(baseURI + "Poster");

        OntClass venue = publication_ontmodel.createClass(baseURI + "Venue");
        OntClass conference = publication_ontmodel.createClass(baseURI + "Conference");
        OntClass journal = publication_ontmodel.createClass(baseURI + "Journal");
        OntClass regularconference = publication_ontmodel.createClass(baseURI + "RegularConference");
        OntClass symposium = publication_ontmodel.createClass(baseURI + "Symposium");
        OntClass workshop = publication_ontmodel.createClass(baseURI + "Workshop");
        OntClass expertgroup = publication_ontmodel.createClass(baseURI + "ExpertGroup");

        OntClass publicationlist = publication_ontmodel.createClass(baseURI + "PublicationList");
        OntClass journalvolume = publication_ontmodel.createClass(baseURI + "JournalVolume");
        OntClass conferenceproceeding = publication_ontmodel.createClass(baseURI + "ConferenceProceeding");

        OntClass area = publication_ontmodel.createClass(baseURI + "Area");
        OntClass review = publication_ontmodel.createClass(baseURI + "Review");
        OntClass publication = publication_ontmodel.createClass(baseURI + "Publication");

        // Set the superclasses for the appropriate classes
        author.addSuperClass(academic);
        leader.addSuperClass(academic);
        reviewer.addSuperClass(academic);
        chairman.addSuperClass(leader);
        editor.addSuperClass(leader);

        fullpaper.addSuperClass(paper);
        shortpaper.addSuperClass(paper);
        demopaper.addSuperClass(paper);
        poster.addSuperClass(paper);

        conference.addSuperClass(venue);
        journal.addSuperClass(venue);
        regularconference.addSuperClass(conference);
        symposium.addSuperClass(conference);
        workshop.addSuperClass(conference);
        expertgroup.addSuperClass(conference);

        journalvolume.addSuperClass(publicationlist);
        conferenceproceeding.addSuperClass(publicationlist);


        // Create a data property
        DatatypeProperty abstractProperty = publication_ontmodel.createDatatypeProperty(baseURI + "abstract");
        abstractProperty.addDomain(paper);
        abstractProperty.addRange(XSD.xstring);

        DatatypeProperty affiliationProperty = publication_ontmodel.createDatatypeProperty(baseURI + "affiliation");
        affiliationProperty.addDomain(academic);
        affiliationProperty.addRange(XSD.xstring);

        DatatypeProperty keywordProperty = publication_ontmodel.createDatatypeProperty(baseURI + "keyword");
        keywordProperty.addDomain(area);
        keywordProperty.addRange(XSD.xstring);

        DatatypeProperty decisionProperty = publication_ontmodel.createDatatypeProperty(baseURI + "decision");
        decisionProperty.addDomain(review);
        decisionProperty.addRange(XSD.xstring);

        DatatypeProperty editionProperty = publication_ontmodel.createDatatypeProperty(baseURI + "edition");
        editionProperty.addDomain(conference);
        editionProperty.addRange(XSD.xint);

        DatatypeProperty hindexProperty = publication_ontmodel.createDatatypeProperty(baseURI + "h-index");
        hindexProperty.addDomain(academic);
        hindexProperty.addRange(XSD.xdouble);

        DatatypeProperty impfactorProperty = publication_ontmodel.createDatatypeProperty(baseURI + "impact-factor");
        impfactorProperty.addDomain(journal);
        impfactorProperty.addRange(XSD.xstring);

        DatatypeProperty justificationProperty = publication_ontmodel.createDatatypeProperty(baseURI + "justification");
        justificationProperty.addDomain(review);
        justificationProperty.addRange(XSD.xstring);

        DatatypeProperty nameProperty = publication_ontmodel.createDatatypeProperty(baseURI + "person-name");
        nameProperty.addDomain(academic);
        nameProperty.addRange(XSD.xstring);

        DatatypeProperty posterpubyearProperty = publication_ontmodel.createDatatypeProperty(baseURI + "poster-publication-year");
        posterpubyearProperty.addDomain(poster);
        posterpubyearProperty.addRange(XSD.xint);

        DatatypeProperty pubyearProperty = publication_ontmodel.createDatatypeProperty(baseURI + "publication-year");
        pubyearProperty.addDomain(publication);
        pubyearProperty.addRange(XSD.xint);

        DatatypeProperty titleProperty = publication_ontmodel.createDatatypeProperty(baseURI + "title");
        titleProperty.addDomain(paper);
        titleProperty.addRange(XSD.xstring);

        DatatypeProperty venuedateProperty = publication_ontmodel.createDatatypeProperty(baseURI + "venue-date");
        venuedateProperty.addDomain(venue);
        venuedateProperty.addRange(XSD.date);

        DatatypeProperty venuenameProperty = publication_ontmodel.createDatatypeProperty(baseURI + "venue-name");
        venuenameProperty.addDomain(review);
        venuenameProperty.addRange(XSD.xstring);


        // Create an object property
        ObjectProperty assignedBy = publication_ontmodel.createObjectProperty(baseURI + "assigned-by");
        assignedBy.addDomain(reviewer);
        assignedBy.addRange(leader);

        ObjectProperty presents = publication_ontmodel.createObjectProperty(baseURI + "presents");
        presents.addDomain(venue);
        presents.addRange(area);

        ObjectProperty approves = publication_ontmodel.createObjectProperty(baseURI + "approves");
        approves.addDomain(review);
        approves.addRange(publication);

        ObjectProperty compiledFor = publication_ontmodel.createObjectProperty(baseURI + "compiled-for");
        compiledFor.addDomain(journalvolume);
        compiledFor.addRange(journal);

//        ObjectProperty demoedAt = publication_ontmodel.createObjectProperty(baseURI + "demoed-at");
//        demoedAt.addDomain(poster);
//        demoedAt.addRange(conference);

//        ObjectProperty describes = publication_ontmodel.createObjectProperty(baseURI + "describes");
//        describes.addDomain(poster);
//        describes.addRange(publication);

//        ObjectProperty designs = publication_ontmodel.createObjectProperty(baseURI + "designs");
//        designs.addDomain(author);
//        designs.addRange(poster);

        ObjectProperty editedBy = publication_ontmodel.createObjectProperty(baseURI + "edited-by");
        editedBy.addDomain(journal);
        editedBy.addRange(editor);

        ObjectProperty embodies = publication_ontmodel.createObjectProperty(baseURI + "embodies");
        embodies.addDomain(paper);
        embodies.addRange(area);

        ObjectProperty evaluates = publication_ontmodel.createObjectProperty(baseURI + "evaluates");
        evaluates.addDomain(review);
        evaluates.addRange(paper);

        ObjectProperty ledBy = publication_ontmodel.createObjectProperty(baseURI + "led-by");
        ledBy.addDomain(conference);
        ledBy.addRange(chairman);

        ObjectProperty performs = publication_ontmodel.createObjectProperty(baseURI + "performs");
        performs.addDomain(reviewer);
        performs.addRange(review);

        ObjectProperty publishedAt = publication_ontmodel.createObjectProperty(baseURI + "published-at");
        publishedAt.addDomain(publication);
        publishedAt.addRange(publicationlist);

        ObjectProperty resumes = publication_ontmodel.createObjectProperty(baseURI + "resumes");
        resumes.addDomain(conferenceproceeding);
        resumes.addRange(conference);

        ObjectProperty submittedTo = publication_ontmodel.createObjectProperty(baseURI + "submitted-to");
        submittedTo.addDomain(paper);
        submittedTo.addRange(venue);

        ObjectProperty writes = publication_ontmodel.createObjectProperty(baseURI + "writes");
        writes.addDomain(author);
        writes.addRange(paper);


        try {
            FileOutputStream out = new FileOutputStream("final-ontology.owl");
            publication_ontmodel.write(out, "RDF/XML");
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}