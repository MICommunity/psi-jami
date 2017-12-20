package psidev.psi.mi.jami.bridges.mapper.mock;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.mapper.ProteinMapper;
import psidev.psi.mi.jami.bridges.mapper.ProteinMapperListener;
import psidev.psi.mi.jami.model.Protein;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 23/07/13

 */
public class MockProteinMapper implements ProteinMapper {

    private ProteinMapperListener listener = null;
    private boolean checking;
    private boolean priorityIdentifiers;
    private boolean prioritySequence;

    private Map<String,String> localRemap;

    /**
     * <p>Constructor for MockProteinMapper.</p>
     */
    public MockProteinMapper(){
        localRemap = new HashMap<String, String>();
    }

    /**
     * <p>addMappingResult.</p>
     *
     * @param oldKey a {@link java.lang.String} object.
     * @param newIdentifier a {@link java.lang.String} object.
     */
    public void addMappingResult(String oldKey, String newIdentifier){
        if(oldKey == null || newIdentifier == null) return;
        this.localRemap.put(oldKey , newIdentifier);
    }

    /** {@inheritDoc} */
    public void setListener(ProteinMapperListener listener) {
        this.listener = listener;
    }

    /**
     * <p>Getter for the field <code>listener</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.bridges.mapper.ProteinMapperListener} object.
     */
    public ProteinMapperListener getListener() {
        return listener;
    }

    /** {@inheritDoc} */
    public void map(Protein p) throws BridgeFailedException {
        String newID = null;

        if(p.getSequence() != null) {
            newID = localRemap.get(p.getSequence());
            if(newID != null){
                p.setUniprotkb(newID);
                if( listener != null )
                    listener.onSuccessfulMapping(p, Collections.<String>emptyList());
            }
            return;
        }
        if(! p.getXrefs().isEmpty()) {
            newID = localRemap.get(p.getXrefs().iterator().next().getId());
            if(newID != null){
                p.setUniprotkb(newID);
                if( listener != null )
                    listener.onSuccessfulMapping(p, Collections.<String>emptyList());
            }
            return;
        }

        if( listener != null )
            listener.onFailedMapping(p, Collections.<String>emptyList());

    }

    /**
     * <p>isCheckingEnabled.</p>
     *
     * @return a boolean.
     */
    public boolean isCheckingEnabled() {
        return checking;
    }

    /**
     * <p>setCheckingEnabled.</p>
     *
     * @param checkingEnabled a boolean.
     */
    public void setCheckingEnabled(boolean checkingEnabled) {
        this.checking = checkingEnabled;
    }

    /**
     * <p>isPriorityIdentifiers.</p>
     *
     * @return a boolean.
     */
    public boolean isPriorityIdentifiers() {
        return priorityIdentifiers;
    }

    /**
     * <p>Setter for the field <code>priorityIdentifiers</code>.</p>
     *
     * @param priorityIdentifiers a boolean.
     */
    public void setPriorityIdentifiers(boolean priorityIdentifiers) {
        this.priorityIdentifiers = priorityIdentifiers;
    }

    /**
     * <p>isPrioritySequence.</p>
     *
     * @return a boolean.
     */
    public boolean isPrioritySequence() {
        return prioritySequence;
    }

    /**
     * <p>Setter for the field <code>prioritySequence</code>.</p>
     *
     * @param prioritySequence a boolean.
     */
    public void setPrioritySequence(boolean prioritySequence) {
        this.prioritySequence = prioritySequence;
    }
}
