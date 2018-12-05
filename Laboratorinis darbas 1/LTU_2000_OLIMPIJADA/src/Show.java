public class Show {
    private String name;
    private double time;

    public Show(String n, double t){
        setName(n);
        setTime(t);
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name =  newName;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double newTime) {
        time =  newTime;
    }

    @Override
    public String toString() {
        return name + " " + time + " min";
    }
}
