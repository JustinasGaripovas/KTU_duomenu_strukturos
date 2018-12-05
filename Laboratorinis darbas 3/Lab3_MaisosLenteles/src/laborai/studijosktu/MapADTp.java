package laborai.studijosktu;

import java.util.List;

/**
 *
 * @param <K> raktas
 * @param <V> reikšmė
 */
public interface MapADTp<K, V> extends MapADT<K, V> {

    /**
     * Grąžina maksimalų grandinėlės ilgį.
     *
     * @return Maksimalus grandinėlės ilgis.
     */
    int getMaxChainSize();
    
    int emptyElementCount();
    
    public List<K> keySet();
    
    public V replace(K key, V value);

    double avgChainLength();

    /**
     * Grąžina maišos lentelę formuojant įvykusių permaišymų kiekį.
     *
     * @return Permaišymų kiekis.
     */
    int getRehashesCounter();

    /**
     * Grąžina maišos lentelės talpą.
     *
     * @return Maišos lentelės talpa.
     */
    int getTableCapacity();

    /**
     * Grąžina paskutinės papildytos grandinėlės indeksą.
     *
     * @return Paskutinės papildytos grandinėlės indeksas.
     */
    int getLastUpdatedChain();

    /**
     * Grąžina grandinėlių kiekį.
     *
     * @return Grandinėlių kiekis.
     */
    int getChainsCounter();

}
