package psidev.psi.mi.jami.utils.comparator.cooperativity;

import psidev.psi.mi.jami.model.CooperativeEffect;

/**
 * Unambiguous exact cooperative effect comparator
 *
 * Allostery effects will always come before basic cooperative effects (preassembly)
 *
 * - It will use UnambiguousExactAllosteryComparator to compare allostery
 * - It will use UnambiguousExactCooperativeEffectBaseComparator to compare basic cooperative effects
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>31/05/13</pre>
 */
public class UnambiguousExactCooperativeEffectComparator extends CooperativeEffectComparator {

    private static UnambiguousExactCooperativeEffectComparator unambiguousExactCooperativeEffectComparator;

    /**
     * <p>Constructor for UnambiguousExactCooperativeEffectComparator.</p>
     */
    public UnambiguousExactCooperativeEffectComparator() {
        super(new UnambiguousExactAllosteryComparator());
    }

    /** {@inheritDoc} */
    @Override
    public UnambiguousExactAllosteryComparator getAllosteryComparator() {
        return (UnambiguousExactAllosteryComparator) super.getAllosteryComparator();
    }

    /**
     * Allostery effects will always come before basic cooperative effects (preassembly)
     *
     * - It will use DefaultExactAllosteryComparator to compare allostery
     * - It will use DefaultExactCooperativeEffectBaseComparator to compare basic cooperative effects
     *
     * @param effect1 a {@link psidev.psi.mi.jami.model.CooperativeEffect} object.
     * @param effect2 a {@link psidev.psi.mi.jami.model.CooperativeEffect} object.
     * @return a int.
     */
    public int compare(CooperativeEffect effect1, CooperativeEffect effect2) {
        return super.compare(effect1, effect2);
    }

    /**
     * Use UnambiguousExactCooperativeEffectComparator to know if two CooperativeEffects are equals.
     *
     * @param effect1 a {@link psidev.psi.mi.jami.model.CooperativeEffect} object.
     * @param effect2 a {@link psidev.psi.mi.jami.model.CooperativeEffect} object.
     * @return true if the two CooperativeEffects are equal
     */
    public static boolean areEquals(CooperativeEffect effect1, CooperativeEffect effect2){
        if (unambiguousExactCooperativeEffectComparator == null){
            unambiguousExactCooperativeEffectComparator = new UnambiguousExactCooperativeEffectComparator();
        }

        return unambiguousExactCooperativeEffectComparator.compare(effect1, effect2) == 0;
    }
}
