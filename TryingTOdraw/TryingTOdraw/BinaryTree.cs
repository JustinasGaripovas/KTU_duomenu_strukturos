using System;
using Gtk;
using Cairo;

namespace TryingTOdraw
{
    public class BinaryTree
    {
        public class Node
        {
            public Node left = null;
            public Node right = null;

            public Color color = new Color(0, 0, 0);
            public float length = 0;

            public float score = 0;
        }

        Node root = new Node();

        private int maxDepth(Node node)  
        { 
           if (node==null)  
               return 0; 
           else 
           { 
               /* compute the depth of each subtree */
                int lDepth = maxDepth(node.left);
                int rDepth = maxDepth(node.right); 
          
               /* use the larger one */
               if (lDepth > rDepth)  
                   return(lDepth+1); 
               else return(rDepth+1); 
           }
        }

        public void AddToTree(Node n)
        {
            isFound = false;
            AddRecursively(root, n);
        }

        private bool isFound = false;
        private void AddRecursively(Node current, Node n)
        {
            if (isFound)
                return;

            if(current == null)
            {
                current = n;
                isFound = true;
            }

            if(current.score < n.score)
            {
                AddRecursively(current.right, n);
            }else{
                AddRecursively(current.left, n);
            }
        }

    }

}
