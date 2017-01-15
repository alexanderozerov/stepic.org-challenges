package stepic.generics;


class Pair<T, V> {
    private  T first;
    private V second;

    private Pair() {
        this.first = null;
        this.second = null;
    }

    private Pair(T firstT, V secondV) {
        this.first = firstT;
        this.second = secondV;
    }

    public T getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    public static <T, V> Pair<T, V> of (T firstT, V secondV) {
        return new Pair<>(firstT, secondV);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Pair<?, ?> o = (Pair<?, ?>) other;
        boolean firstTrue = false;
        boolean secondTrue = false;
        if ((first == null & o.first != null) | (first != null & o.first == null)
                | (second == null & o.second != null) | second != null & o.second == null) {
            return false;
        }
        if ((first == null && o.first == null) || (first.equals(o.first))) {
            firstTrue = true;
        }
        if ((second == null && o.second == null) || (second.equals(o.second))) {
            secondTrue = true;
        }
        return firstTrue && secondTrue;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 31 + (first == null ? 0 : first.hashCode());
        hash = hash * 31 + (second == null ? 0 : second.hashCode());
        return hash;
    }
}
