/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author Lenovo
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import javax.swing.JOptionPane;



public class Project<T extends Comparable <T> > 
{
    private class Node <T>
    {
        T value;
        Node<T> left,right,parent;
        
        public Node(T v)
        {
            value=v;
            left=right=parent=null;
        }
    }
    
    Node<T> root;

    public Project()
    {
        root=null;
    }
    
    
    
    
    
    
    public void insert(T v)
    {
        insert(root,v);
    }    
    private void insert(Node<T> n,T data)
    {
        Node<T> p=null;
        if(n==null){n=new Node(data);
            //System.out.println("Node: "+n.value+" inserted ");
        }
        else{
        while(n!=null){
            p=n;
        if(data.compareTo(p.value)<0)
            n=n.right;
            
        else
            n=n.left;
            }
        n=new Node(data);
        n.parent=p;
        }
         if(p==null)
            root=n;
        else if(data.compareTo(p.value)<0)
            p.right=n;
        else
            p.left=n;
        //System.out.println("Node: "+n.value+" inserted ");
       Splay(n);
    }
    
    
    
    
    
   
    private void LeftasParent(Node<T> ch, Node<T> pr)
     {        
        if (pr.parent != null)
         {
             if (pr == pr.parent.left)
                 pr.parent.left = ch;
             else 
                 pr.parent.right = ch;
         }
         if (ch.right != null)
             ch.right.parent = pr;
 
         ch.parent = pr.parent;
         pr.parent = ch;
         pr.left = ch.right;
         ch.right = pr;
     }
    
    
    
    
    
 
    private void RightasParent(Node<T> ch, Node<T> pr)
     {
          
         if (pr.parent != null)
         { 
             if (pr == pr.parent.left)
                 pr.parent.left = ch;
             else 
                 pr.parent.right = ch;
         }
         if (ch.left != null)
             ch.left.parent = pr;
         ch.parent = pr.parent;
         pr.parent = ch;
         pr.right = ch.left;
         ch.left = pr;
     }
 
     private void Splay(Node<T> x)
     {
         JOptionPane.showMessageDialog(null, "Splaying "+x.value);
         while (x.parent != null)
         {
             Node Parent = x.parent;
             Node GP = Parent.parent;
             if (GP == null)
             {
                 if (x == Parent.left)
                     LeftasParent(x, Parent);
                 else
                     RightasParent(x, Parent);                 
             } 
             else
             {
                 if (x == Parent.left)
                 {
                     if (Parent == GP.left)
                     {
                         LeftasParent(Parent,GP);
                         LeftasParent(x, Parent);
                     }
                     else 
                     {
                         LeftasParent(x, x.parent);
                         RightasParent(x, x.parent);
                     }
                 }
                 else 
                 {
                     if (Parent == GP.left)
                     {
                         RightasParent(x, x.parent);
                         LeftasParent(x, x.parent);
                     } 
                     else 
                     {
                         RightasParent(Parent, GP);
                         RightasParent(x, Parent);
                     }
                 }
             }
         }
         root = x;
         JOptionPane.showMessageDialog(null,x.value+ "Splayed ");
        // return x;
             
}
     
  
 
     private void remove(T v)
     {
         Node<T> node=root;
         if (node == null){
            JOptionPane.showMessageDialog(null,"Tree empty");
             return;
            
         }
         while (node != null)
         {
             if (v .compareTo( node.value)<0)
                 node = node.right;
             else if (v .compareTo( node.value)>0)
                 node = node.left;
             else 
                 break;
         }
         
         if(node==null)JOptionPane.showMessageDialog(null,"Value not found");
         else{
         Splay(node);
         if( (node.left != null) && (node.right !=null))
         { 
             Node<T> min = node.left;
             while(min.right!=null)
                 min = min.right;
 
             min.right = node.right;
             node.right.parent= min;
             node.left.parent = null;
             root = min;
         
             
         }
         else if (node.right != null && node.left==null)
         {
             node.right.parent = null;
             root = node.right;
        } 
         else if( node.left !=null&&node.right==null)
         {
             node.left.parent = null;
             root = node.left;
         }
         else
         {
             root = null;
         }

         node.parent = null;
         node.left = null;
         node.right = null;
         node = null;
         JOptionPane.showMessageDialog(null, "Element Removed");
         }
     }

      public void deletetree(){
          //Node<T> node=root;
          if(root==null)JOptionPane.showMessageDialog(null," Tree already empty..");
          else{
              root=null;
              JOptionPane.showMessageDialog(null," Tree emptied..");
 
                      
          }
      }
   public void BFS() {
       Queue<Node> queue = new LinkedList() ;
    if (root == null){
        JOptionPane.showMessageDialog(null,"Tree Empty..");
        return;
    
        
    }queue.clear();
    queue.add(root);
    String p=new String();
    while(!queue.isEmpty()){
        Node node = queue.remove();
        p+=node.value+" ";
     //   System.out.print(node.value + " ");
        if(node.left != null) queue.add(node.left);
        if(node.right != null) queue.add(node.right);
    }
    JOptionPane.showMessageDialog(null,p); 
       

}

    public static void main(String[] args) {
        Project<String> p=new Project();  
      int  n;
        
      //Scanner dummy=new Scanner(System.in);  
      
       while(true)
       {
           String Choice = JOptionPane.showInputDialog("\nEnter your choice: "+"\na ) Insertion"+"\nb ) Deletion"+"\nc )Traversal"+"\nd ) Random number insertion"+"\n e ) Delete Tree"+"\nf ) Exit");
           String a; 
           
           switch(Choice)
           {
               case "a":
               {
                   a=JOptionPane.showInputDialog("\nEnter the element you want to insert: ");
                   p.insert(a);
                   System.out.println();
                   JOptionPane.showMessageDialog(null, "Element Inserted");
                    p.BFS();
                   break;
               }
               case "b":
               {
                   a=JOptionPane.showInputDialog("\nEnter the element you want to delete: ");
                   p.remove(a);
                   p.BFS();
                   System.out.println();
                   break;
               }
               case "c":
               {
                  p.BFS();
                   System.out.println();
                   break;
               }
               
               
               case "d":
               {
                   String ch;
                   ch=JOptionPane.showInputDialog("\nInput number of elements you want to insert ");
                   if(isNumeric(ch)==false)     JOptionPane.showMessageDialog(null, "Invalid choice.");
                   else{
                   int s= Integer.parseInt(ch);
                   int i=1;
                   String t;
                   while(i<=s){
                       n= (int )(Math.random() * 50 + 1);
                       t=Integer.toString(n);
                       p.insert(t);
                       i++;
                       p.BFS();
                       
                   }
                   System.out.println("");
                   JOptionPane.showMessageDialog(null,"Numbers inserted");}
                           
                   break;
               }
               case "e":
               {
                   p.deletetree();
                   break;
               }  
               case "f":
               {
                   JOptionPane.showMessageDialog(null,"\nExiting!!!!");
                   System.exit(0);
               }
               default:
               {
                   
                   JOptionPane.showMessageDialog(null, "Invalid option. Choose again.");
               }
           }
           
       }    
        
    }
}