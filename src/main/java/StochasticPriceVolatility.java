import java.util.Random;
public class StochasticPriceVolatility {
    public Random generator1 = new Random();
    public Random generator2 = new Random();

    //one constructor
    public StochasticPriceVolatility() {
    }

    double p1=0.0;
    double p1estimation=0.0;
    double p2=0.0;
    double p2estimation=0.0;
    double p3=0.0;
    double p3estimation=0.0;
    double p4=0.0;
    double p4estimation=0.0;
    double q1=0.0;
    double q1estimation=0.0;
    double q2=0.0;
    double q2estimation=0.0;
    double v1=0.0;//
    double v1estimation=0.0;
    public double getP1Call(double alpha, double sigmastar, double ksi,double rho, int numberOfSubintervals,
                            int numberOfIterations, int timeToMaturity, double sigma, double stockprice,
                            double interestrate, double strikeprice){
        double deltati=(double)
                timeToMaturity/(360*numberOfSubintervals);
        double[] vi1=new double[numberOfSubintervals+1];
        vi1[0]=Math.pow(sigma/100,2);
        double[] price1=new double[numberOfSubintervals+1];
        double pone=0.0;
        for (int sim=1;sim<numberOfIterations+1;sim++){
            price1[0]=stockprice;
            double onePathPrice=0;
            double onePathPrice1=0;//
            for (int d=1;d<numberOfSubintervals+1;d++){
                price1[d] =price1[d-1]*Math.exp(((interestrate*0.01)- (vi1[d-1]/2))*deltati+ generator1.nextGaussian()*Math.sqrt(vi1[d-1]*deltati));
                vi1[d]=vi1[d-1]*Math.exp(((alpha*(sigmastar-Math.sqrt(vi1[d-1]))-(Math.pow(ksi,2)/2))*deltati)+
                        rho*generator1.nextGaussian()*ksi*Math.sqrt(deltati)+ Math.sqrt(deltati*(1-rho*rho))*
                                generator2.nextGaussian()*ksi);
                onePathPrice= price1[numberOfSubintervals-1];
                onePathPrice1= vi1[numberOfSubintervals-1];
            }
            p1=Math.exp(-(interestrate/100)*((double)timeToMaturity/360))*
                    Math.max(onePathPrice-strikeprice,0);
            pone=pone +p1;
            v1=v1+onePathPrice1;
        }
        p1estimation =((double) pone/numberOfIterations);
        v1estimation =((double) v1/numberOfIterations);
        return p1estimation;
    }
    public double getP1Put(double alpha, double sigmastar, double ksi, double rho, int numberOfSubintervals,
                           int numberOfIterations, int timeToMaturity, double sigma, double stockprice,
                           double interestrate, double strikeprice) {
        double deltati=(double)
                timeToMaturity/(360*numberOfSubintervals);
        double[] vi1=new double[numberOfSubintervals+1];
        vi1[0]=Math.pow(sigma/100,2);
        double[] price1=new
                double[numberOfSubintervals+1];
        double pone=0.0;
        for (int sim=1;sim<numberOfIterations+1;sim++){
            price1[0]=stockprice;
            double onePathPrice=0;
            for (int d=1;d<numberOfSubintervals+1;d++){
                price1[d] =price1[d-
                        1]*Math.exp(((interestrate*0.01)- (vi1[d-1]/2))*deltati+
                        generator1.nextGaussian()*Math.sqrt(vi1[d-1]*deltati));
                vi1[d] =vi1[d-
                        1]*Math.exp(((alpha*(sigmastar-Math.sqrt(vi1[d-1]))-
                        (Math.pow(ksi,2)/2))*deltati)+
                        rho*generator1.nextGaussian()*ksi*Math.sqrt(deltati)+
                        Math.sqrt(deltati*(1-rho*rho))* generator2.nextGaussian()*ksi);
                onePathPrice=
                        price1[numberOfSubintervals-1];
            }
            p1=Math.exp(-(interestrate/100)*((double)timeToMaturity/360))*
                    Math.max(strikeprice-onePathPrice,0);
            pone=pone +p1;
        }
        p1estimation =((double) pone/numberOfIterations);
        return p1estimation;
    }

    public double getP2Call(double alpha, double sigmastar, double ksi, double rho, int numberOfSubintervals,
                            int numberOfIterations, int timeToMaturity, double sigma, double stockprice,
                            double interestrate, double strikeprice){
        double deltati=(double) timeToMaturity/(360*numberOfSubintervals);
        double[] vi2=new double[numberOfSubintervals+1];
        vi2[0]=Math.pow(sigma/100,2);
        double[] price2=new double[numberOfSubintervals+1];
        double ptwo=0.0;
        for (int sim=1;sim<numberOfIterations+1;sim++){
            price2[0]=stockprice;
            double onePathPrice=0;
            for (int d=1;d<numberOfSubintervals+1;d++){
                price2[d] =price2[d-1]*Math.exp(((interestrate*0.01)- (vi2[d-1]/2))*deltati-
                        generator1.nextGaussian()*Math.sqrt(vi2[d-1]*deltati));
                vi2[d]=vi2[d-1]*Math.exp(((alpha*(sigmastar-Math.sqrt(vi2[d-1]))- (Math.pow(ksi,2)/2))*deltati)-
                        rho*generator1.nextGaussian()*ksi*Math.sqrt(deltati)+
                        Math.sqrt(deltati*(1-rho*rho))* generator2.nextGaussian()*ksi);
                onePathPrice= price2[numberOfSubintervals-1];
            }
            p2=Math.exp(-(interestrate/100)*((double)timeToMaturity/360))*
                    Math.max(onePathPrice-strikeprice,0);
            ptwo=ptwo +p2;
        }
        p2estimation =((double) ptwo/numberOfIterations);
        return p2estimation;
    }
    public double getP2Put(double alpha, double sigmastar, double ksi, double rho, int numberOfSubintervals,
                           int numberOfIterations, int timeToMaturity, double sigma, double stockprice,
                           double interestrate, double strikeprice) {
        double deltati = (double) timeToMaturity / (360 * numberOfSubintervals);
        double[] vi2 = new double[numberOfSubintervals + 1];
        vi2[0] = Math.pow(sigma / 100, 2);
        double[] price2 = new double[numberOfSubintervals + 1];
        double ptwo = 0.0;
        for (int sim = 1; sim < numberOfIterations + 1; sim++) {
            price2[0] = stockprice;
            price2[0] = stockprice;
            double onePathPrice = 0;
            for (int d = 1; d < numberOfSubintervals + 1; d++) {
                price2[d] = price2[d - 1] * Math.exp(((interestrate * 0.01) - (vi2[d -
                        1] / 2)) * deltati -
                        generator1.nextGaussian() * Math.sqrt(vi2[d - 1] * deltati));
                vi2[d] = vi2[d - 1] * Math.exp(((alpha * (sigmastar - Math.sqrt(vi2[d - 1])) - (Math.pow(ksi, 2) / 2)) * deltati) -
                        rho * generator1.nextGaussian() * ksi * Math.sqrt(deltati) +
                        Math.sqrt(deltati * (1 - rho * rho)) * generator2.nextGaussian() * ksi);
                onePathPrice = price2[numberOfSubintervals - 1];
            }
            p2 = Math.exp(-(interestrate / 100) * ((double) timeToMaturity / 360)) *
                    Math.max(strikeprice - onePathPrice, 0);
            ptwo = ptwo + p2;
        }
        p2estimation = ((double) ptwo / numberOfIterations);
        return p2estimation;
    }
    public double getP3Call(double alpha, double sigmastar, double ksi, double rho, int numberOfSubintervals,
                            int numberOfIterations, int timeToMaturity, double sigma, double stockprice,
                            double interestrate, double strikeprice){
        double deltati=(double) timeToMaturity/(360*numberOfSubintervals);
        double[] vi3 = new double[numberOfSubintervals+1];
        vi3[0] = Math.pow(sigma/100,2);
        double[] price3=new double[numberOfSubintervals+1];
        double pthree=0.0;
        for (int sim=1;sim<numberOfIterations+1;sim++){
            price3[0]=stockprice;
            double onePathPrice=0;
            for (int d=1;d<numberOfSubintervals+1;d++){
                price3[d] =price3[d-1]*Math.exp(((interestrate*0.01)-
                        (vi3[d-1]/2))*deltati+
                        generator1.nextGaussian()*Math.sqrt(vi3[d-1]*deltati));
                vi3[d]=vi3[d-1]*Math.exp(((alpha*(sigmastar-Math.sqrt(vi3[d-1]))-
                        (Math.pow(ksi,2)/2))*deltati)+
                        rho*generator1.nextGaussian()*ksi*Math.sqrt(deltati)+
                        Math.sqrt(deltati*(1-rho*rho))*
                                (-generator2.nextGaussian())*ksi);
                onePathPrice= price3[numberOfSubintervals-1];
            }
            p3=Math.exp(-(interestrate/100)*((double)timeToMaturity/360))*
                    Math.max(onePathPrice-strikeprice,0);
            pthree=pthree +p3;
        }
        p3estimation =((double) pthree/numberOfIterations);
        return p3estimation;
    }
    public double getP3Put(double alpha, double sigmastar, double ksi, double rho, int numberOfSubintervals,
                           int numberOfIterations, int timeToMaturity, double sigma, double stockprice,
                           double interestrate, double strikeprice){
        double deltati=(double) timeToMaturity/(360*numberOfSubintervals);
        double[] vi3 = new double[numberOfSubintervals+1];
        vi3[0] = Math.pow(sigma/100,2);
        double[] price3=new double[numberOfSubintervals+1];
        double pthree=0.0;
        for (int sim=1;sim<numberOfIterations+1;sim++){
            price3[0]=stockprice;
            double onePathPrice=0;
            for (int d=1;d<numberOfSubintervals+1;d++){
                price3[d] =price3[d-1]*Math.exp(((interestrate*0.01)- (vi3[d-1]/2))*deltati+
                        generator1.nextGaussian()*Math.sqrt(vi3[d-1]*deltati));
                vi3[d]=vi3[d-1]*Math.exp(((alpha*(sigmastar-Math.sqrt(vi3[d-1]))- (Math.pow(ksi,2)/2))*deltati)+
                        rho*generator1.nextGaussian()*ksi*Math.sqrt(deltati)+
                        Math.sqrt(deltati*(1-rho*rho))* (-generator2.nextGaussian())*ksi);
                onePathPrice= price3[numberOfSubintervals-1];
            }
            p3=Math.exp(-(interestrate/100)*((double)timeToMaturity/360))*
                    Math.max(strikeprice-onePathPrice,0);
            pthree=pthree +p3;
        }
        p3estimation =((double) pthree/numberOfIterations);
        return p3estimation;
    }
    public double getP4Call(double alpha, double sigmastar, double ksi, double rho, int numberOfSubintervals,
                            int numberOfIterations, int timeToMaturity, double sigma, double stockprice,
                            double interestrate, double strikeprice){
        double deltati=(double) timeToMaturity/(360*numberOfSubintervals);
        double[] vi4=new double[numberOfSubintervals+1];
        vi4[0]=Math.pow(sigma/100,2);
        double[] price4=new double[numberOfSubintervals+1];
        double pfour=0.0;
        for (int sim=1;sim<numberOfIterations+1;sim++) {
            price4[0]=stockprice;
            double onePathPrice=0;
            for (int d=1;d<numberOfSubintervals+1;d++){
                price4[d] =price4[d-1]*Math.exp(((interestrate*0.01)- (vi4[d-1]/2))*deltati-
                        generator1.nextGaussian()*Math.sqrt(vi4[d-1]*deltati));
                vi4[d]=vi4[d-1]*Math.exp(((alpha*(sigmastar-Math.sqrt(vi4[d-1]))-
                        (Math.pow(ksi,2)/2))*deltati)- rho*generator1.nextGaussian()*ksi*Math.sqrt(deltati)+
                        Math.sqrt(deltati*(1-rho*rho))*(- generator2.nextGaussian())*ksi);
                onePathPrice= price4[numberOfSubintervals-1];
            }
            p4=Math.exp(-(interestrate/100)*((double)timeToMaturity/360))*
                    Math.max(onePathPrice-strikeprice,0);
            pfour=pfour +p4;
        }
        p4estimation =((double) pfour/numberOfIterations);
        return p4estimation;
    }
    public double getP4Put(double alpha, double sigmastar, double ksi, double rho, int numberOfSubintervals,
                           int numberOfIterations, int timeToMaturity, double sigma, double stockprice,
                           double interestrate, double strikeprice){
        double deltati=(double) timeToMaturity/(360*numberOfSubintervals);
        double[] vi4=new double[numberOfSubintervals+1];
        vi4[0]=Math.pow(sigma/100,2);
        double[] price4=new double[numberOfSubintervals+1];
        double pfour=0.0;
        for (int sim=1;sim<numberOfIterations+1;sim++){
            price4[0]=stockprice;
            double onePathPrice=0;
            for (int d=1;d<numberOfSubintervals+1;d++){
                price4[d] =price4[d-1]*Math.exp(((interestrate*0.01)- (vi4[d-1]/2))*deltati-
                        generator1.nextGaussian()*Math.sqrt(vi4[d-1]*deltati));
                vi4[d] =vi4[d-1]*Math.exp(((alpha*(sigmastar-Math.sqrt(vi4[d-1]))- (Math.pow(ksi,2)/2))*deltati)-
                        rho*generator1.nextGaussian()*ksi*Math.sqrt(deltati)+
                        Math.sqrt(deltati*(1-rho*rho))*(- generator2.nextGaussian())*ksi);
                onePathPrice= price4[numberOfSubintervals-1];
            }
            p4=Math.exp(-(interestrate/100)*((double)timeToMaturity/360))* Math.max(strikeprice-onePathPrice,0);
            pfour=pfour +p4;
        }
        p4estimation =((double) pfour/numberOfIterations);
        return p4estimation;
    }

    public double getQ1Call( int numberOfSubintervals, int numberOfIterations, int timeToMaturity, double sigma,
            double stockprice, double interestrate, double strikeprice){
        double deltati=(double) timeToMaturity/(360*numberOfSubintervals);
        double vol=Math.pow(sigma/100,2);
        double[] priceq1=new double[numberOfSubintervals+1];
        double qone=0.0;
        for (int sim=1;sim<numberOfIterations+1;sim++){
            priceq1[0]=stockprice;
            double onePathPrice=0;
            for (int d=1;d<numberOfSubintervals+1;d++){
                priceq1[d] =priceq1[d-1]*Math.exp(((interestrate*0.01)- (vol/2))*deltati+
                        generator1.nextGaussian()*Math.sqrt(vol*deltati));
                onePathPrice= priceq1[numberOfSubintervals-1];
            }
            q1=Math.exp(-(interestrate/100)*((double)timeToMaturity/360))* Math.max(onePathPrice-strikeprice,0);
            qone=qone +q1;
        }
        q1estimation =((double) qone/numberOfIterations);
        return q1estimation;
    }
    public double getQ1Put(int numberOfSubintervals, int numberOfIterations, int timeToMaturity, double sigma,
            double stockprice, double interestrate, double strikeprice){
        double deltati=(double) timeToMaturity/(360*numberOfSubintervals);
        double vol=Math.pow(sigma/100,2);
        double[] priceq1=new double[numberOfSubintervals+1];
        double qone=0.0;
        for (int sim=1;sim<numberOfIterations+1;sim++){
            priceq1[0]=stockprice;
            double onePathPrice=0;
            for (int d=1;d<numberOfSubintervals+1;d++){
                priceq1[d] =priceq1[d-1]*Math.exp(((interestrate*0.01)- (vol/2))*deltati+
                        generator1.nextGaussian()*Math.sqrt(vol*deltati));
                onePathPrice= priceq1[numberOfSubintervals-1];
            }
            q1=Math.exp(-(interestrate/100)*((double)timeToMaturity/360))*
                    Math.max(strikeprice-onePathPrice,0);
            qone=qone +q1;
        }
        q1estimation =((double) qone/numberOfIterations);
        return q1estimation;
    }
    public double getQ2Call(int numberOfSubintervals, int numberOfIterations, int timeToMaturity,
            double sigma, double stockprice, double interestrate, double strikeprice){
        double deltati=(double) timeToMaturity/(360*numberOfSubintervals);
        double vol=Math.pow(sigma/100,2);
        double[] priceq2=new double[numberOfSubintervals+1];
        double qtwo=0.0;
        for (int sim=1;sim<numberOfIterations+1;sim++){
            priceq2[0]=stockprice;
            double onePathPrice=0;
            for (int d=1;d<numberOfSubintervals+1;d++){
                priceq2[d] =priceq2[d-
                        1]*Math.exp(((interestrate*0.01)- (vol/2))*deltati-
                        generator1.nextGaussian()*Math.sqrt(vol*deltati));
                onePathPrice= priceq2[numberOfSubintervals-1];
            }
            q2=Math.exp(-(interestrate/100)*((double)timeToMaturity/360))*
                    Math.max(onePathPrice-strikeprice,0);
            qtwo=qtwo +q2;
        }
        q2estimation =((double) qtwo/numberOfIterations);
        return
                q2estimation;
    }
    public double getQ2Put(int numberOfSubintervals, int numberOfIterations, int timeToMaturity,
            double sigma, double stockprice, double interestrate, double strikeprice){
        double deltati=(double) timeToMaturity/(360*numberOfSubintervals);
        double vol=Math.pow(sigma/100,2);
        double[] priceq2=new double[numberOfSubintervals+1];
        double qtwo=0.0;
        for (int sim=1;sim<numberOfIterations+1;sim++){
            priceq2[0]=stockprice;
            double onePathPrice=0;
            for (int d=1;d<numberOfSubintervals+1;d++){
                priceq2[d]=priceq2[d-1]*Math.exp(((interestrate*0.01)- (vol/2))*deltati-
                        generator1.nextGaussian()*Math.sqrt(vol*deltati));
                onePathPrice= priceq2[numberOfSubintervals-1];
            }
            q2=Math.exp(-(interestrate/100)*((double)timeToMaturity/360))* Math.max(strikeprice-onePathPrice,0);
            qtwo=qtwo +q2;
        }
        q2estimation =((double) qtwo/numberOfIterations);
        return q2estimation;
    }


}












