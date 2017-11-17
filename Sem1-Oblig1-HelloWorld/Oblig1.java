//Manhal  mmalkhay inf1000 oblig 1
public class Oblig1 {
    public static void main(String[] arga) {
    	System.out.println("hello world!");
	int nedbّrmai,nedbّrjuni,nedbّrjuli,sum;
	nedbّrmai =68 ;
	nedbّrjuni =157 ;
	nedbّrjuli =107 ;
	sum = nedbّrmai + nedbّrjuni + nedbّrjuli;
	System.out.println("Totalt nedbّr i sommeren 2011 er: " + sum + " mm.");

	double dagmai,dagjuni,dagjuli , gjennomsnittregn;
	dagmai = 14;
	dagjuni=21;
	dagjuli = 19;
	double totaldag = dagmai+dagjuni+dagjuli;
	gjennomsnittregn = sum/totaldag;
	System.out.printf("Gjennomsnit nedbّr de dagene det regnet i sommeren 2011 er: %.2f mm per dag. \n",gjennomsnittregn);
	double juligjennomsnittregn, blinderngjennomsnitt;
	blinderngjennomsnitt = 81 ;
	juligjennomsnittregn = nedbّrjuli * 100 / blinderngjennomsnitt;
	System.out.printf("Det regnet %.0f prosent av normal nedbّren i juli mهned 2011. \n", juligjennomsnittregn);
	}
}
