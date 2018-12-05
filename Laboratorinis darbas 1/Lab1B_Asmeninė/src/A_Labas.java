    public class A_Labas {

        static void sveikintis(){
            System.out.println("LABAS pasauli ");
        }

        static void sveikintis(String kalba){
            String atsakymas = "Atsiprašau - nesupratau";
            switch(kalba){
                case "LTU": atsakymas = "Labas pasauli"; break;
                case "USA": atsakymas = "Hello WORLD";   break;
                case "SWE": atsakymas = "Hallå världen"; break;
                case "GRE": atsakymas = "Γεια κόσμος";   break;
                case "FRA": atsakymas = "Bonjour tout le monde";break;
                case "RUS": atsakymas = "привет мир";break;
            }
            System.out.println(atsakymas);
        }

        public static void main(String[] args) {
            sveikintis();
            sveikintis("GRE");
            sveikintis("LAT");
            sveikintis("FRA");   // kodėl ne taip, kur klaida?
        }
    }
