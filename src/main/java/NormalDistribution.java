public class NormalDistribution {
    public NormalDistribution() {
    }
    //d1= the first argument on BS
    //d2= the first argument on BS
    //create an array of doubles
    //this is a method that finds the cumulative probability
    //for a standard normal variable

    public double getProbability(double u) {
        double[] points = new double[200000];
        double[] points1 = new double[200000];
        double[] area = new double[200000];
        int k = 0;
        int r = 0;
        double t = -100.00;
        points[0] = 0.00;
        points1[0] = 0.00;
        for (k = 1; k < 200000; k++) {
            points[k] = (Math.exp(-Math.pow((t + 0.001 * k), 2) / 2));
            points[k] = points[k] / Math.sqrt(2 * Math.PI);
            area[k] = (Math.abs(points[k] + points[k - 1]) / 2) * 0.001;
            points1[k] = points1[k - 1] + area[k];
        }
        double p=Math.exp(-0)/Math.sqrt(2*Math.PI);
        double distance=Math.abs(-100-u);
        //System.out.println(distance);
        search:
        for ( k=1;k<200000;k++){
            if (Math.abs(-100-u+0.001*k)<distance){
                distance=Math.abs(-100-u+0.001*k);
                //System.out.println(distance);
            }
            else if ( Math.abs(-100-u+0.001*k)>=distance ){
                r=k-1;
                break search;
            }
        }
        return points1[r];
    }
}
