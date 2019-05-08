/**Student name: Long Nguyen
Class: CS3345
Section: 004 
Semester: Spring 2019
Project: 2
Description: Create a list of magazine using self-design linked list 
*/
package project2;

/**
 *
 * @author Sorairono
 */
import java.util.*;
import java.io.*;
//IDedObject interface
interface IDedObject 
{
	int getID();
	void printID();
}

//Magazine class, implement IDedObject interface
class Magazine implements IDedObject
{
	//Private data for class
	private int magazineID;
	private String magazineName;
	private String publisherName;
	//Default constructor
	public Magazine()
	{
		magazineID = 0;
		magazineName = null;
		publisherName = null;
	}
	//Constructor
	public Magazine(int id, String name, String person)
	{
		magazineID = id;
		magazineName = name;
		publisherName = person;
	}
	//Print a magazine's information
	public void printID()
	{
		System.out.println("Magazine ID: " + magazineID);
		System.out.println("Magazine name: " + magazineName);
		System.out.println("Publisher name: " + publisherName);
	}
	//Get ID of the magazine
	public int getID()
	{
		return magazineID;
	}	
}

//MyLinkedList class
class MyLinkedList<AnyType extends IDedObject> 
{
	//Node class
	class Node 
	{
		private AnyType data;
		private Node next;
		Node (AnyType d)
		{
			data = d;
			next = null;
		}
		//Method to get/set node/data
		public AnyType getData()
		{
			return data;
		}
		public Node getNode()
		{
			return next;
		}
		public void setData(AnyType d)
		{
			data = d;
		}
		public void setNode(Node temp)
		{
			next = temp;
		}	
	}
	private Node head;
	//Default constructor
	public MyLinkedList()
	{
		head = null;
	}
	//Make the list empty by making head pointer to be null
	public void makeEmpty()
	{
		head = null;
	}
	//Find ID, if not found return null
	public AnyType findID(int id)
	{
		Node temp = head;
		while (temp != null)
		{
			if (temp.getData().getID() == id)
			{
				return temp.getData();
			}
			else 
			{
				temp = temp.getNode();
			}
		}
		return null;
	}
	//Insert at front, return false if the object is already in the list
	public boolean insertAtFront(AnyType newInsert)
	{
		Node newNode = new Node(newInsert);
		if (head == null)
		{
			head = newNode;
			return true;
		}
		Node temp = head;
		while (temp != null)
		{
			if (temp.getData().getID() == newInsert.getID())
			{
				return false;
			}
			else 
			{
				temp = temp.getNode();
			}
		}
		newNode.setNode(head);
		head = newNode; 
		return true;
	}
	//Delete from front, return null if the list is empty
	public AnyType deleteFromFront()
	{
		if (head == null)
		{
			return null;
		}
		AnyType deleted = head.getData();
		head = head.getNode();
		return deleted;
	}
	//Delete from front, return null if the object is not in the list
	public AnyType delete(int id)
	{
		if (head == null)
		{
			return null;
		}
		if (head.getData().getID() == id)
		{
			AnyType deleted = head.getData();
			head = head.getNode();
			return deleted;
		}
		AnyType deleted = null;
		Node prev = head;
		Node curr = head.getNode();
		while (curr != null)
		{
			if (curr.getData().getID() == id)
			{
				deleted = curr.getData();
				prev.setNode(curr.getNode());
				return deleted;
			}
			else 
			{
				prev = curr;
				curr = curr.getNode();
			}
		}
		return null;
	}
	//Print all objects in the list
	public void printAllRecords()
	{
		if (head == null)
		{
			System.out.println("The magazine list is empty at the moment.");
		}
		Node temp = head;
		while (temp != null)
		{
			temp.getData().printID();
			temp = temp.getNode();
		}
	}
}
public class Project2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Create a magazine list from MyLinkedList class, generic type Magazine
        MyLinkedList<Magazine> magazineList = new MyLinkedList<Magazine>();
        //String for display menu
        String menu = "Operation on List\n" + "1. Make Empty\n" + "2. Find ID\n"
        + "3. Insert At Front\n" + "4. Delete From Front\n" + "5. Delete ID\n" + 
        "6. Print All Records\n" + "7. Done\n" + "Your choice: ";
        System.out.print(menu);
        //Get user's choice
        int choice = getPositiveInput();
        while (choice != 7)
        {
        	Magazine magazine, deletedMagazine;
        	int id;
        	String name, person;
        	//Based on user's option do the appropriate action
        	switch(choice)
        	{
        		//Make the list empty
        		case 1:
	        		magazineList.makeEmpty();
	        		System.out.println("The list is empty now");
	        		break;
	        	//Find a magazine in the list based on ID
	        	case 2:
	        		System.out.print("Enter ID of the magazine: ");
	        		id = getPositiveInput();
	        		magazine = magazineList.findID(id);
	        		if (magazine == null)
	        		{
	        			System.out.println("This magazine is not in the list.");
	        		}
	        		else
	        		{
	        			magazine.printID();
	        		}
	        		break;
	        	//Add a new magazine to the front of the list
	        	case 3:
	        		System.out.print("Enter Magazine ID: ");
	        		id = getPositiveInput();
	        		System.out.print("Enter Magazine Name: ");
	        		name = getNonEmptyString();
	        		System.out.print("Enter Publisher Name: ");
	        		person = getNonEmptyString();
	        		magazine = new Magazine(id, name, person);
	        		if (magazineList.insertAtFront(magazine) == false)
	        		{
	        			System.out.println("This magazine already existed in the list.");
	        		}
	        		else 
	        		{
	        			System.out.println("...");
	        			System.out.println("Magazine added.");
	        		}
	     			break;
	        	//Delete the magazine list from the front
	        	case 4:
	        		deletedMagazine = magazineList.deleteFromFront();
	        		if (deletedMagazine == null)
	        		{
	        			System.out.println("The magazine list is empty at the moment");
	        		}
	        		else 
	        		{
	        			deletedMagazine.printID();
	        			System.out.println("First item deleted.");
	        		}
	        		break;
	        	//Delete a specific magazine based on its ID
	        	case 5:
	        		System.out.print("Enter Magazine ID need to be deleted: ");
	        		id = getPositiveInput();
	        		deletedMagazine = magazineList.delete(id);
	        		if (deletedMagazine == null)
	        		{
	        			System.out.println("This magazine is not found in the list");
	        		}
	        		else 
	        		{
	        			deletedMagazine.printID();
	        			System.out.println("This magazine has been deleted from the list");
	        		}
	        		break;
	        	//Print all current magazines in the list
	        	case 6:
	        		magazineList.printAllRecords();
	        		break;
	        	//If the choice user enter does not exist
	        	default: 
	        		System.out.println("Invalid choice");
	        		break;
        	}
        	//Display menu and get a new choice
        	System.out.print(menu);
        	choice = getPositiveInput();
        }
        System.out.println("Done");
    }

    //Method to get a positive integer input
    public static int getPositiveInput()
   	{
   		Scanner sc = new Scanner(System.in);
        int number;
        do 
        {
        	//Check if the input is an integer
            while (!sc.hasNextInt()) 
            {
                String input = sc.next();
                System.out.printf("\"%s\" is not a valid number.\n", input);
            }
            number = sc.nextInt();
            //Check if the input is positive
            if (number < 0)
            {
            	System.out.print("Please enter a positive number: ");
            }
        } 
        while (number < 0);
        return number;
   	}

   	//Method to get a non-empty string
   	public static String getNonEmptyString()
   	{
   		Scanner sc = new Scanner(System.in);
   		String str;
   		do 
   		{
   			str = sc.nextLine();
   			//Check if the string is empty or contains of whitespace
   			if (str == null || str.trim().isEmpty() == true)
   			{
   				System.out.print("Please enter a non-empty and not only whitespace string: ");
   			}
   		}
   		while (str == null || str.trim().isEmpty() == true);
   		return str;
   	}
}
