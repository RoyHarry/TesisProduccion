/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
 
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
 
/**
 * Enables messages to be rendered on different pages from which they were set.
 *
 * After each phase where messages may be added, this moves the messages
 * from the page-scoped FacesContext to the session-scoped session map.
 *
 * Before messages are rendered, this moves the messages from the
 * session-scoped session map back to the page-scoped FacesContext.
 *
 * Only global messages, not associated with a particular component, are
 * moved. Component messages cannot be rendered on pages other than the one on
 * which they were added.
 *
 * To enable multi-page messages support, add a <code>lifecycle</code> block to your
 * faces-config.xml file. That block should contain a single
 * <code>phase-listener</code> block containing the fully-qualified classname
 * of this file.
 *
 * @author Jesse Wilson jesse[AT]odel.on.ca
 * @secondaryAuthor Lincoln Baxter III lincoln[AT]ocpsoft.com 
 */
public class MultiPageMessagesSupport implements PhaseListener
{
 
    
   private static final long serialVersionUID = 3328743500652081238L;
 
    /** a name to save messages in the session under */
    private static final String sessionToken = "MULTI_PAGE_MESSAGES_SUPPORT";
 
    /**
     * Return the identifier of the request processing phase during which this
     * listener is interested in processing PhaseEvent events.
     */
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
 
    /**
     * Handle a notification that the processing for a particular phase of the
     * request processing lifecycle is about to begin.
     */
    public void beforePhase(PhaseEvent event) {
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            FacesContext facesContext = event.getFacesContext();
            restoreMessages(facesContext);
        }
    }
 
    /**
     * Handle a notification that the processing for a particular phase has just
     * been completed.
     */
    public void afterPhase(PhaseEvent event) {
        if (event.getPhaseId() == PhaseId.APPLY_REQUEST_VALUES
                || event.getPhaseId() == PhaseId.PROCESS_VALIDATIONS
                || event.getPhaseId() == PhaseId.INVOKE_APPLICATION) {
            FacesContext facesContext = event.getFacesContext();
            saveMessages(facesContext);
        }
    }
 
    /**
     * Remove the messages that are not associated with any particular component
     * from the faces context and store them to the user's session.
     * 
     * @return the number of removed messages.
     */
    @SuppressWarnings("unchecked")
    private int saveMessages(FacesContext facesContext) {
        // remove messages from the context
        Set<FacesMessage> messages = new HashSet<FacesMessage>();
        for (Iterator i = facesContext.getMessages(null); i.hasNext();) {
            FacesMessage msg = (FacesMessage) i.next();
            messages.add(msg);
            i.remove();
        }
        // store them in the session
        if (messages.size() == 0)
            return 0;
        Map sessionMap = facesContext.getExternalContext().getSessionMap();
        // if there already are messages
        Set<FacesMessage> existingMessages = (Set<FacesMessage>) sessionMap
                .get(sessionToken);
        if (existingMessages != null) {
            existingMessages.addAll(messages);
            // if these are the first messages
        } else {
            sessionMap.put(sessionToken, messages);
        }
        return messages.size();
    }
 
    /**
     * Remove the messages that are not associated with any particular component
     * from the user's session and add them to the faces context.
     * 
     * @return the number of removed messages.
     */
    @SuppressWarnings("unchecked")
    private int restoreMessages(FacesContext facesContext) {
        // remove messages from the session
        Map sessionMap = facesContext.getExternalContext().getSessionMap();
        Set<FacesMessage> messages = (Set<FacesMessage>) sessionMap
                .remove(sessionToken);
        // store them in the context
        if (messages == null)
            return 0;
        int restoredCount = messages.size();
 
        // set that contains wich messages already exists in the FacesContext
        Set<FacesMessage> facesContextMessages = new HashSet<FacesMessage>();
        for (Iterator i = facesContext.getMessages(null); i.hasNext();) {
            FacesMessage msg = (FacesMessage) i.next();
            facesContextMessages.add(msg);
            i.remove();
        }
 
        // add the messages that aren't in the FacesContext 
        for (FacesMessage facesMessage : messages) {
            if (!facesContextMessages.contains(facesMessage))
                facesContext.addMessage(null, facesMessage);
        }
        return restoredCount;
    }
}