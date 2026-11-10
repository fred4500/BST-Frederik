//Implementation of a Generic BSTNode where data can be any element (E) which extends Comparable
public class BstNode<E extends Comparable<E>> implements Treeable<E>
{
    public E data;
    public Treeable leftNode;
    public Treeable rightNode;
    public BstNode(E val) //Constructor
    {
        leftNode = null;
        rightNode = null;
        data = val;
    }
      
    public int compareTo(Treeable item)
    {
        return item.getValue().compareTo(this.data);
    }
    
    public E getValue() {
        return data;
    }
    public Treeable getLeft()
    {
        return leftNode;
    }
    public Treeable getRight()
    {
        return rightNode;
    }
    public void setValue(E value)
    {
        data = value;
    }
    public void setLeft(Treeable left)
    {
        leftNode = left;
    }
    public void setRight(Treeable right)
    {
        rightNode = right;
    }
    public boolean equals(Treeable node)
    {
        return this.getValue().equals(node.getValue());
    }
}