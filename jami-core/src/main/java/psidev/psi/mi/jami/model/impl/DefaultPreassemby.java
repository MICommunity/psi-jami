package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Preassembly;

/**
 * Default implementation for Preassembly
 *
 * Notes: The equals and hashcode methods have NOT been overridden because the Preassembly object is a complex object.
 * To compare Preassembly objects, you can use some comparators provided by default:
 * - DefaultCooperativeEffectBaseComparator
 * - UnambiguousCooperativeEffectBaseComparator
 * - DefaultExactCooperativeEffectBaseComparator
 * - UnambiguousExactCooperativeEffectBaseComparator
 * - CooperativeEffectBaseComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>23/05/13</pre>
 */
public class DefaultPreassemby extends DefaultCooperativeEffect implements Preassembly{

    /**
     * <p>Constructor for DefaultPreassemby.</p>
     *
     * @param outcome a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultPreassemby(CvTerm outcome) {
        super(outcome);
    }

    /**
     * <p>Constructor for DefaultPreassemby.</p>
     *
     * @param outcome a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param response a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultPreassemby(CvTerm outcome, CvTerm response) {
        super(outcome, response);
    }
}
