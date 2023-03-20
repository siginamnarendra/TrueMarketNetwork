package marketNetwork;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class NewMember {

	String name, UserID, ParentID;
	static NewMember root = null;
	NewMember parentAddress;
	double Commission;
	List<NewMember> ChildrenList = new ArrayList<NewMember>();

	NewMember(String name, String UserID, String ParentID) {
		this.name = name;
		this.ParentID = ParentID;
		this.UserID = UserID;

	}

	public static NewMember FindUser(NewMember root, String id) {
		if(root==null)
		{
			System.out.println("No users");
			return null;
		}

		else
		{
		Queue<NewMember> q = new LinkedList<NewMember>();
		q.add(root);
		
		while (!q.isEmpty()) {
			int n = q.size();

			while (n > 0) {

				NewMember u = q.peek();
				
				if (u.UserID.equals(id))
				 {
					System.out.println();
					return u;
				 }

				q.remove();
				for (int i = 0; i < u.ChildrenList.size(); i++) {
					q.add(u.ChildrenList.get(i));
				}
				n--;
			}
			System.out.println();
		}
		
		return null;
	}
	}

	static void printing (NewMember root)
	{
		if(root==null)
		{
			System.out.println("No users");
			
		}

		else
		{
		int level =0;
	    Queue<NewMember >q = new LinkedList<>();
	    q.add(root);
	    while (!q.isEmpty())
	    {
	        int n = q.size();
			System.out.println("At Level :"+level);
	        while (n > 0)
	        {
	            
	            NewMember p = q.peek();
	            q.remove();
	            System.out.print(p.name + " ");
	 
	            for (int i = 0; i < p.ChildrenList.size(); i++)
	                q.add(p.ChildrenList.get(i));
	            n--;
	        }
			level++;
	        System.out.println();
	    }
		}
	}

	
	
}