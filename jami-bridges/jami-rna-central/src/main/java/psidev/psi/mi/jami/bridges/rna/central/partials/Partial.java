package psidev.psi.mi.jami.bridges.rna.central.partials;

import java.util.Arrays;

public interface Partial<T>  {
    T complete();
    default Partial<T> combine(Partial<T> other) {
        Arrays.stream(this.getClass().getDeclaredFields()).forEach(field -> {
            try {
                field.setAccessible(true);
                field.set(this, field.get(other) != null ? field.get(other) : field.get(this));
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        return this;
    }

    default T complete(Partial<T> other) {
        return this.combine(other).complete();
    }
}

