package laborai.studijosktu;

import laborai.demo.Automobilis;

public interface SortedSetADTx<E> extends SortedSetADT<E> {

    void add(String dataString);
 
    //public boolean containsAll(BstSetKTU<E> n);

    void load(String fName);

    String toVisualizedString(String dataCodeDelimiter);

    Object clone() throws CloneNotSupportedException;

    //public Object higher(Object object);

    public E higher(E object);

    public E pollLast();

    public BstSetKTU subSet(E from, boolean fromInclusive, E to, boolean toInclusive);

    public BstSetKTU subSet(E from, E to);

    public boolean containsAll(BstSetKTU c);
}