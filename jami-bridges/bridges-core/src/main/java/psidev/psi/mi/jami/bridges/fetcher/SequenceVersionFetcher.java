package psidev.psi.mi.jami.bridges.fetcher;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;

/**
 * A fetcher that fetches a sequence from a specific version and/or a version from a specific sequence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/09/13</pre>
 */
public interface SequenceVersionFetcher {

    /**
     * <p>fetchSequenceFromVersion.</p>
     *
     * @param id a {@link java.lang.String} object.
     * @param version a int.
     * @return a {@link java.lang.String} object.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public String fetchSequenceFromVersion(String id, int version) throws BridgeFailedException;

    /**
     * <p>fetchVersionFromSequence.</p>
     *
     * @param id a {@link java.lang.String} object.
     * @param sequence a {@link java.lang.String} object.
     * @return a int.
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public int fetchVersionFromSequence(String id, String sequence) throws BridgeFailedException;
}
