package psidev.psi.mi.jami.bridges.mapper;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.model.Protein;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since  07/06/13

 */
public interface ProteinMapper {

    /**
     * <p>setListener.</p>
     *
     * @param listener a {@link psidev.psi.mi.jami.bridges.mapper.ProteinMapperListener} object.
     */
    public void setListener(ProteinMapperListener listener);
    /**
     * <p>getListener.</p>
     *
     * @return a {@link psidev.psi.mi.jami.bridges.mapper.ProteinMapperListener} object.
     */
    public ProteinMapperListener getListener();

    /**
     * Will remap the protein using the settings provided.
     * A remapReport will be fired to all RemapListeners at the end.
     *
     * @param p a {@link psidev.psi.mi.jami.model.Protein} object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public void map(Protein p) throws BridgeFailedException;
}
