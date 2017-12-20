package psidev.psi.mi.jami.xml.cache;

/**
 * The location of an object with id in an entry
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/06/14</pre>
 */
public class EntryLocation {

    private int id;
    private int entryId;

    /**
     * <p>Constructor for EntryLocation.</p>
     *
     * @param entryId a int.
     * @param id a int.
     */
    public EntryLocation(int entryId, int id){
         this.id = id;
        this.entryId = entryId;
    }

    /**
     * <p>Getter for the field <code>id</code>.</p>
     *
     * @return a int.
     */
    public int getId() {
        return id;
    }

    /**
     * <p>Getter for the field <code>entryId</code>.</p>
     *
     * @return a int.
     */
    public int getEntryId() {
        return entryId;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( !( o instanceof EntryLocation ) ) {
            return false;
        }

        final EntryLocation location = ( EntryLocation ) o;

        if ( location.getEntryId() != getEntryId() ) {
            return false;
        }

        return location.getId() == getId();
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        int code = 37;

        code = 37 * code + getEntryId();
        code = 37 * code + getId();

        return code;
    }
}
