package marketNetwork;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class UserInterface {

	static int ui=1,option;
	static void UserInterfaceM()
	{
		int looping=0;
		do
		{
			System.out.println("                         Welcome to the ** True Market pvt Ltd **");
			System.out.println();
			System.out.println();
			System.out.println("  1)Member Registration ");
			System.out.println("  2)Member Purchase ");
			System.out.println("  3)Display the Members at each level");
			System.out.println("  4)Display Commission");
			System.out.println("  5)Enter values form CSV");
			System.out.println();
			System.out.print(" Please select any of the options (1, 2, 3, 4, 5) which ever you want to proceed  ");
		
			option=sc.nextInt();

			switch(option)
			{
				case 1:
				System.out.println("Enter your Name");
				String UserName=sc.next();
				System.out.println("Enter your UserID");
				String UserID=sc.next();
				String ParentID=null;

				if(NewMember.root != null )
				{
					System.out.println("Enter your ParentID");
					ParentID=sc.next();
					NewMember user = new NewMember(UserName,UserID, ParentID);
					user.parentAddress = NewMember.FindUser(NewMember.root, ParentID);

					if(user.parentAddress==null)
					{
						System.out.println("The parent does not exist.");
					}
					else
					{
						System.out.println("parent is: "+user.parentAddress.name);
						user.parentAddress.ChildrenList.add(user);
						Commission.addCommision(user.parentAddress, 100000.0);
					}

					System.out.println("Your Account got created. Your Id is: "+(user.UserID)+"  All the Best!");
				}
				else
				{
					NewMember.root = new NewMember(UserName,UserID, ParentID);
					System.out.println("Your Account got created. Your Id is: "+(NewMember.root.UserID)+"  All the Best!");
				}
				break;

				case 2:
				System.out.println("Enter User ID: ");
				UserID= sc.next();
				NewMember user = NewMember.FindUser(NewMember.root, UserID);
				Purchase.purchaseDisplay(user.parentAddress);	
				break;

				case 3:
				NewMember.printing(NewMember.root);
				break;

				case 4:
				System.out.println("Enter userId to find commission");
				UserID = sc.next();
				System.out.println(NewMember.FindUser(NewMember.root, UserID).Commission);
				break;

				case 5:
																																//String path = "Network-Marketing\\src\\marketNetwork\\treeData.csv";
				String path ="C:\\Users\\TrueID Pvt Ltd\\Downloads\\treeData.csv";
				try  
				{
				BufferedReader br = new BufferedReader(new FileReader(path));
				String line;
				while((line=br.readLine())!=null)
				{
					String elements [] =line.split(",");
					UserName = elements[0];
					UserID = elements[1];
					if(elements.length==2)
					{
						ParentID = null;  
					}
					else
					{
						ParentID = elements[2];
					}

					newUser(UserName,UserID,ParentID);
//
					//System.out.println(line);
				}
				br.close();

				}
				catch(IOException e)
				{
					e.printStackTrace();;
				}
				
				 break;

				default:
					looping=1;
					System.out.println("Please enter valid ID(1 or 2 or 3) ");
					break;
			}
		}while(looping==1);
		if(option!=2)
		{
			System.out.println("Please enter '1' for Main menu and '0' for exit");
			ui=sc.nextInt();
		}

	}

	public static void newUser(String UserName, String UserID, String ParentID)
	{
		if(NewMember.root==null)
		{
			NewMember.root = new NewMember(UserName,UserID, ParentID);
		}
		
		NewMember user = new NewMember(UserName,UserID, ParentID);
		user.parentAddress = NewMember.FindUser(NewMember.root, ParentID);

		if(NewMember.root != user && user.parentAddress!=null)
		{
			user.parentAddress.ChildrenList.add(user);
			Commission.addCommision(user.parentAddress, 100000.0);
		}	 

	}

	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		while(ui==1||ui==2)
		{
			if(ui==1)
			{
				UserInterfaceM();
			}
			else
			{
				System.out.println("Enter User ID: ");
				String userid = sc.next();
				NewMember user = NewMember.FindUser(NewMember.root, userid);
				Purchase.purchaseDisplay(user.parentAddress);
			}
		}
		System.out.println("You are logged Out");
	}
}