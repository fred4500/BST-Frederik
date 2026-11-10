import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class BST<E extends Comparable<E>/*, T extends Treeable<E>*/>
{
    private Treeable<E> rootNode;
    //constructor: Null tree
    public BST()
    {
        rootNode = null;
    }

    //constructor: Root node constructed with value i
    public BST(E val) 
    {
        rootNode = new BstNode(val); 
        
        //NOTE:  Really we should not be using the specific BSTNode class here.
        //Instead, we could accept a NODE Type as a generic parameter.  Then, we could
        //use the generic parameter to get an instance of that class - but this might
        //require some extra coding to accomplish.
    }

    public BST (String filename) throws FileNotFoundException, NumberFormatException
    {
        Scanner myFile= new Scanner(new File(filename));
        while (myFile.hasNextLine())
        {
            String line = myFile.nextLine();
            E value = (E)(Integer)(Integer.parseInt(line));
            addNode(value); //add node will set the root node value if necessary
        }  
        myFile.close();        
    }

    //adds a new Node with value i to the BST.  maintains correct value for levels
    //Note - this could be rewritten as a recursive method instead of an iterative method.
    public void addNode(E item)
    {
        Treeable<E> currentNode = rootNode;
        if (currentNode == null)
        {
            rootNode = new BstNode(item);
            //NOTE:  Really we should not be using the specific BSTNode class here.
            //Instead, we could accept a NODE Type as a generic parameter.  Then, we could
            //use the generic parameter to get an instance of that class - but this might
            //require some extra coding to accomplish and is beyond the scope of this course.
            return;
        }

        boolean finished = false;
        do
        {
            if(item.compareTo(currentNode.getValue()) > 0)
            { //look down the right branch
                if(currentNode.getRight() == null)
                { //create a new node referenced with currentNode.rightNode
                    currentNode.setRight(new BstNode(item));
                    finished = true;
                    System.out.println("added to right");
                }
                else { //keep looking right
                    currentNode = currentNode.getRight();
                     System.out.println("moving right");
                    }
            }
            else if (item.compareTo(currentNode.getValue()) < 0)
            { //look down the left branch
                if(currentNode.getLeft() == null)
                { //create a new node referenced with currentNode.leftNode
                    currentNode.setLeft(new BstNode(item));
                    finished = true;
                    System.out.println("added to left");
                }
                else { //keep looking left
                    currentNode = currentNode.getLeft();
                     System.out.println("moving left");
                    }
            }
            else throw new IllegalArgumentException(item + " is alreaady in the BST and cannot be added.");
        }while(!finished);
    }

    // mutator method - removes the node with value i, if it exists
    // recalculates levels
    public void removeNode(E item)
    {
        throw new UnsupportedOperationException("not implemented.  Please implement this method in BST");       
    }
    //returns parent BstNode of given BstNode.
    private Treeable<E> parentOf(Treeable<E> child)
    {
        Treeable<E> currentNode = rootNode;
        Treeable<E> parent = null;
        while(currentNode != null)
        {
            if(child.getValue().equals(currentNode.getValue()))
                return parent; 
            else if(child.getValue().compareTo(currentNode.getValue()) > 0)
            {
                parent = currentNode;
                currentNode = currentNode.getRight();
            }
            else if(child.getValue().compareTo(currentNode.getValue()) < 0)
            {
                parent = currentNode;
                currentNode = currentNode.getLeft();
            }
        }
        return null;
    }
    
    public int height()
    {
        throw new UnsupportedOperationException("not implemented.  Please implement this method in BST");
    }

    public int calculateLevels()
    {
        return calculateLevelsFrom(rootNode);
    }

    private int calculateLevelsFrom(Treeable<E> currentNode)
    {
        if (currentNode == null)
            return 0;
        else
        {
            int leftLevels = calculateLevelsFrom(currentNode.getLeft());
            int rightLevels = calculateLevelsFrom(currentNode.getRight());

            if (leftLevels > rightLevels)
                return 1 + leftLevels;
            else
                return 1 + rightLevels;
        }       
    }

    public ArrayList<E> inOrderList()
    {
        ArrayList<E> listOfNodes = new ArrayList<E>();
        inOrderList(rootNode, listOfNodes);
        return listOfNodes;
    }

    private void inOrderList(Treeable<E> start, List<E> listOfNodes)
    {
        throw new UnsupportedOperationException("not implemented.  Please implement this method in BST");
    }

    public ArrayList<E> preOrderList()
    {
        ArrayList<E> listOfNodes = new ArrayList<E>();
        preOrderList(rootNode, listOfNodes);
        return listOfNodes;
    }

    private void preOrderList(Treeable<E> start, ArrayList<E> listOfNodes)
    {
        throw new UnsupportedOperationException("not implemented.  Please implement this method in BST");
    }

    public ArrayList<E> postOrderList()
    {
        ArrayList<E> listOfNodes = new ArrayList<E>();
        postOrderList(rootNode, listOfNodes);
        return listOfNodes;

    }

    private void postOrderList(Treeable start, ArrayList<E> listOfNodes)
    {
        throw new UnsupportedOperationException("not implemented.  Please implement this method in BST");
    }

    public ArrayList<E> levelOrderList()
    {
        ArrayList<E> levelOrder = new ArrayList<E>();

        int levels = calculateLevels();
        for (int i=0; i<levels; i++)
        {
            addLevelToLevelOrderList(levelOrder, rootNode, i); 
        }
        return levelOrder;
    }

    private void addLevelToLevelOrderList(ArrayList<E> levelOrder, Treeable start, int distanceToGo)
    {
        if (start == null)
        {
            for(int i=0; i<Math.pow(2,distanceToGo); i++)
                levelOrder.add(null);
        }
        else if (distanceToGo == 0) 
            levelOrder.add((E)(start.getValue()));
        else if (distanceToGo > 0) 
        { 
            addLevelToLevelOrderList(levelOrder, start.getLeft(), distanceToGo-1); 
            addLevelToLevelOrderList(levelOrder, start.getRight(), distanceToGo-1);
        } 
    }

    // Prints level order of tree, one level per line, with appropriate spacing between nodes on a level
    public void printLevelOrder() 
    {  
        ArrayList<E> levelOrderList = levelOrderList();
        for (int level=0; level<calculateLevels(); level++)
        {
            String spacing = spaces(level);
            for(int nodePos = (int)Math.pow(2,level); nodePos < (int)Math.pow(2,level+1); nodePos++)
            {
                E value = levelOrderList.get(nodePos-1);
                String text;
                if (value == null)
                    text = "----";
                else
                    text = value + "";
                System.out.print(spacing + String.format("%4s",text) + spacing);
            }
            System.out.println();
        }
    } 
    
    //private method returns a String with num spaces
    private String spaces(int level)
    {
        int num = ((int)Math.round(Math.pow(2,calculateLevels()-level)*4-3))/2;
        String spaceOutputString = "";
        for(int i=0; i < num; i++)
            spaceOutputString += " ";
        return spaceOutputString;
    }

    public void traverseAndPrint()
    {
        traverseAndPrint(rootNode);
    }

    public void traverseAndPrint(Treeable currentNode )
    {
        System.out.print("data = " + currentNode.getValue());

        if(currentNode.getLeft() == null)
        {
            System.out.print("left = null");
        }
        else
        {
            System.out.print("left = " + (currentNode.getLeft()).getValue());
        }
        if(currentNode.getRight() == null)
        {
            System.out.print("right = null");
        }
        else
        {
            System.out.print("right =" + (currentNode.getRight()).getValue());
        }
        System.out.println("");
        if(currentNode.getLeft() != null)
            traverseAndPrint(currentNode.getLeft());

        if(currentNode.getRight() != null)
            traverseAndPrint(currentNode.getRight());
    }
    
    public boolean isBalanced(){
        return isBalanced(rootNode);
    }

    private boolean isBalanced(Treeable currentNode){
        throw new UnsupportedOperationException("not implemented.  Please implement this method in BST");
    }
    //returns true if given element is found
    public boolean contains(E i)
    {
        throw new UnsupportedOperationException("not implemented.  Please implement this method in BST");
    }

    //returns the BstNode that contains a given value, or unll if no such BstNode exists
    public Treeable findNode(E item)
    {
        throw new UnsupportedOperationException("not implemented.  Please implement this method in BST");
    }

    public ArrayList<E> range(E lowBound, E highBound)
    {
        ArrayList<E> resultList = new ArrayList<E>();
        Treeable<E> start = rootNode;
        range(start, lowBound, highBound, resultList);
        return resultList;
    }        

    private void range(Treeable<E> start, E lowBound, E highBound, ArrayList<E> listOfNodes)
    {    
        throw new UnsupportedOperationException("not implemented.  Please implement this method in BST");        
    }

    public String pathToNode(E i)
    {
        throw new UnsupportedOperationException("not implemented.  Please implement this method in BST"); 
    }

    //return the number of nodes in the BST
    public int size()
    {
        throw new UnsupportedOperationException("not implemented.  Please implement this method in BST"); 
    }

    public void rebalance()
    {
        ArrayList<E> inOrderList = inOrderList();
        int start = 0;
        int end = inOrderList.size() - 1;
        BST newBST = new BST();
        newBST.bstFromList(inOrderList, start, end);
        rootNode = newBST.rootNode;
    } 

    
    //adds the values from ArrayList<E> values starting at position start and going to position end, to the BST
    private void bstFromList(ArrayList<E> values, int start, int end)
    {
        throw new UnsupportedOperationException("not implemented.  Please implement this method in BST");
    }  

    public E minValue()
    {
        throw new UnsupportedOperationException("not implemented.  Please implement this method in BST");
    }

    public E maxValue()
    {
        throw new UnsupportedOperationException("not implemented.  Please implement this method in BST");
    }
}