import java.util.*;
public class BlackScholesFormula {
    public BlackScholesFormula() {
    }

    /**
     *
     * @param S: Current Stock Price
     * @param K: Strike Price
     * @param sigma: volatility
     * @param T: time to maturity
     * @param r: risk-free interest rate
     * @return: Call-options price
     */
    public double BlackScholesCallA(double S, double K, double sigma, double T, double r) {
        double d1 = (Math.log(S / K) + ((r / 100) + Math.pow((sigma / 100), 2) / 2) *
                ((double) T / 360)) / ((sigma / 100) * Math.sqrt((double) T / 360));
        double d2 = d1 - ((sigma / 100) * Math.sqrt((double) T / 360));
        double p = 0;
        p = S * new NormalDistribution().getProbability(d1);
        double d = Math.max(p - K * Math.exp(-(r / 100) * (T / 360)) * new NormalDistribution().getProbability(d2), 0);
        return d;
    }

    public double BlackScholesCallB(double S, double K, double sigma, double T, double r) {
        double d1 = (Math.log(S / K) + ((r / 100) + Math.pow((sigma / 100), 2) / 2)
                * ((double) T / 360)) / ((sigma / 100) * Math.sqrt((double) T / 360));
        double d2 = d1 - ((sigma / 100) * Math.sqrt((double) T / 360));
        double p = 0;
        p = S * new NormalDistributionCND().CND(d1);
        double d = Math.max(p - K * Math.exp(-(r / 100) * (T / 360)) * new NormalDistributionCND().CND(d2), 0);
        return d;
    }

    public double BlackScholesPutA(double S, double K, double sigma, double T, double r) {
        double d1 = (Math.log(S / K) + ((r / 100) + Math.pow((sigma / 100), 2) / 2)
                * ((double) T / 360)) / ((sigma / 100) * Math.sqrt((double) T / 360));
        double d2 = d1 - ((sigma / 100) * Math.sqrt((double) T / 360));
        double p = 0;
        double norm1 = new NormalDistribution().getProbability(-d1);
        double norm2 = new NormalDistribution().getProbability(-d2);
        p = -S * norm1;
        p = Math.max((p + K * Math.exp(-(r / 100) * (T / 360)) * norm2), 0);
        return p;
    }

    public double BlackScholesPutB(double S, double K, double sigma, double T, double r) {
        double d1 = (Math.log(S / K) + ((r / 100) + Math.pow((sigma / 100), 2) / 2) * ((double) T / 360)) / ((sigma / 100) * Math.sqrt((double) T / 360));
        double d2 = d1 - ((sigma / 100) * Math.sqrt((double) T / 360));
        double p = 0;
        double norm1 = new NormalDistributionCND().CND(-d1);
        double norm2 = new NormalDistributionCND().CND(-d2);
        p = -S * norm1;
        p = Math.max((p + K * Math.exp(-(r / 100) * (T / 360)) * norm2), 0);
        return p;
    }
}


