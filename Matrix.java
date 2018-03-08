class Matrix {
    public static void mover(int linhas,int colunas, char[][] fim, char[][] busca) {
      
	for (int i = 0;i < linhas ;++i ) 
	    for (int j = 0;j < colunas ;++j) 
		fim[j][i] = busca[j][i];
    }
}
