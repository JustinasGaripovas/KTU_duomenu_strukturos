

public class FirstTask {

    public static void main(String[] args) {

        int n = 6;
        Show[] shows = new Show[n];

        Cassette[] cas = new Cassette[2];
        cas[0] = new Cassette(915,180);
        cas[1] = new Cassette(1090,240);

        for (int i=0;i<n;i++)
        {
            shows[i] = new Show("Show name "+i,140+i*30);
            System.out.println(shows[i]);
        }

        double finalSum = calculations(cas,shows);

        System.out.println(finalSum/100 + " Lt");

    }

    static private double calculations(Cassette[] cas, Show[] shows)
    {
        double tempSum = 0;

        for (Show s:shows) {
            tempSum += s.getTime();

            if(tempSum>cas[1].time)
            {
                cas[1].setAmount(cas[1].getAmount()+1);
                tempSum -= cas[1].time;
            }
        }

        if(tempSum != 0)
        {
            cas[0].setAmount(cas[0].getAmount()+1);
        }

        tempSum = 0;

        tempSum += cas[0].getAmount()*cas[0].getPrice();

        tempSum += cas[1].getAmount()*cas[1].getPrice();

        return tempSum;
    }



    public static class Cassette {
        private double time;
        private double price;
        private int amount;


        public Cassette(double p,double t){
            setPrice(p);
            setTime(t);
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getAmount() {
            return amount;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double newPrice) {
            price =  newPrice;
        }

        public double getTime() {
            return time;
        }

        public void setTime(double newTime) {
            time =  newTime;
        }

        @Override
        public String toString() {
            return price + "cnt " + time + " min";
        }
    }
}
