import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.impl.ResourceImpl;
import org.apache.jena.vocabulary.RDF;

public class Author extends ResourceImpl {
    public Model model;
    public String name;
    public Resource resource;
    public Author(Model model, String name, String uri, DatatypeProperty nameProperty, Resource authorClass) {
        super(uri);
        this.model = model;
        this.name = name;
        this.resource = model.createResource(uri);
        this.resource.addProperty(RDF.type, authorClass);
        this.resource.addProperty(nameProperty, name);
    }
}
