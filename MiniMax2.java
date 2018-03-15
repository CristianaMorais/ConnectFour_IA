class MiniMax2 {

    public int nos;
    private int profundidade_maxima;
    private Tabuleiro ult; //ultimo movimento feito ?????

    MiniMax (int p){
        ult = null;
	profundidade_maxima = p;
    }

    public Tabuleiro Decisao (Tabuleiro tab_decisao){ //erro aqui
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
	int sitio_altura = Integer.MIN_VALUE; // é a profundidade
	Tabuleiro max_no = null;
      
	for(Tabuleiro target : tab_inicial.nextMovimento()){
	    int target_max = MIN_VALUE(target,2);
	    nos++;

	    if (max < target_max ||(max == target_max && sitio_altura > target.solucao)) {
		max = target_max;
		max_no = target;
		sitio_altura = target.solucao;
	    }
	}
      
	ult = max_no;
	return max_no;
    }


    private int MAX_VALUE(Tabuleiro tab_inicial,int altura){
      
	if(tab_inicial.vencer(altu) || altu >= profundidade_maxima || tab_inicial.isFull()){
	    return tab_inicial.UTILITY();
	}
      
	int max = Integer.MIN_VALUE;
	int sitio_altura = Integer.MIN_VALUE;
      
	for(Tabuleiro target : tab_inicial.nextMovimento()){
	    nos++;
	    int a = MIN_VALUE(target,altu+1);
	
	    if(max < a || (max == a && sitio_altura > target.solucao)) {
		max = a;
		sitio_altura = target.solucao;
	    }
	}
      
	tab_inicial.solucao = sitio_altura;
	return max;
    }



    private int MIN_VALUE(Tabuleiro tab_inicial,int altura) {
      
	if(tab_inicial.vencer (altura) || altura >= profundidade_maxima || tab_inicial.isFull()){
	    return tab_inicial.UTILITY();
	}
	int mini = Integer.MAX_VALUE;
	int sitio_altura = Integer.MIN_VALUE;
	for(Tabuleiro target : tab_inicial.nextMovimento()){
	    int a = MAX_VALUE(target,altura+1);
	    nos++;
   
	    if(mini > a || (mini == a && sitio_altura > target.solucao)) {
		mini = a;
		sitio_altura = target.solucao;
	    }
	}

	tab_inicial.solucao = sitio_altura;
	return mini;
    }
}
