
package org.neogroup.sparks.views.freemarker;

import freemarker.template.Template;
import org.neogroup.sparks.views.View;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Freemarker view for sparks framework
 */
public class FreeMarkerView extends View {

    private static String TEMPLATE_PROCESSING_ERROR = "Error processing freemarker template !!";

    private final Template template;
    private final Map<String,Object> parameters;

    /**
     * Constructor for the view
     * @param template freemarker template
     */
    public FreeMarkerView(Template template) {
        this.template = template;
        this.parameters = new HashMap<>();
    }

    /**
     * Set a parameter for the view
     * @param name name of parameter
     * @param value value of parameter
     */
    @Override
    public void setParameter(String name, Object value) {
        parameters.put(name, value);
    }

    /**
     * Get a parameter value of the view
     * @param name name of parameter
     * @return value of parameter
     */
    @Override
    public Object getParameter(String name) {
        return parameters.get(name);
    }

    /**
     * Renders to string the view
     * @return string
     */
    @Override
    public String render() {
        String response = null;
        try (StringWriter writer = new StringWriter()) {
            template.process(parameters, writer);
            response = writer.toString();
        }
        catch (Throwable throwable) {
            throw new RuntimeException(TEMPLATE_PROCESSING_ERROR, throwable);
        }
        return response;
    }
}
