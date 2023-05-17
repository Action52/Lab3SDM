import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.impl.ResourceImpl;
import org.apache.jena.vocabulary.RDF;

public class Conference extends ResourceImpl {
    public Model model;
    public String conferenceName;
    public Resource resource;
    public Conference(Model model, String conferenceName, String uri, DatatypeProperty conferenceNameProperty,
                      Resource conferenceClass) {
        super(uri);
        this.model = model;
        this.title = title;
        this.resource = model.createResource(uri);
        this.resource.addProperty(RDF.type, conferenceClass);
        this.resource.addProperty(titleProperty, title);
    }
}
