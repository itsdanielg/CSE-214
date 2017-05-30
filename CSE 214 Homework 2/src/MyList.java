/**
 * @author Ritwik Banerjee
 */
public interface MyList<T> extends MyCollection<T> {
    
    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (<code>index &lt; 0 || index &gt;= size()</code>)
     */
    T get(int index);
    
    /**
     * Replaces the element at the specified position in this list with the specified element (optional operation).
     *
     * @param index index of the element to replace
     * @param t     element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws ClassCastException        if the class of the specified element prevents it from being added to the list
     * @throws NullPointerException      if the specified element is null and the list does not permit null elements
     * @throws IllegalArgumentException  if some property of the specified element prevents it from being added to this
     *                                   list
     * @throws IndexOutOfBoundsException if the index is out of range (<code>index &lt; 0 || index &gt;= size()</code>)
     */
    T set(int index, T t);
    
    /**
     * Inserts the specified element at the specified position in this list. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param t     element to be inserted
     * @throws ClassCastException        if the class of the specified element prevents it from being added to the list
     * @throws NullPointerException      if the specified element is null and the list does not permit null elements
     * @throws IllegalArgumentException  if some property of the specified element prevents it from being added to this
     *                                   list
     * @throws IndexOutOfBoundsException if the index is out of range (<code>index &lt; 0 || index &gt;= size()</code>)
     */
    void add(int index, T t);
    
    /**
     * Removes the element at the specified position in this list. Shifts any subsequent elements to the left
     * (subtracts one from their indices).  Returns the element that was removed from the list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (<code>index &lt; 0 || index &gt;= size()</code>)
     */
    T remove(int index);
}
