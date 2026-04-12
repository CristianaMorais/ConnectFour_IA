import java.util.LinkedList;

class Tabuleiro{

  //Horizontal

  private static final int[] MoveX_HOD = {1, 2, 3};
  private static final int[] MoveY_HOD = {0, 0, 0};
  private static final int[] MoveX_HOE = {-1, -2, -3};
  private static final int[] MoveY_HOE = {0, 0, 0};

  //Vertical

  private static final int[] MoveX_VEC = {0, 0, 0};
  private static final int[] MoveY_VEC = {1, 2, 3};
  private static final int[] MoveX_VEB = {0, 0, 0};
  private static final int[] MoveY_VEB = {-1, -2, -3};

  //Diagonais y = x

  private static final int[] MoveX_D1D = {1, 2, 3};
  private static final int[] MoveY_D1D = {1, 2, 3};
  private static final int[] MoveX_D1E = {-1, -2, -3};
  private static final int[] MoveY_D1E = {-1, -2, -3};

  //Diagonais y = -x
  private static final int[] MoveX_D2D = {1, 2, 3};
  private static final int[] MoveY_D2D = {-1, -2, -3};
  private static final int[] MoveX_D2E = {-1, -2, -3};
  private static final int[] MoveY_D2E = {1, 2, 3};


  public int solucao;

  public static final int linhas = 6;
  public static final int colunas = 7;

  private static final char r = '-';

  public static final char COMPUTER = 'X';
  public static final char PERSON = 'O';

  public char[][] tab;
  private char Utilizador;

  public Boolean vencedor;
  public Integer  pontos ;
  LinkedList<Tabuleiro> filhos;

  Tabuleiro(){ 
    tab = new char[linhas][colunas];
    pontos = null;
    filhos = null;
    vencedor = null;

    //Tabuleiro em branco
    for (int i = 0;i < linhas ;++i )
      for (int j = 0 ;j < colunas ;++j )
        tab[i][j] = r;

    Utilizador = 'O';
  }

  Tabuleiro(char[][] m, char Utilizador){
    tab = new char[linhas][colunas];
    this.Utilizador = Utilizador;
    Matrix.mover(colunas,linhas, tab, m);
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
        System.err.println("Erro: Jogador não encontrado!");
        System.exit(0);
    }

    LinkedList<Tabuleiro> Movimento = new LinkedList<Tabuleiro>();
    for (int i = 0;i < colunas ;++i)
      Movimento.add(nextMovimento(i, nextUtilizador));
    filhos = Movimento;
    return Movimento;
  }

  public Tabuleiro nextMovimento(int row, char nextUtilizador){
    Tabuleiro target = new Tabuleiro(this.tab, nextUtilizador);
      for (int j = linhas-1;j>=0; --j) {
        if(target.tab[j][row] == r){
          target.tab[j][row] = target.Utilizador;
          break;
        }
      }
      return target;
    }
    private boolean isValid(int y, int x){
      return (0<=y && y < linhas && 0<=x && x < colunas);
    }

    private int vitorias(int y, int x,int[] movex,int[] movey,int contador){
      for (int i = 0;i < 3 ; ++i) {
        int targetX = (x+movex[i]), targetY = (y+movey[i]);
        if(isValid(targetY,targetX) && tab[targetY][targetX]==Utilizador){
          contador++;
          if(contador>=4){
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
          if (isValid(targetY,targetX) && tab[targetY][targetX]!=r) {
           return 10+contador;
          }
          break;
        }
      }
      return contador;
    }

    private boolean vencerHO(int y, int x){
      int conta;
      boolean verifica = false;
      conta = vitorias(y, x, MoveX_HOD, MoveY_HOD,1);

      if(conta >= 10){
        conta -= 10;
        verifica = true;
      }

      if(conta>=4)
          return true;

      conta = vitorias(y, x, MoveX_HOE, MoveY_HOE, conta);

      if(conta >= 10){
        if(verifica)
          return false;
        else
          conta -= 10;
      }


      if(conta>=4)
          return true;

      int f;
      if(Utilizador == COMPUTER){
        f = 1;
      }
      else{
        f = -1;
      }

      switch(conta){
        case 1:
          pontos += f;
          break;
        case 2:
          pontos += (f*10);
          break;
        case 3:
          pontos += (f*50);
          break;
        default:
          System.err.println("Erro switch do eval");
          System.exit(0);
          break;
      }

      return false;
    }

    private boolean vencerVE(int y, int x){
      int conta;
      boolean verifica = false;
      conta = vitorias(y, x, MoveX_VEC, MoveY_VEC,1);

      if(conta >= 10){
        conta -= 10;
        verifica = true;
      }

      if(conta>=4)
          return true;

      conta = vitorias(y, x, MoveX_VEB, MoveY_VEB, conta);
      if(conta >= 10){
        if(verifica)
        return false;
        else
        conta -= 10;
      }

      if(conta>=4)
          return true;

          int f;
          if(Utilizador == COMPUTER){
            f = 1;
          }
          else{
            f = -1;
          }

          switch(conta){
            case 1:
            pontos += f;
            break;
            case 2:
            pontos += (f*10);
            break;
            case 3:
            pontos += (f*50);
            break;
            default:
            System.err.println("Erro switch do eval");
            System.exit(0);
            break;
          }


      return false;
    }

    private boolean vencerD1(int y, int x){
      int conta;
      boolean verifica = false;
  
      conta = vitorias(y, x, MoveX_D1D, MoveY_D1D,1);

      if(conta >= 10){
        conta -= 10;
        verifica = true;
      }

      if(conta>=4)
          return true;

      conta = vitorias(y, x, MoveX_D1E, MoveY_D1E, conta);

      if(conta >= 10){
        if(verifica)
          return false;
        else
          conta -= 10;
      }

      if(conta>=4)
          return true;

          int f;
          if(Utilizador == COMPUTER){
            f = 1;
          }
          else{
            f = -1;
          }

          switch(conta){
            case 1:
              pontos += f;
              break;
            case 2:
              pontos += (f*10);
              break;
            case 3:
              pontos += (f*50);
              break;
            default:
              System.err.println("Erro switch do eval");
              System.exit(0);
              break;
          }


      return false;
    }

    private boolean vencerD2(int y, int x){
      int conta;
      boolean verifica = false;

      conta = vitorias(y, x, MoveX_D2D, MoveY_D2D,1);

      if(conta >= 10){
        conta -= 10;
        verifica = true;
      }

      if(conta>=4)
      return true;

      conta = vitorias(y, x, MoveX_D2E, MoveY_D2E, conta);

      if(conta >= 10){
        if(verifica)
        return false;
        else
        conta -= 10;
      }


      if(conta>=4)
      return true;

      int f;
      if(Utilizador == COMPUTER){
        f = 1;
      }
      else{
        f = -1;
      }

      switch(conta){
        case 1:
        pontos += f;
        break;
        case 2:
        pontos += (f*10);
        break;
        case 3:
        pontos += (f*50);
        break;
        default:
        System.err.println("Erro switch do eval");
        System.exit(0);
        break;
      }

      return false;
    }

    public boolean vencer(int altura){
      if(vencedor != null){
        return vencedor;
      }
      if(Utilizador == COMPUTER)
        pontos = 16;
      else
        pontos = -16;

      for (int i=0;i < colunas ;++i) {
        for (int j = linhas-1;j >=0 ;--j) {
          if(tab[j][i] == r)
            break;
          if(tab[j][i] == Utilizador){
            if(vencerHO(j,i) || vencerVE(j,i) || vencerD1(j,i) || vencerD2(j,i)){
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

    public Integer UTILITY(){
      return  pontos;
    }

    public boolean isFull(){
      for (int j = 0;j<colunas; ++j)
        if(tab[0][j] == r)
          return false;
      return true;
    }

    public boolean equals(Object o){
      Tabuleiro t1 = this;
      Tabuleiro t2 = (Tabuleiro)o;
      for (int i = 0;i < linhas ;++i ) {
        for (int j = 0 ;j < colunas ;++j ) {
          if(t1.tab[i][j]!=t2.tab[i][j])
            return false;
        }
      }
      return true;
    }

    public String toString(){
      String target = "0 1 2 3 4 5 6\n";
      for (int i = 0;i < linhas ;++i ) {
        for (int j = 0 ;j < colunas ;++j ) {
          target = target + tab[i][j] + " ";
        }
        target = target + '\n';
      }
      return target;
    }
}
