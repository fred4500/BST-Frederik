import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.List;
public class Tester
{
    private static int maxMenuItemLength = 30;
    private static String[] menuItems = {"A. Build BST from file",
            "B. Add Node",
            "C. Delete Node",
            "D. Print Tree in Level Order",
            "E. In Order Traversal",
            "F. Pre Order Traversal",
            "G. Post Order Traversal",
            "H. Check if balanced",
            "I. Rebalance Tree",
            "J. Find Path to Value",
            "K. Min",
            "L. Max",
            "M. Find Values in Range",
            "N. Height",
            "O. Number of nodes in Tree",
            "Q. Quit"};
    
    private static void displayTwoColumnMenu(String[] menu)
    {      
        System.out.println("--------------------------------------------------------");
        for (int i=0; i<menu.length/2; i++)
            System.out.println(menu[i] + getSpace(menu[i].length()) + menu[i + menu.length/2] );
        if (menu.length % 2 == 1) //if odd number of menu items
            System.out.println(menu[menu.length-1]);//then print the last menu item
        System.out.println("--------------------------------------------------------");
    }

    private static String getSpace(int length)
    {
        String space = " ";
        for (int i=0; i < maxMenuItemLength - length; i++)
            space += " ";
        return space;
    }

    private static String blankLines(int length)
    {
        String blankLines = "";
        for (int i=0; i<length; i++)
            blankLines += "\n";
        return blankLines;
    }

    public static void main(String args[])
    {   BST<Integer/*, BstNode<Integer>*/> tree = new BST<>();
        Scanner in = new Scanner(System.in);
        String selection = " ";
        while(!selection.equals("Q"))
        {
            tree.printLevelOrder();
            displayTwoColumnMenu(menuItems);
            selection = in.nextLine().substring(0,1).toUpperCase();
            // Build Tree from File
            try{
            if (selection.equals("A")){
                System.out.print("Enter filename: ");
                String filename = in.nextLine();
                try{
                    tree = new BST<Integer/*, BstNode<Integer>*/>(filename);

                }catch(FileNotFoundException e){
                    System.out.println("File not found");
                }
            }
            // Add Node
            if(selection.equals("B")){
                System.out.print("Enter value to add: ");
                String value = in.nextLine();
                try{
                    int intValue = Integer.parseInt(value);
                    tree.addNode(intValue);

                }catch(Exception e){
                    System.out.println(e);
                }
            }
            //Remove Node
            if(selection.equals("C")){
                System.out.print("Enter value to remove: ");
                String value = in.nextLine();
                try{
                    int intValue = Integer.parseInt(value);
                    tree.removeNode(intValue);

                }catch(Exception e){
                    System.out.println(e);
                }
            }                
            //Print Tree in Level Order
            if (selection.equals("D"))
            {
                System.out.println("Level Order: " + tree.levelOrderList());
            }
            //In order traversal
            if (selection.equals("E")){
                System.out.println("In Order: " + tree.inOrderList());
            }
            //Pre order traversal
            if (selection.equals("F")){
                System.out.println("Pre Order: " + tree.preOrderList());
            }
            //Post order traversal
            if (selection.equals("G")){
                System.out.println("Post Order: " + tree.postOrderList());
            }
            //Check if Balanced
            if (selection.equals("H")){
                System.out.println(tree.isBalanced()?"Tree is Balanced":"Tree is NOT Balanced");
            }
            //Rebalance Tree
            if (selection.equals("I")){
                tree.rebalance();
            }
            //Find Path to Value
            if (selection.equals("J")){
                System.out.print("Enter value to find: ");
                String value = in.nextLine();
                try{
                    int intValue = Integer.parseInt(value); 
                    if(tree.contains(intValue))
                        System.out.println(tree.pathToNode(intValue));
                    else
                        System.out.println("Not Found.");
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }
            //Min Value
            if (selection.equals("K")){
                System.out.println("Min value: " + tree.minValue());
            }
            //Max Value
            if (selection.equals("L")){
                System.out.println("Max value: " + tree.maxValue());
            }
            //Range of Values
            if (selection.equals("M")){
                System.out.print("Enter lowBound");
                String lowBound = in.nextLine();
                System.out.print("Enter highBound");
                String highBound = in.nextLine();
                try{
                    List result = tree.range(Integer.parseInt(lowBound),Integer.parseInt(highBound));

                    if(result == null)
                        System.out.println("There are not values in that range");
                    else
                        System.out.println(result);     
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }  
            //Height
            if (selection.equals("N")){
                System.out.println(tree.height());
            }
            //Number of Nodes
            if (selection.equals("O")){
                System.out.println("Number of Nodes: " + tree.size());
            }
        }catch(UnsupportedOperationException e){
            System.out.println(e );
            //e.printStackTrace();
        }
        }
        System.out.println("Goodbye");
    }

}
