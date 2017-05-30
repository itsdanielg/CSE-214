/**
 * @author Ritwik Banerjee
 */

public interface MyCollection<T> extends Iterable<T> {
    
    /**
     * @return the number of elements in the collection
     */
    int size();
    
    /**
     * @return <code>true</code> if the collection contains no elements, <code>false</code> otherwise.
     */
    boolean isEmpty();
    
    /**
     * Removes all elements from the collection. The collection will be empty after this method returns.
     */
    void clear();
    
    /**
     * Returns <code>true</code> if this collection contains the specified element. More formally, returns
     * <code>true</code> if and only if this collection contains at least one element <code>e</code> such that
     * <code>(t == null ? e == null : o.equals(e))</code>.
     *
     * @param t element whose presence in this collection is to be tested
     * @return <code>true</code> if this collection contains the specified element
     * @throws ClassCastException   if the type of the specified element is incompatible with this collection
     * @throws NullPointerException if the specified element is null and this collection does not permit null elements
     */
    boolean contains(T t);
    
    /**
     * Ensures that the specified element is in the collection. Returns <code>true</code> if this collection changed as
     * a result of the call. Returns <code>false</code> if this collection does not permit duplicates and already
     * contains the specified element.
     *
     * @param t element whose presence in this collection is to be ensured
     * @return <code>true</code> if the collection changed as a result of this method call, <code>false</code> otherwise
     * @throws ClassCastException       if the class of the specified element
     *                                  prevents it from being added to this collection
     * @throws NullPointerException     if the specified element is null and this
     *                                  collection does not permit null elements
     * @throws IllegalArgumentException if some property of the element
     *                                  prevents it from being added to this collection
     */
    boolean add(T t);
    
    /**
     * Removes the first occurrence in this collection of the specified element, where 'first' is defined by the
     * iteration order in the concrete implementation of the collection.
     *
     * @param t the specified element
     * @return <code>true</code> if the collection contained the specified element.
     */
    boolean remove(T t);
    
}
