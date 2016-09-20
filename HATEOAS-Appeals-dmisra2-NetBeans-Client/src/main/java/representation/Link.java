package representation;

import java.net.URI;
import java.net.URISyntaxException;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = Representation.DAP_NAMESPACE)
public class Link
{
    
    @XmlAttribute(name = "rel")
    private String rel;
    @XmlAttribute(name = "uri")
    private String uri;

    @XmlAttribute(name = "mediaType")
    private String mediaType;

    public Link()
    {
        System.out.print("");
    }
    public Link(String name, GradebookUri uri, String mediaType) 
    {
        
        this.rel = name;
        this.uri = uri.getFullUri().toString();
        this.mediaType = mediaType;
    }

    public Link(String name, GradebookUri uri) 
    {
        this(name, uri, Representation.GRADEBOOK_MEDIA_TYPE);
    }

    public String getRelValue() 
    {
        return rel;
    }

    public URI getUri()
    {
        
        try
        {
            URI local_uri = new URI(uri);
            return local_uri;
        } 
        catch (URISyntaxException e)
        {
            throw new RuntimeException(e);
        }
    }

    public String getMediaType()
    {
        return mediaType;
    }
}
