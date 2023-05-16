import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.impl.ResourceImpl;
import org.apache.jena.vocabulary.RDF;

public class Paper extends ResourceImpl {
    public Model model;
    public String title;
    public Resource resource;
    public Paper(Model model, String title, String uri, DatatypeProperty titleProperty, Resource paperClass) {
        super(uri);
        this.model = model;
        this.title = title;
        this.resource = model.createResource(uri);
        this.resource.addProperty(RDF.type, paperClass);
        this.resource.addProperty(titleProperty, title);
    }
}
