
package org.neogroup.sparks.views.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.neogroup.sparks.views.ViewException;
import org.neogroup.sparks.views.ViewFactory;
import org.neogroup.sparks.views.ViewNotFoundException;

import java.io.File;

/**
 * Freemarker view factory
 */
public class FreeMarkerViewFactory extends ViewFactory<FreeMarkerView> {

    public static final String TEMPLATE_NAMESPACE_SEPARATOR = ".";

    private final Configuration configuration;

    /**
     * Constructor for the view factory
     * @param configuration freemarker configuration
     */
    public FreeMarkerViewFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * Consctructor for the view factory
     */
    public FreeMarkerViewFactory() {
        configuration = new Configuration(Configuration.VERSION_2_3_25);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setClassForTemplateLoading(this.getClass(), "/");
        configuration.setLogTemplateExceptions(false);
    }

    /**
     * Get the freemarker configuration
     * @return freemarker configuration
     */
    public Configuration getConfiguration() {
        return configuration;
    }

    /**
     * Creates a view
     * @param viewName name of the view
     * @return freemarker view
     * @throws ViewException
     */
    @Override
    public FreeMarkerView createView(String viewName) throws ViewException {
        try {
            String templateFilename = viewName.replace(TEMPLATE_NAMESPACE_SEPARATOR, File.separator) + ".ft";
            Template template = configuration.getTemplate(templateFilename);
            return new FreeMarkerView(template);
        }
        catch (freemarker.template.TemplateNotFoundException ex) {
            throw new ViewNotFoundException(ex);
        }
        catch (Exception ex) {
            throw new ViewException(ex);
        }
    }
}
