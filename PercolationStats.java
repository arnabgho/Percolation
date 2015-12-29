import java.util.Random;

public class PercolationStats {
   Percolation p; 
   double [] results;
   int N,T;
   public PercolationStats(int N, int T){
       this.N=N;
       this.T=T;
       results=new double [T];
       for(int iter=0;iter<T;iter++){
           p=new Percolation(N);
           int numOpened=0;
           while(!p.percolates()){
               Random rn = new Random();
               int range = N;
               int i =  rn.nextInt(range) + 1;
               int j =  rn.nextInt(range) + 1;
               if(!p.isOpen(i,j)){
                   p.open(i,j);
                   numOpened++;
               }
           }
           //System.out.println("Number of Opened : "+numOpened);
           results[iter]=(1.0*numOpened)/(1.0*N*N);
       }
   }     
   public double mean(){
       double mu=0;
       for(int i=0;i<T;i++)
           mu=mu+results[i];
       mu/=T;
       return mu;
   }                      
   public double stddev(){
       double mu=mean();
       double sigma_sq=0;
       
       for(int i=0;i<T;i++){
           sigma_sq+=(results[i]-mu)*(results[i]-mu);
       }
       sigma_sq/=(T-1);
       double sigma=Math.sqrt(sigma_sq);
       return sigma;
   }                    
   public double confidenceLo(){
       double mu,sigma;
       mu=mean();
       sigma=stddev();
       return mu-(1.96*sigma)/T;
   }              
   public double confidenceHi(){
       double mu,sigma;
       mu=mean();
       sigma=stddev();
       return mu+(1.96*sigma)/T;   
   }              
   public static void main(String[] args){
       /*for (int i = 0; i < args.length; i++) {
        System.out.println("Argument #" + i + " = " + args[i]);
       }*/
       int N=Integer.parseInt(args[0]);
       int T=Integer.parseInt(args[1]);
       PercolationStats ps=new PercolationStats(N,T);
       System.out.println("mean \t = "+ps.mean());
       System.out.println("stddev \t = "+ps.stddev());
       System.out.println("95% confidence interval \t = "+ps.confidenceLo()+","+ps.confidenceHi());
   }  
}