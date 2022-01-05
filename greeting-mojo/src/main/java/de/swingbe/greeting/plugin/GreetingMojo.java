package de.swingbe.greeting.plugin;

//provide infrastructure required to implement a MOJO except for the execute method
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * Says "Hi" to the user.
 *
 */
//required annotation that controls how and when the MOJO is executed
@Mojo( name = "sayhi")
public class GreetingMojo extends AbstractMojo
{
    public void execute() throws MojoExecutionException
    {
	//defined in AbstractMojo
	//returns logger objects that allows plugins to display messages
        getLog().info( "Hello, world." );
    }
}
