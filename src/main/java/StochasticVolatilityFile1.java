import java.util.Random;

public class StochasticVolatilityFile1 {
    double vione;
    double vitwo;
    public Random generator1=new Random();
    public Random generator2=new Random();
    public Random generator3=new Random();
    public StochasticVolatilityFile1() {
    }
    public double simulateVolatility1(double alpha,double ksi, double sigmastar,int numberOfSubintervals,
        int numberOfIterations,double sigma, double timeToMaturity) {
        double deltati=timeToMaturity/(360*numberOfSubintervals);
        double sigmap = sigma/100;
        double vol=0.0;
        double[] vi=new double[numberOfSubintervals+1];
        //here we create an array of doubles representing Vi in each of the
        //time steps (input - intervals1)
        vi[0]=Math.pow(sigmap,2);
        for(int s=1;s<numberOfIterations+1;s++) {
            double sumvi = 0.0;
            sigmap = sigma/100;
            for (int d=1;d<numberOfSubintervals+1;d++){
                // vi[d] is equal to the vol in the last time step
                vi[d] =vi[d-1]*Math.exp((alpha*(sigmastar-sigmap)- (Math.pow(ksi,2)/2))*deltati+ generator1.nextGaussian()*ksi*Math.sqrt(deltati));
                sumvi= sumvi+vi[d];
                sigmap=Math.sqrt(vi[d]);
            }
            vol =sumvi/numberOfSubintervals;
            vione= vione+vol;
        }

        vol=Math.sqrt(vione/numberOfIterations)*100;
        return vol;
    }

    public double simulateVolatility2(double alpha,double ksi, double sigmastar,int numberOfSubintervals,
                                      int numberOfIterations,double sigma, double timeToMaturity){
        double deltati=timeToMaturity/(360*numberOfSubintervals);
        double sigmap = sigma/100;
        double vol=0.0;
        double[] vi=new double[numberOfSubintervals+1];
        vi[0]=Math.pow(sigmap,2);
        for(int s=1;s<numberOfIterations+1;s++){
            double sumvi=0.0;
            sigmap = sigma/100;
            for (int d=1;d<numberOfSubintervals+1;d++){
                vi[d] =vi[d-1]*Math.exp((alpha*(sigmastar-sigmap)-
                        (Math.pow(ksi,2)/2))*deltati-
                        generator2.nextGaussian()*ksi*Math.sqrt(deltati));
                sumvi= sumvi+vi[d];
                sigmap=Math.sqrt(vi[d]) ;
            }
            vol =sumvi/numberOfSubintervals;
            vione= vione+vol;
        }
        vol=Math.sqrt(vione/numberOfIterations)*100;
        return vol;
    }

    public double simulateVolatility3(double alpha,double ksi, double sigmastar,int numberOfSubintervals,
                                      int numberOfIterations,double sigma, double timeToMaturity){
        double deltati=timeToMaturity/(360*numberOfSubintervals);
        double sigmap = sigma/100;
        double vol=0.0;
        double[] vi=new double[numberOfSubintervals+1];
        vi[0]=Math.pow(sigmap,2);
        for (int d=1;d<numberOfSubintervals+1;d++){
            double sim=0.0;
            double sumsim=0.0;
            for(int s=1;s<numberOfIterations+1;s++){
                sim=vi[d-1]*Math.exp((alpha*(sigmastar-sigmap)-
                        (Math.pow(ksi,2)/2))*deltati-
                        generator3.nextGaussian()*ksi*Math.sqrt(deltati));
                sumsim= sumsim+sim;
            }
            vi[d]=sumsim/numberOfIterations;
            sigmap=Math.sqrt(vi[d]);
            vitwo= vitwo+vi[d];
        }
        vol=Math.sqrt(vitwo/numberOfSubintervals)*100;
        return vol;
    }
}



