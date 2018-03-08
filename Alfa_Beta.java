class Alfa_Beta {
  public int nos;
  private int profundidade_maxima;
  private Tabuleiro ult;

  Alfa_Beta(int p){
      ult = null;
      profundidade_maxima = p;
  }

  public Tabuleiro Decisao(Tabuleiro tab_decisao){
    nos = 0;
    Tabuleiro tab_inicial=null;
    
    if(ult != null){
      for (Tabuleiro target : ult.nextMovimento()) {

	  if(target.equals(tab_decisao)){
          tab_inicial = target;
          break;
        }
      }
    }

    else
      tab_inicial = tab_decisao;

    int max = Integer.MIN_VALUE;
    int sitio_altura = Integer.MIN_VALUE;
    Tabuleiro max_no = null;
    
    for(Tabuleiro target : tab_inicial.nextMovimento()){
      int target_max = MIN_VALUE(target, 2, Integer.MIN_VALUE, Integer.MAX_VALUE);

      if (max < target_max || (max == target_max && sitio_altura > target.solucao)) { 
        nos++;
        max = target_max;
        max_no = target;
        sitio_altura = target.solucao;
      }
    }
    
    ult = max_no;
    return max_no;
  }


  private int MAX_VALUE(Tabuleiro tab_inicial,int alt,int alfa, int beta) {

      int max = Integer.MIN_VALUE;
      int sitio_altura = Integer.MIN_VALUE;
      
      if(tab_inicial.vencer(alt) || alt >= profundidade_maxima || tab_inicial.isFull()){
         return tab_inicial.UTILITY();
      }
      
      for(Tabuleiro target : tab_inicial.nextMovimento()){
	  
	  int n = MIN_VALUE(target,alt+1, alfa, beta);
	  nos++;
	  
	  if(max < n || (max == n && sitio_altura > target.solucao)) { 
	      max = n;
	      sitio_altura = target.solucao;
	  }

	  if(max >= beta){
	      return max;
	  }
	  
	  else{
	      alfa=Math.max(alfa,max);
	  }
      }
      
      tab_inicial.solucao = sitio_altura;
      return max;
  }



    private int MIN_VALUE(Tabuleiro tab_inicial, int alt , int alfa, int beta) {

       
	int min = Integer.MAX_VALUE;
	int sitio_altura = Integer.MIN_VALUE;
    
      
	if(tab_inicial.vencer(alt) || alt >= profundidade_maxima || tab_inicial.isFull()){
	    return tab_inicial.UTILITY();
	}
   
	for(Tabuleiro target : tab_inicial.nextMovimento()){
	    nos++;
      
	    int n = MAX_VALUE(target,alt+1, alfa, beta);
	    if(min > n || (min == n && sitio_altura > target.solucao)) { 
		min = n ;
		sitio_altura = target.solucao;
	    }
      
	    if(min <= alfa){
		return min;
	    }
      
	    else{
		beta = Math.min(beta,min);
	    }
	}
    
	tab_inicial.solucao = sitio_altura;
	return min;
    }
}
