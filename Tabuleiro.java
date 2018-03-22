//alterar o X e O do jogo

import java.util.LinkedList;

class Tabuleiro {

    //Movimentos das diagonais
    
    private static final int[] MoveX_D1D = {1, 2, 3};
    private static final int[] MoveY_D1D = {1, 2, 3};
    private static final int[] MoveX_D1E = {-1, -2, -3};
    private static final int[] MoveY_D1E = {-1, -2, -3};
    
    private static final int[] MoveX_D2D = {1, 2, 3};
    private static final int[] MoveY_D2D = {-1, -2, -3};
    private static final int[] MoveX_D2E  = {-1, -2, -3};
    private static final int[] MoveY_D2E = {1, 2, 3};

    private static final int[] MoveX_HD = {1, 2, 3};
    private static final int[] MoveY_HD = {0, 0, 0};
    private static final int[] MoveX_HE = {-1, -2, -3};
    private static final int[] MoveY_HE = {0, 0, 0};
    
    private static final int[] MoveX_VC = {0, 0, 0};
    private static final int[] MoveY_VC = {1, 2, 3};
    private static final int[] MoveX_VB = {0, 0, 0};
    private static final int[] MoveY_VB = {-1, -2, -3};
 
    public int solucao;

    public static final int colunas = 6;
    public static final int linhas = 7;

    private static final char r = '-';

    public static final char COMPUTER = 'O';
    public static final char PERSON = 'X';

    public char[][] tab;
    private char Utilizador;
    public int valor;
    public int conta;

    public Boolean vencedor;
    public Integer pontos ;
    LinkedList<Tabuleiro> filhos;

    Tabuleiro() { 
	tab = new char[colunas][linhas];
	pontos = null;
	filhos = null;
	vencedor = null;

	for (int i = 0; i < colunas ;++i )
	    for (int j = 0 ; j < linhas ;++j )
		tab[i][j] = r ;

	Utilizador = 'O';
    }

    Tabuleiro(char[][] c, char Utilizador){
	tab = new char[colunas][linhas];
	this.Utilizador = Utilizador;
	Matrix.mover(linhas, colunas, tab, c);
	pontos = null;
	filhos = null;
	vencedor = null;
    }

    public LinkedList<Tabuleiro> nextMovimento(){
	if(filhos != null)
	    return filhos;

	char nextUtilizador;
	Tabuleiro target;
	switch  (Utilizador){
	case 'X':
	    nextUtilizador = 'O';
	    break;

	case 'O':
	    nextUtilizador = 'X';
	    break;
	default:
	    nextUtilizador = '*';
	    System.out.println("Erro: Jogador não encontrado!");
	    System.exit(0);

	}

	LinkedList<Tabuleiro> movimento = new LinkedList<Tabuleiro>();
	for (int i = 0; i < linhas ; i++)
	    movimento.add(nextMovimento(i, nextUtilizador));
	filhos = movimento;
	return movimento;
    }

    public Tabuleiro nextMovimento(int linha, char nextUtilizador){
      
	Tabuleiro target = new Tabuleiro(this.tab, nextUtilizador);
	for (int j = colunas-1; j >= 0; j--) {

	    if(target.tab[j][linha] == r){
		target.tab[j][linha] = target.Utilizador;

		break;
	    }
	}
	return target;
    }
    
    private boolean isValid(int x, int y){
	return (0<=y && y < linhas && 0<=x && x < colunas);
    }

    private int vitorias (int y, int x, int[] movex, int[] movey,int contador){
	for (int i = 0; i < 3 ; i++) {
	  
	    int targetX = (x+movex[i]), targetY = (y+movey[i]);
	    if(isValid(targetY, targetX) && tab[targetY][targetX] == Utilizador){
		contador++;

		if(contador >= 4){

		    if(Utilizador == COMPUTER){
			pontos += 512;
		    }

		    else{
			pontos -= 512;
		    }
		    return contador;
		}
	    }

	    else{
		if (isValid(targetY, targetX) && tab[targetY][targetX] != r) {
		    return 10+contador;
		}
	  
		break;
	    }
	}
	return contador;
    }

    private boolean horizontal (int y, int x){
	int conta;
	boolean verifica = false;
	conta = vitorias (y, x, MoveX_HD, MoveY_HD,1);

	if(conta >= 10){
	    conta -= 10;
	    verifica = true;
	}

	if(conta >= 4)
	    return true;

	conta = vitorias (y, x, MoveX_HE, MoveY_HE, conta);

	if(conta >= 10){
	    if(verifica)
		return false;

	    else
		conta -= 10;
	}


	if(conta >= 4)
	    return true;
      
	if(Utilizador == COMPUTER){
	    valor = 1;
	}

	else {
	    valor = -1;
	}

	switch(conta){

        case 1:
	    pontos += valor;
	    break;

        case 2:
	    pontos += (valor*10);
	    break;

        case 3:
	    pontos += (valor*50);
	    break;

        default:
	    System.out.println("Erro switch dos pontos");
	    System.exit(0);
	    break;
	}

	return false;
    }

    private boolean vertical (int x, int y){
        
	boolean verifica = false;
	conta = vitorias (y, x, MoveX_VC, MoveY_VC,1);

	if(conta >= 10){
	    conta -= 10;
	    verifica = true;
	}

	if(conta>=4)
	    return true;

	conta = vitorias (y, x, MoveX_VB, MoveY_VB, conta);
      
	if(conta >= 10){
	    if(verifica)
		return false;

	    else
		conta -= 10;
	}

	if(conta >= 4)
	    return true;

	if(Utilizador == COMPUTER){
            valor = 1;
	}
	  
	else {
            valor = -1;
	}

	switch(conta){
	case 1:
            valor += valor;
            break;

	case 2:
            pontos += (valor*10);
            break;

	case 3:
            pontos += (valor*50);
            break;
	    
	default:
            System.err.println("Erro switch dos pontos");
            System.exit(0);
            break;
	}


	return false;
    }

    private boolean diagonal1(int y, int x){
  
	boolean verifica = false;
	conta = vitorias (y, x, MoveX_D1D, MoveY_D1D,1);

	if(conta >= 10){
	    conta -= 10;
	    verifica = true;
	}

	if(conta >= 4)
	    return true;

	conta = vitorias (y, x, MoveX_D1E, MoveY_D1E, conta);

	if(conta >= 10){
	    if(verifica)
		return false;

	    else
		conta -= 10;
	}

	if(conta >= 4)
	    return true;

	if(Utilizador == COMPUTER){
            valor = 1;
	}
	else{
            valor = -1;
	}

        switch(conta){

	case 1:
	    pontos += valor;
	    break;

	case 2:
	    pontos += (valor*10);
	    break;

	case 3:
	    pontos += (valor*50);
	    break;

	default:
	    System.out.println("Erro switch dos pontos");
	    System.exit(0);
	    break;
	}


	return false;
    }

    private boolean diagonal2(int y, int x){
	int count;
	int valor;
	boolean verifica = false;
	conta = vitorias (y, x, MoveX_D2D, MoveY_D2D,1);

	if(conta >= 10){
	    conta -= 10;
	    verifica = true;
	}

	if(conta >= 4)
	    return true;
      
	conta = vitorias (y, x, MoveX_D2E, MoveY_D2E, conta);

	if(conta >= 10){
	    if(verifica)
		return false;

	    else
		conta -= 10;
	}

	if(conta >= 4)
	    return true;

	if(Utilizador == COMPUTER){
	    valor = 1;
	}
      
	else{
	    valor = -1;
	}

      
	switch(conta){
        case 1:
	    pontos += valor;
	    break;

        case 2:
	    pontos += (valor*10);
	    break;
 
        case 3:
	    pontos += (valor*50);
	    break;
	
        default:
	    System.out.println("Erro switch dos pontos");
	    System.exit(0);
	    break;
	}

	return false;
    }

    public boolean vencer (int altura){

	if(vencedor != null){
	    return vencedor;
	}
      
	if(Utilizador == COMPUTER)
	    pontos = 16;
      
	else
	    pontos = -16;

	for (int i=0 ; i < linhas ; i++) {
	    for (int j = colunas-1 ; j >= 0 ; j--) {
          
		if(tab[j][i] == r)
		    break;
	  
		if(tab[j][i] == Utilizador){
		    if( horizontal (j,i) || vertical(j,i) || diagonal1(j,i) || diagonal2(j,i)) {
			solucao = altura;
			vencedor = true;
			return true;
		    }
		}
	    }
	}
      
	vencedor = false;
	return false;
    }

    public  Integer UTILITY(){
	return pontos;
    }

    public boolean isFull(){
	for (int j = 0 ; j < linhas ; j++)
	    if(tab[0][j] == r)
		return false;
	return true;
    }

    public boolean equals(Object o){
	Tabuleiro t1 = this;
	Tabuleiro t2 = (Tabuleiro)o;
	for (int i = 0 ; i < colunas ; i++ ) {
	    for (int j = 0 ; j < linhas ; j++ ) {
		if(t1.tab[i][j]!=t2.tab[i][j])
		    return false;
	    }
	}
	return true;
    }

    public String toString(){
	String target = "0 1 2 3 4 5 6 \n";
	for (int i = 0;i < colunas ;++i ) {
	    for (int j = 0 ;j < linhas ;++j ) {
	        target = target + tab[i][j]+" ";
	    }
	    target = target + '\n';
	}
	return target;
    }

}
