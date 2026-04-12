class Mini_Max{
    private int profundidade_maxima;
    private Tabuleiro ult; //ultima jogada
    public int nos;

    Mini_Max(int p){
      ult = null;
      profundidade_maxima = p;
    }

    public Tabuleiro Decisao(Tabuleiro tab_adversario){
      Tabuleiro tab_inicial=null;
      if(ult != null){
        for (Tabuleiro target : ult.nextMovimento()) {
          if(target.equals(tab_adversario)){
            tab_inicial = target;
            break;
          }
        }
      }
      else
        tab_inicial = tab_adversario;

      int max = Integer.MIN_VALUE;
      int sitio_altura = Integer.MIN_VALUE;
      Tabuleiro max_node = null;
      for(Tabuleiro target : tab_inicial.nextMovimento()){
        nos++;
        int target_max=MIN_VALUE(target,2);
        if (max<target_max ||(max==target_max && sitio_altura > target.solucao)) { 
          max = target_max;
          max_node = target;
          sitio_altura = target.solucao;
        }
      }
      ult = max_node;
      return max_node;
    }


  private int MAX_VALUE(Tabuleiro tab_inicial,int altura){
      if(tab_inicial.vencer(altura) || altura>=profundidade_maxima || tab_inicial.isFull()){
         return tab_inicial.UTILITY();
      }
      int max = Integer.MIN_VALUE;
      int sitio_altura = Integer.MIN_VALUE;
      for(Tabuleiro target : tab_inicial.nextMovimento()){
        nos++;
        int t = MIN_VALUE(target,altura+1);
        if(max<t || (max==t && sitio_altura > target.solucao)) { 
          max = t;
          sitio_altura = target.solucao;
        }
      }
      tab_inicial.solucao = sitio_altura;
      return max;
    }



  private int MIN_VALUE(Tabuleiro tab_inicial,int altura){
    if(tab_inicial.vencer(altura) || altura>=profundidade_maxima || tab_inicial.isFull()){
       return tab_inicial.UTILITY();
    }
    int min = Integer.MAX_VALUE;
    int sitio_altura = Integer.MIN_VALUE;
    for(Tabuleiro target : tab_inicial.nextMovimento()){
      ++nos;
      int d = MAX_VALUE(target,altura+1);
      if(min>d || (min==d && sitio_altura > target.solucao)) {
        min = d;
        sitio_altura = target.solucao;
      }
    }
    tab_inicial.solucao = sitio_altura;
    return min;
  }
}
