package representation;

import java.net.URI;
import java.net.URISyntaxException;

import model.Identifier;

public class GradebookUri
{
    private URI uri;
    
    public static int ordinalIndexOf(String str, char c, int n) 
    {
        int pos = str.indexOf(c, 0);
        while (n-- > 0 && pos != -1)
            pos = str.indexOf(c, pos+1);
        return pos;
    }
    
    public GradebookUri(String uri)
    {
        try 
        {
            this.uri = new URI(uri);
        }
        catch (URISyntaxException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    public GradebookUri(URI uri) 
    {
        this(uri.toString());
    }

    public GradebookUri(URI uri, Identifier identifier)
    {
        this(uri.toString() + "/" + identifier.toString());
    }

    public Identifier getId()
    {
        String path = uri.getPath();
        path = path.substring(0,path.length()-1);
        return new Identifier(path.substring(path.lastIndexOf("/") + 1, path.length()));
    }

    public URI getFullUri() 
    {
        return uri;
    }
    
    @Override
    public String toString()
    {
        return uri.toString();
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof GradebookUri) 
        {
            return ((GradebookUri)obj).uri.equals(uri);
        }
        return false;
    }

    public String getBaseUri() 
    {
        String uriString = uri.toString();
        int index = ordinalIndexOf(uriString, '/', 5);
        String newString = uriString.substring(0, index+1);
        //String baseURI   = uriString.substring(0, uriString.lastIndexOf("webresources/")+"webresources".length());
        
        return newString;
    }
}
