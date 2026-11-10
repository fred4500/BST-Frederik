public interface Treeable<E extends Comparable<E>>
{
    public E getValue();
    public Treeable getLeft();
    public Treeable getRight();
    public void setValue(E value);
    public void setLeft(Treeable left);
    public void setRight(Treeable right);
    public boolean equals(Treeable node);
    public int compareTo(Treeable node);
}