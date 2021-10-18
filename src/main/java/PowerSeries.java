import java.util.Random;
public class PowerSeries {
    public PowerSeries() {
    }

    /**
     *
     * @param S: Current Stock price
     * @param K: Strike price
     * @param sigma: Volatility(sigma^2 = variance rate)
     * @param time: Time in days
     * @param interestrate:
     * @param ksi3: Diffusion coefficient for the stochastic process of the variance V
     * @return
     */
    public double calculatePSA(double S, double K, double sigma, double time, double interestrate, double ksi3) {
        // These next 3 lines ease the work when using sigma, T and r
        double sigmap = sigma/100;
        double T = time/360;
        double r = interestrate/100;

        // d1 and d2
        double d1 = (Math.log(S/K) + (r + Math.pow(sigmap, 2)/2) * T) / (sigmap
                * Math.sqrt(T));
        double d2 = d1 - (sigmap * Math.sqrt(T));
        double c,k,x,n,part1,part2,part3,part4,part5,part6,ps = 0;

        // Regular Black Scholes and k = ksi^2(T-t)
        double d = S* new NormalDistribution().getProbability(d1);
        c = Math.max(d - K * Math.exp(-r * T) * new
                NormalDistribution().getProbability(d2),0);
        k = Math.pow(ksi3, 2) * T;
        x = (Math.exp(-d1*d1/2)) / (1/(Math.sqrt(2*Math.PI)));

        // calculating formula (9)
        part1 = 0.5* (S*(double)Math.sqrt(T)*x*(d1*d2- 1)/(4*Math.pow((sigmap),3)));    // 1st term in eq
        part2 = (2*Math.pow(sigmap,4)*(Math.exp(k)-k-1))/(Math.pow(k,2)) - Math.pow(sigmap,4);  // 2nd term in eq
        part3 = (double)(1/6) * S*Math.sqrt(T)*x*((d1*d2-3)*(d1*d2-1)- (Math.pow(d1,2)+Math.pow(d2,2)));
        part4 = 8*Math.pow(sigmap,5);
        part5 = Math.pow(sigmap,6);
        part6 = (Math.exp(3*k)- (9+18*k)*Math.exp(k)+(8+24*k+18*Math.pow(k,2)+6*Math.pow(k,3))) / (3*Math.pow(k,3));
        ps = c + part1*part2 + (part3/part4)*part5*part6;
        ps=Math.max(ps,0);
        return ps;
    }
    public double calculatePSB(double S, double K, double sigma, double time, double interestrate, double ksi3) {
        double sigmap = sigma/100;
        double T = time/360;
        double r = interestrate/100;
        double d1 = (Math.log(S/K) + (r + Math.pow(sigmap, 2)/2) * T) / (sigmap
                * Math.sqrt(T));
        double d2 = d1 - (sigmap * Math.sqrt(T));
        double c,k,x,n,part1,part2,part3,part4,part5,part6,ps = 0;
        double d = S* new NormalDistributionCND().CND(d1);
        c = Math.max(d - K * Math.exp(-r * T) * new
                NormalDistributionCND().CND(d2),0);
        k = Math.pow(ksi3, 2) * T;
        x = (Math.exp(-d1*d1/2)) / (1/(Math.sqrt(2*Math.PI)));
        part1 = 0.5* (S*(double)Math.sqrt(T)*x*(d1*d2-
                1)/(4*Math.pow((sigmap),3)));
        part2 = (2*Math.pow(sigmap,4)*(Math.exp(k)-k-1))/(Math.pow(k,2)) - Math.pow(sigmap,4);
        part3 = (double)(1/6) * S*Math.sqrt(T)*x*((d1*d2-3)*(d1*d2-1)-
                (Math.pow(d1,2)+Math.pow(d2,2)));
        part4 = 8*Math.pow(sigmap,5);
        part5 = Math.pow(sigmap,6);
        part6 = (Math.exp(3*k)-
                (9+18*k)*Math.exp(k)+(8+24*k+18*Math.pow(k,2)+6*Math.pow(k,3))) /
                (3*Math.pow(k,3));
        ps = c + part1*part2 + (part3/part4)*part5*part6;
        ps=Math.max(ps,0);
        return ps;
    }
}
