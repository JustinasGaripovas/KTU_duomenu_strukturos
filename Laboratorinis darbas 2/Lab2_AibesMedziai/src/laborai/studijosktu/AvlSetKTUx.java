package laborai.studijosktu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;

public class AvlSetKTUx<E extends KTUable<E>> extends AvlSetKTU<E>
        implements SortedSetADTx<E> {

    private final E baseObj;       // bazinis objektas skirtas naujų kūrimui

    /**
     * Konstruktorius su bazinio objekto fiksacija dėl naujų elementų kūrimo
     *
     * @param baseObj
     */
    public AvlSetKTUx(E baseObj) {
        super();
        this.baseObj = baseObj;
    }
    
    public E higer(E e)
    {
        return null;
    }
    
    /**
     * Konstruktorius su bazinio objekto fiksacija dėl naujų elementų kūrimo
     *
     * @param baseObj
     * @param c
     */
    public AvlSetKTUx(E baseObj, Comparator<? super E> c) {
        super(c);
        this.baseObj = baseObj;
    }

    /**
     * Sukuria elementą iš String ir įdeda jį į pabaigą
     *
     * @param dataString
     */
    @Override
    public void add(String dataString) {
        add((E) baseObj.create(dataString));
    }

    /**
     * Suformuoja sąrašą iš filePath failo
     *
     * @param filePath
     */
    @Override
    public void load(String filePath) {
        clear();
        if (filePath == null || filePath.length() == 0) {
            return;
        }
        if (baseObj == null) { // elementų kūrimui reikalingas baseObj
            Ks.ern("Naudojant load-metodą, "
                    + "reikia taikyti konstruktorių = new ListKTU(new E())");
        }
        try {
            try (BufferedReader fReader = Files.newBufferedReader(Paths.get(filePath), Charset.defaultCharset())) {
                fReader.lines().filter(line -> line != null && !line.isEmpty()).forEach(line -> add(line));
            }
        } catch (FileNotFoundException e) {
            Ks.ern("Duomenų failas " + filePath + " nerastas");
        } catch (IOException e) {
            Ks.ern("Failo " + filePath + " skaitymo klaida");
        }
    }

    @Override
    public BstSetKTU<E> subSet(E element1, boolean fromInclusive, E element2, boolean toInclusive) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsAll(BstSetKTU c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BstSetKTU subSet(E from, E to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
