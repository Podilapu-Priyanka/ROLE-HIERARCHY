package Implement_Hierarchy;

import java.util.*;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class Main_Function{
	
	// all used container and global variables
	 static TreeNode<String>getHead=null,temp1=null,temp2=null;
	 static ArrayList<Collection<String> >res=new ArrayList<>();
	 static HashSet<String> set=new HashSet<String>(); 
	 static Multimap<String, String> User = ArrayListMultimap.create();
	 static Multimap<String, String> Disp = ArrayListMultimap.create();
	 static int ans=-1,flag=0;
	 
     //Question_2
	 
	 public static String levelOrder(TreeNode<String> root) {
	        if (root == null)
				return " ";		
	        StringJoiner buf = new StringJoiner(", ");
	        Queue<TreeNode<String>> q = new LinkedList<TreeNode<String>>();
			
			q.add(root);
			
			while (!q.isEmpty()) {
				int s = q.size();
				
				
				for (int j = 0; j < s; j++) {
					TreeNode<String> cur = q.poll();
					if(cur!=null) {
						buf.add(String.valueOf(cur.data));
						
						
						for (TreeNode<String>child : cur.children) {
							q.add(child);
						}
					}
				}
				
				
			}
			return buf.toString();	
	    }
	 
	  
	 
	
       // Question_3_part1
       public static void find_Parent_add(TreeNode<String>root,String key){
    	if(root==null)
    		return;
    	if(root.data.equals(key)) {
    		getHead=root;
    		return;
    		
    	}
    	
    	int len=root.children.size();
    	
    	for(int i=0;i<len;i++) {
    		 find_Parent_add(root.children.get(i),key);
    	}
		
       }
       
      //Question_3_part2
      public static void find_Parent(TreeNode<String>root,String key){
    	if(root==null)
    		return;
    	else if(root.data.equals(key)) {
    		ans=1;
    		return;
    		
    	}
    	
    	int len=root.children.size();
    	
    	for(int i=0;i<len;i++) {
    		 find_Parent(root.children.get(i),key);
    	}
		
     }
   
    
    //Question_3_part3
    public static void Delete_1(TreeNode<String>root,String key,int idx,TreeNode<String>par,TreeNode<String>newHead,TreeNode<String>del){
    	if(root==null)
    		return;
    	if(root.data.equals(key)) {
    		par.children.set(idx, null);
    		int len=del.children.size();
        	
        	for(int i=0;i<len;i++) {
        		
        		par.children.add(del.children.get(i));
        	}
    		return;
    		
    	}
    	
    	int len=root.children.size();
    	
    	for(int i=0;i<len;i++) {
    		Delete_1(root.children.get(i),key,i,root,newHead,del);
    	}
    	
     }
    
    //Question_3_part4
    public static void Delete_2(TreeNode<String>root,String key,int idx,TreeNode<String>par,TreeNode<String>newHead,TreeNode<String>del){
    	if(root==null)
    		return;
    	if(root.data.equals(key)) {
    		par.children.set(idx, null);
    		int len=del.children.size();
        	
        	for(int i=0;i<len;i++) {
        		
        		newHead.children.add(del.children.get(i));
        	}
        	
    		return;
    		
    		
    	}
    	
    	int len=root.children.size();
    	
    	for(int i=0;i<len;i++) {
    		Delete_2(root.children.get(i),key,i,root,newHead,del);
    	}
    	
    }
    
    //Question_3 part
    public static void Delete_Role_Update_User(String key,String Rtransfre) {
    	HashSet<String>hs=new HashSet<>();
		for (String entry : User.get(key)) {
			
			User.put(Rtransfre, entry);
			hs.add(entry);
			
		}
		Iterator<String>i=hs.iterator();
		while(i.hasNext()) {
			User.remove(key,i.next());
		}
     }
    
     //Question_6_part_1
     public static void find_Child(TreeNode<String>root,String key){
       	if(root==null)
       		return;
       	if(root.data.equals(key)==false) {
       		
       		res.add(User.get(root.data));
       		
       		
       	}
       	
       	int len=root.children.size();
       	
       	for(int i=0;i<len;i++) {
       		 find_Child(root.children.get(i),key);
       	}
   		
       }
    
    
	   //Question 6 part_2
	    public static void Display_User_Sub_User() {
	    	Iterator<String> it = set.iterator();
	    	while (it.hasNext()) {
	    		 String entry=it.next();
	    		 System.out.print(entry+"-");
	    		 StringJoiner buf = new StringJoiner(", ");
		 		 for(String i:Disp.get(entry)) {
		 				buf.add(String.valueOf(i));
		 		 }
		 		 System.out.print(buf.toString());
		 		 System.out.println();
	        }
		
	    }
    
       // Question_8
	    public static HashSet<String> Root_to_Target(TreeNode<String>root,String val){
	    	
	    	if(root!=null && root.data.equals(val)) {
	    		
	    		HashSet<String> list =new HashSet<>();
	    		
	    		for(String key:User.get(root.data))
	    			list.add(key);
	    		return list;
	    	}
	    	int len=0;
	    	if(root!=null)
	    		len=root.children.size();
	    	
	    	for(int i=0;i<len;i++) {
	    		HashSet<String>ptc=Root_to_Target(root.children.get(i),val);
	    		if(ptc.size()>0) {
	        		for(String key:User.get(root.data))
	        			ptc.add(key);
	    			return ptc;
	    		}
	    	}
			return new HashSet<>();
	    	
	    	
	    }
	    
	    //Question_9
	    public static int get_Height(TreeNode<String>root) {
	    	
	    	
	    	int ht=0;
	    	int len=0;
	        if(root!=null)
	        	len=root.children.size();
	    	
	    	for(int i=0;i<len;i++) {
	    		int ch=get_Height(root.children.get(i));
	    		ht=Math.max(ht, ch);
	    	}
	    	ht++;
			return ht;
	    	
	    	
	    }
    
   
    
    
   
     //Question_10_part 1
  	 public static ArrayList<String> nodeToRootPath(TreeNode<String> node,String data){
  		 
  		 if(node!=null && node.data.equals(data)) {
  			 ArrayList<String>path=new ArrayList<>();
  			 path.add(node.data);
  			 return path;
  		 }
  		 
  		int len=0;
        if(node!=null)
        	len=node.children.size();
      	
      	for(int i=0;i<len;i++) {
      		 ArrayList<String>ptc=nodeToRootPath(node.children.get(i),data);
      		 if(ptc.size()>0) {
      			 ptc.add(node.data);
      			 return ptc;
      		 }	
      	}
      	
      	return new ArrayList<>();
  	 }
  	 
  	 
  	//Question_10_part 2
  	 
  	 public static String lca(TreeNode<String> node,String d1,String d2) {
  		 ArrayList<String>p1=nodeToRootPath(node,d1);
  		 ArrayList<String>p2=nodeToRootPath(node,d2);
  		
  		 int i=p1.size()-1;
  		 int j=p2.size()-1;
  		 
  		 while(i>=0 && j>=0 && p1.get(i).equals(p2.get(j))) {
  			 i--;
  			 j--;
  		 }
  		 
  		 i++;
  		 j++;
  		
  		 
  		 return p1.get(i);
  		 
  	 }
   
	public static void main(String[] args) {
		
		try
		{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter root role name : ");
		String head=sc.nextLine();
		
		TreeNode<String> root=new TreeNode<>(head);
		
		while(true) {
			System.out.println("Operations: ");
			System.out.println("     1. Add Sub Role.");
			System.out.println("     2. Display Roles.");
			System.out.println("     3. Delete Role.");
			System.out.println("     4. Add User.");
			System.out.println("     5. Display Users.");
			System.out.println("     6. Display Users and Sub Users.");
			System.out.println("     7. Delete User.");
			System.out.println("     8. Number of users from top.");
			System.out.println("     9. Height of role hierachy.");
			System.out.println("     10.Common boss of users.");
			System.out.println();
			System.out.println("Operations to be Performed: ");
			int x=sc.nextInt();
			sc.nextLine();  
			if(x==1) {
				System.out.println("Enter sub role name : ");
				String child=sc.nextLine();
				System.out.println("Enter reporting to role name : ");
				String curr=sc.nextLine();
				getHead=null;
				find_Parent_add(root,curr);
				TreeNode<String> currRoot=getHead;
				TreeNode<String> r1=new TreeNode<>(child);
				currRoot.children.add(r1);
					
			}
			
			else if(x==2) {
				String res=levelOrder(root);
				System.out.println(res);	
				System.out.println();
			}
			else if(x==3) {
				
				System.out.println("Enter the role to be deleted :");
				String role_to_be_deleted=sc.nextLine();
				System.out.println("Enter the role to be transferred: ");
				String role_to_be_transferred=sc.nextLine();
				
				//check if transfered role lies in the subtree of deleted
				
				getHead=null;
				find_Parent_add(root,role_to_be_deleted);
				ans=-1;
				find_Parent(getHead,role_to_be_transferred);
				getHead=null;
				find_Parent_add(root,role_to_be_deleted);
				temp1=getHead;
				getHead=null;
				find_Parent_add(root,role_to_be_transferred);
				temp2=temp1;
				if(ans==-1) {
					Delete_2(root,role_to_be_deleted,0,null,getHead,temp2);
					
				}
				else {
					Delete_1(root,role_to_be_deleted,0,null,getHead,temp2);
				}
				
				Delete_Role_Update_User(role_to_be_deleted,role_to_be_transferred);
					
			}
			else if(x==4) {
				System.out.println("Enter User Name : ");
				String name=sc.nextLine();
				System.out.println("Enter Role : ");
				String rName=sc.nextLine();
				User.put(rName,name);	
			}
			else if(x==5) {
				System.out.println();
				for (Map.Entry<String,String> entry : User.entries())
		            System.out.println(entry.getValue() +"-"+ entry.getKey());
			}
			else if(x==6) {
				System.out.println();
				Disp.clear();
				for (String entry : User.keySet()) {
					res.clear();
					getHead=null;
					find_Parent_add(root,entry);
					find_Child(getHead,entry);
					for (String key : User.get(entry)) {
						set.add(key);
						for(Collection<String>f :res) {
							for(String s:f)
								Disp.put(key,s);
						}
					
					}	
				}
				Display_User_Sub_User();
				System.out.println();
		           
			}
			else if(x==7) {
				System.out.println("Enter username to be deleted : ");
				String val=sc.nextLine();
				HashMap<String,String>temp=new HashMap<>();
				
				for (Map.Entry<String,String> entry : User.entries()) {
					
					String key=entry.getKey();
					String Value=entry.getValue();
					
					if(val.equals(Value))
						temp.put(key, Value);
	       
				}
				for (Map.Entry<String,String> entry : temp.entrySet()) {
					
					String key=entry.getKey();
					String Value=entry.getValue();
					
					User.remove(key, Value);
	       
				}
				temp.clear();
				
				
				if(set.contains(val))
					set.remove(val);
				
				
				for (Map.Entry<String,String> entry : Disp.entries()) {
					
					String key=entry.getKey();
					String Value=entry.getValue();
					
					if(val.equals(Value))
						temp.put(key, Value);
	       
				}
				for (Map.Entry<String,String> entry : temp.entrySet()) {
					
					String key=entry.getKey();
					String Value=entry.getValue();
					
					Disp.remove(key, Value);
	       
				}
				temp.clear();
				
				Display_User_Sub_User();
				System.out.println();
		            
				
			}
			else if(x==8) {
				System.out.println("Enter user name : ");
				String user=sc.nextLine();
				String role = null;
				for (Map.Entry<String,String> entry : User.entries()) {
					if(entry.getValue().equals(user)) {
						role=entry.getKey();
						break;
					}
				}
				int q=Root_to_Target(root,role).size()-User.get(role).size();
				System.out.println("Number of users from top : "+ q);
			}
			else if(x==9) {
				
				System.out.println("height - "+get_Height(root));
			}
			else if(x==10) {
				
				System.out.println("Enter user 1 : ");
				String user1=sc.nextLine();
				System.out.println("Enter user 2 : ");
				String user2=sc.nextLine();
				
				String role1=null,role2=null;
				for (Map.Entry<String,String> entry : User.entries()) {
					if(entry.getValue().equals(user1)) {
						role1=entry.getKey();
						break;
					}
				}
				for (Map.Entry<String,String> entry : User.entries()) {
					if(entry.getValue().equals(user2)) {
						role2=entry.getKey();
						break;
					}
				}
				String key=lca(root,role1,role2);
				System.out.println("Top most common boss : "+User.get(key));
				
			}
			else {
				break;
			}
			
		}
		sc.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		

	}

}
