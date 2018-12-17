using Gtk;
using Cairo;
using System;

class TryingTOdraw : Window
{
    public static BinaryTree binaryTree;

    const int XSIZE = 700;
    const int YSIZE = 700;

    public TryingTOdraw() : base("Simple drawing")
    {
        SetDefaultSize(XSIZE, YSIZE);
        SetPosition(WindowPosition.Center);
        DeleteEvent += delegate { Application.Quit(); }; ;

        DrawingArea darea = new DrawingArea();
        darea.ExposeEvent += OnExpose;

        Add(darea);

        ShowAll();
    }


    int width, height;
    void drawing(Cairo.Context cr, Node n)
    {
        if (n == null)
            return;

        cr.SetSourceColor(n.color);


        if (n.left != null)
        {
         //   cr.SetSourceColor(n.left.color);

            cr.MoveTo(n.xPos, n.yPos);

            n.left.xPos = n.xPos - n.left.length;
            n.left.yPos = n.yPos - n.left.length;

            cr.LineTo(n.left.xPos, n.left.yPos);

            cr.Stroke();

            drawing(cr, n.left);
        }

        if (n.right != null)
        {
           // cr.SetSourceColor(n.right.color);

            cr.MoveTo(n.xPos, n.yPos);

            n.right.xPos = n.xPos + n.right.length;
            n.right.yPos = n.yPos - n.right.length;

            cr.LineTo(n.right.xPos, n.right.yPos);

            cr.Stroke();

            drawing(cr, n.right);
        }
    }

    void OnExpose(object sender, ExposeEventArgs args)
    {
        DrawingArea area = (DrawingArea)sender;
        Cairo.Context cr = Gdk.CairoHelper.Create(area.GdkWindow);

        cr.LineWidth = 20;
        
        cr.LineCap = LineCap.Round;
        cr.LineJoin = LineJoin.Round;

        width = Allocation.Width;
        height = Allocation.Height;

        cr.MoveTo(width/2, height);
        cr.LineTo((width / 2), height - 50);
        cr.Stroke();

        cr.LineWidth = 9;


        drawing(cr, binaryTree.root);
        
        ((IDisposable)cr).Dispose();
    }

    public static void Main()
    {
        Application.Init();

        binaryTree = new BinaryTree();

        binaryTree.root = new Node(new Color(0.5, 0.5, 0.5));

        binaryTree.root.xPos = XSIZE/2;
        binaryTree.root.yPos = YSIZE-50;

        Random rnd = new Random();

        for (int i = 0; i < 100000;i++)
        {
           //Console.WriteLine(rnd.Next(1,100));
           //Console.WriteLine();
           //Console.WriteLine((float)((float)rnd.Next(1, 100) / 100));

            binaryTree.AddToTree(new Color((float)rnd.Next(1, 100) / 100, (float)rnd.Next(1, 100) / 100, (float)rnd.Next(1, 100) / 100), 15+rnd.Next(0,25));
            //binaryTree.AddToTree(new Color((float)rnd.Next(1, 100) / 100, (float)rnd.Next(1, 100) / 100, (float)rnd.Next(1, 100) / 100), 40);

        }

        new TryingTOdraw();
        Application.Run();
    }
}

internal class Node
{
    public Node parent = null;
    public Node left = null;
    public Node right = null;

    public float xPos = 0;
    public float yPos = 0;

    public Color color = new Color(255, 0, 0);
    public float length = 0;

    public double score = 0;

    public Node() { }

    public Node(Color c)
    {
        color = c;
        score = c.G + c.R + c.B;
    }

    public Node(Color c, float leng)
    {
        color = c;
        score = c.G + c.R + c.B;
        length = leng;
    }
}

internal class BinaryTree
{
    public Node root = new Node();

    private int maxDepth(Node node)
    {
        if (node == null)
            return 0;
        else
        {
            /* compute the depth of each subtree */
        int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);

            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
            else return (rDepth + 1);
        }
    }

    public void AddToTree(Color col)
    {
        Node node = new Node(col);

        root = addRecursive(node, root);
    }

    public void AddToTree(Color col, float f)
    {
        Node node = new Node(col, f);

        root = addRecursive(node, root);
    }

    private Node addRecursive(Node element, Node node)
    {
        if (node == null)
        {
            return element;
        }
        
        if (element.score > node.score)
        {
            node.left = addRecursive(element, node.left);
        }
        else
        {
            node.right = addRecursive(element, node.right);
        }

        return node;
    }

}
