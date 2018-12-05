/** @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2013 09 09
   *
   *  Tai yra demonstracinė ekrano valdymo klasė, 
   *    kurioje demonstruojami įvairūs darbo su ekranu ScreenKTU metodai.
   *  Atkreipkite dėmesį į refresh panaudojimo būtinybę, atnaujinant vaizdą.
   *  IŠBANDYKITE įvairius metodus, modifikuokite juos.
   *  EKSPERIMENTUOKITE su spalvomis ir ekrano formavimo algoritmais.
   *  SURAŠYKITE reikiamus veiksmus neužbaigtiems metodams.
   ****************************************************************************/

import java.awt.Color;
import java.awt.event.MouseEvent;

import studijosKTU.ScreenKTU;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ArraySortSavarankiskas extends ScreenKTU{

    public int cellSize;
    public int screenSize;
    public int speed = 3;

    int[] randomValues;

    Color[] assignedColors;

    @Override
    public void mouseClicked(MouseEvent e) {
        int r = e.getY() / cellH;
        int c = e.getX() / cellW;

        if(e.getButton()==1) {
            new Thread(this::bubbleSort).start();
        }

        System.out.println();
        System.out.println("row= " + r +"  col= " + c ); // pagalbinė info
        refresh();
    }

    public ArraySortSavarankiskas(){
        super(2,600);
        cellSize = 2;
        screenSize = 600;
        randomValues = new int[screenSize];
        assignedColors = new Color[screenSize];
    }

    // šiame metode demonstruojamos ekrano valdymo metodų ypatybės
    void demoĮvadinis(){

        float rDiff = 125f;
        float gDiff = 45f;
        float bDiff = 206f;

        float r = (rDiff / screenSize);
        float g = (gDiff / screenSize);
        float b = (bDiff / screenSize);

        System.out.println(r);
        System.out.println(g);
        System.out.println(b);

        for (int i=0;i<screenSize;i++)
        {
            randomValues[i]=i;
            assignedColors[i] = new Color(Math.round(r*i),Math.round(g*i),Math.round(b*i));
        }

        shuffleArray(randomValues,assignedColors);
        makePillars();
    }

    void drawSwapedPillars() {

       for(int j=0;j<screenSize;j++)
       {
           print(j,indexOne,Color.blue); //BACKGROUND REPAINT
       }

        for(int j=randomValues[indexOne];j<screenSize;j++)
        {
            print(j,indexOne,assignedColors[indexOne]); //BACKGROUND REPAINT
        }

       for(int j=0;j<screenSize;j++)
       {
           print(j,indexTwo,Color.blue); //BACKGROUND REPAINT
       }

        for(int j=randomValues[indexTwo];j<screenSize;j++)
        {
            print(j,indexTwo,assignedColors[indexTwo]); //BACKGROUND REPAINT
        }

        refresh(speed);


    }

    void makePillars()
    {
        for (int i=0;i<screenSize;i++)
        {
            for(int j=0;j<screenSize;j++)
            {
                print(j,i,Color.blue); //BACKGROUND REPAINT
            }
        }

        refresh(speed);

        for (int i=0;i<screenSize;i++)
        {
            for(int j=randomValues[i];j<screenSize;j++)
            {
                print(j,i,assignedColors[i]);
            }
            refresh(speed);
        }
    }

    private int indexOne = 0;
    private int indexTwo = 0;

    void bubbleSort()
    {
        for (int i=0;i<screenSize;i++)
        {
            for (int j=0;j<screenSize;j++)
            {
                if(randomValues[i] < randomValues[j])
                {
                    int temp = randomValues[i];
                    randomValues[i] = randomValues[j];
                    randomValues[j] = temp;

                    Color tempCol = assignedColors[i];
                    assignedColors[i] = assignedColors[j];
                    assignedColors[j] = tempCol;

                    indexOne = i;
                    indexTwo = j;

                    //new Thread(this::drawSwapedPillars).start();
                    drawSwapedPillars();

                }
            }
        }
    }

    static void shuffleArray(int[] ar, Color[] ar2)
    {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;

            Color a2 = ar2[index];
            ar2[index] = ar2[i];
            ar2[i] = a2;
        }
    }


    // išbandykite įvairius demonstracinius metodus
    public static void main(String[] args) {
        ArraySortSavarankiskas d = new ArraySortSavarankiskas();



        new Thread(d::demoĮvadinis).start();      // lygiagretus vykdymas

    }
}