import java.util.Scanner;

public class ConnectFour {

  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);

    System.out.println("Que algoritmo pretende usar para jogar?");
    System.out.println("1 - MinMax");
    System.out.println("2 - AlfaBeta");

    int algoritmo = input.nextInt();

    System.out.print("Insira a profundidade máxima com que pretenda que a pesquisa seja efetuada: ");
    int profundidade = input.nextInt();
    
    MiniMax c1 = null;
    Alfa_Beta c2 = null;

    switch (algoritmo){
      case 1:
        c1 = new MiniMax(profundidade);
        break;

      case 2:
        c2 = new Alfa_Beta(profundidade);
        break;

      default:
        System.out.println("Opção não reconhecida.");
        System.exit(0);
    }
    
    Tabuleiro inicial = new Tabuleiro();
    Tabuleiro jogada;
   
    System.out.println(inicial);

    System.out.print("Insira a sua jogada: ");
    jogada = inicial.nextMovimento(input.nextInt(),Tabuleiro.PERSON);

    while(true){

      System.out.println(jogada);
      if(jogada.vencer(0)){
        System.out.println("Parabéns ganhaste :D !!");
        break;
      }
      if(jogada.isFull()){
        System.out.println("Empataram!");
        break;
      }
      
      long startTime = System.currentTimeMillis();

      if(algoritmo == 2)
        jogada = c1.Decisao(jogada);
      
      else
        jogada = c2.Decisao(jogada);
      System.out.println((double)(System.currentTimeMillis() - startTime) / 1000.0 + " segundos.");
      
      if(algoritmo == 2)
        System.out.println(c1.nos+" nos visitados.");
      
      else
        System.out.println(c2.nos+" nos visitados.");

      System.out.println(jogada);

      if(jogada.vencedor){
        System.out.println("O Computador ganhou :(");
        break;
      }

      if(jogada.isFull()){
        System.out.println("Empataram! ");
        break;
      }

      System.out.print("Insira a sua jogada: ");

      jogada = jogada.nextMovimento(input.nextInt(),Tabuleiro.PERSON);
    }
  }
}
