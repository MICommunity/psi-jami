package psidev.psi.mi.jami.utils.comparator;

import java.util.*;

/**
 * A comparator for collections.
 *
 * Two collections are equals if they have the same content and the same size.
 * The smallest collection will come before the longest collection.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/12/12</pre>
 */
public class CollectionComparator<T> implements Comparator<Collection<? extends T>> {

    private final Comparator<T> objectComparator;

    /**
     * Creates a new CollectionComparator. It requires a Comparator for the obejcts in the Collection
     *
     * @param objectComparator a {@link java.util.Comparator} object.
     */
    public CollectionComparator(Comparator<T> objectComparator){
        if (objectComparator == null){
            throw new IllegalArgumentException("The Object comparator is required for comparing Collections of objects and it cannot be null");
        }
        this.objectComparator = objectComparator;
    }

    /**
     * <p>Getter for the field <code>objectComparator</code>.</p>
     *
     * @return a {@link java.util.Comparator} object.
     */
    public Comparator<T> getObjectComparator() {
        return objectComparator;
    }

    /**
     * Two collections are equals if they have the same content and the same size.
     * The smallest collection will come before the longest collection.
     *
     * @param ts1 a {@link java.util.Collection} object.
     * @param ts2 a {@link java.util.Collection} object.
     * @return a int.
     */
    public int compare(Collection<? extends T> ts1, Collection<? extends T> ts2) {

        int EQUAL = 0;
        int BEFORE = -1;
        int AFTER = 1;

        if (ts1 == ts2){
            return EQUAL;
        }
        else if (ts1 == null){
            return AFTER;
        }
        else if (ts2 == null){
            return BEFORE;
        }
        else {

            // compare collection size
            if (ts1.size() < ts2.size()){
                return BEFORE;
            }
            else if (ts1.size() > ts2.size()){
                return AFTER;
            }

            List<T> ts1ElementsNotFound = new ArrayList<>();
            List<T> ts2ElementsToCompare = new ArrayList<>(ts2);
            for (T object : ts1) {
                int indexOfObjectInTs2 = getIndexOfObjectInList(object, ts2ElementsToCompare);
                if (indexOfObjectInTs2 != -1) {
                    ts2ElementsToCompare.remove(indexOfObjectInTs2);
                } else {
                    ts1ElementsNotFound.add(object);
                }
            }
            if (ts1ElementsNotFound.isEmpty() && ts2ElementsToCompare.isEmpty()) {
                return EQUAL;
            } else {
                Iterator<T> iterator1 = ts1ElementsNotFound.iterator();
                Iterator<T> iterator2 = ts2ElementsToCompare.iterator();
                int comp2 = 0;
                while (comp2 == 0 && iterator1.hasNext() && iterator2.hasNext()){
                    comp2 = objectComparator.compare(iterator1.next(), iterator2.next());
                }

                if (comp2 != 0){
                    return comp2;
                }
                else if (iterator1.hasNext()){
                    return AFTER;
                }
                else if (iterator2.hasNext()){
                    return BEFORE;
                }
                else {
                    return comp2;
                }
            }
        }
    }

    private int getIndexOfObjectInList(T object, List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if (objectComparator.compare(object, list.get(i)) == 0) {
                return i;
            }
        }
        return -1;
    }
}
