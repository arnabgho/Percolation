
public class Percolation{
    
    boolean [][] map;
    int N;
    UF uf;
    public Percolation(int N){
        this.N=N;
        map=new boolean [N][N];
        uf=new UF(N*N+1);
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j]=false;
            }
        }    
    }
    
    public boolean isValid(int i,int j){
        return ( 0<=i && i<N && 0<=j && j<N );
    }
    
    public void open(int i,int j){
        i--;j--;
        map[i][j]=true;
        if(i==0){
            uf.union(i*N+j,N*N);
        }
        if(isValid(i-1,j)){
            if(map[i-1][j])
                uf.union(i*N+j,(i-1)*N+j);
        }
        if(isValid(i,j-1)){
            if(map[i][j-1])
                uf.union(i*N+j,(i)*N+j-1);
        }
        if(isValid(i+1,j)){
            if(map[i+1][j])
                uf.union(i*N+j,(i+1)*N+j);
        }
        if(isValid(i,j+1)){
            if(map[i][j+1])
                uf.union(i*N+j,(i)*N+j+1);
        }
    }
    
    public boolean isOpen(int i,int j){
        i--;j--;
        return map[i][j];
    }
    public boolean isFull(int i,int j){
        i--;j--;
        return uf.connected(i*N+j,N*N);
    }
    public boolean percolates(){
        int i=N-1;
        for(int j=0;j<N;j++){
            if(uf.connected(i*N+j,N*N))
                return true;
        }
        return false;
    }
    
    //public static void main(String[] args){}
}