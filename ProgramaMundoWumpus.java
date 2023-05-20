
import java.util.Random;
public class ProgramaMundoWumpus {
    private static MundoWumpus mundoWumpus;
    private static String[][] mapaPrincipal;
    private static String[][] ambienteWumpus;
    //private static Mapeamento[][] mapeamento;
    private static int tamanho = 4;
    private static int linha;
    private static int coluna;
    private static Agente agente;
    private static Ouro ouro;
    private static Monstro monstro;
    private static Poco poco;
    public static int matouWumpus = 0;
    public static int ourosColetados = 0;
    public static int jogosGanhos = 0;
    public static int mortesPorWumpos = 0;
    public static int mortePorPoco = 0;
    public static int partidasJogadas = 0;
    public static int jogosPerdidos = 0;
    public static int flechasAtiradas = 0;

    public static void main(String[] args){
        linha = tamanho;
        coluna = tamanho;   
        // for(int i = 0; i < tamanho; i++){
        //     for(int j = 0; j < tamanho; j++){
        //         mapeamento[i][j] = new Mapeamento();
        //     }
        // }
        
        mapaPrincipal = new String[linha][coluna];
        ambienteWumpus = new String[linha][coluna];
     
        while(partidasJogadas < 10){
            mundoWumpus = new MundoWumpus(tamanho);
            mapaPrincipal = mundoWumpus.getMapa();
            ambienteWumpus = mundoWumpus.getAmbiente();
            agente = mundoWumpus.getAgente();
            ouro = mundoWumpus.getOuro();
            monstro = mundoWumpus.getMonstro();
            poco = mundoWumpus.getPoco();
            jogar();
        }
        // mundoWumpus = new MundoWumpus(tamanho);
        // mapaPrincipal = mundoWumpus.getMapa();
        // ambienteWumpus = mundoWumpus.getAmbiente();
        // agente = mundoWumpus.getAgente();
        // ouro = mundoWumpus.getOuro();
        // monstro = mundoWumpus.getMonstro();
        // poco = mundoWumpus.getPoco();
        // mostrarMapa();
        // mostrarMapaSensacao();
    }
    public static void mostrarMapaSensacao() {
        System.out.println("\t\tMapa de Sensações\n");
    
        // Encontra o comprimento da string mais longa na matriz
        int maxCellLength = 0;
        for (String[] row : ambienteWumpus) {
            for (String cell : row) {
                if (cell != null && cell.length() > maxCellLength) {
                    maxCellLength = cell.length();
                }
            }
        }
        
        // Imprime a matriz com tamanho padrão
        String formatString = "[  %-" + (maxCellLength + 2) + "s] ";
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                String cellValue = ambienteWumpus[i][j];
                if (cellValue == null) {
                    cellValue = "null";
                }
                System.out.printf(formatString, cellValue);
            }
            System.out.println();
            System.out.println();
        }
    }
    public static void mostrarMapa() {
        // Encontra o comprimento da string mais longa na matriz
        int maxCellLength = 0;
        for (String[] row : mapaPrincipal) {
            for (String cell : row) {
                if (cell != null && cell.length() > maxCellLength) {
                    maxCellLength = cell.length();
                }
            }
        }
        
        // Imprime a matriz com tamanho padrão
        String formatString = "[  %-" + (maxCellLength + 2) + "s]";
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                String cellValue = mapaPrincipal[i][j];
                if (cellValue == null) {
                    cellValue = "";
                }
                System.out.printf(formatString, cellValue);
            }
            System.out.println();
            System.out.println();
        }
    }
    public static void limparConsole(){
        try{
            String nomeSistema = System.getProperty("os.name");

            if(nomeSistema.contains("Windows")){
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                return;
            }else{
                for (int i = 0; i < 100; i++){
                    System.out.println();
                }
            }
        }catch(Exception e){
            return;
        }
    }
    public static void jogar(){
       
    while(true){
        try{
            Thread.sleep(500);
        }catch(Exception e){
        }
        limparConsole();
        mostrarMapa();
        //mostrarMapaSensacao();
        relatorio();
        int[] numeros;
        int indice;
        Random geradorDeMovimento = new Random();
        if(mundoWumpus.verificarPosicao(agente.posX, agente.posY) == 0){
            numeros = new int[]{1,3};
            indice = geradorDeMovimento.nextInt(numeros.length);
            if(ambienteWumpus[agente.posX][agente.posY].contains(monstro.sensacao)){
                if(agente.getFlecha()>=1){
                    if(agente.Atirar()){
                        contarFlechasAtiradas();
                    }
                    if(numeros[indice]==1){
                        if(movimentarSul(numeros[indice])) break;
                    }else{
                        if(movimentarLeste(numeros[indice])) break;
                    }
                }else{
                    if(moverLivre(numeros[indice])) break;
                }
            }else{
                if(moverLivre(numeros[indice])) break;
            }

        }else if(mundoWumpus.verificarPosicao(agente.posX, agente.posY) == 1){
            if(agente.isPegarOuro()){
                contarJogosGanhos();

                System.out.println("Ganhouuuuuu");
                break;
            }
            numeros = new int[]{0,3};
            indice = geradorDeMovimento.nextInt(numeros.length);
            if(ambienteWumpus[agente.posX][agente.posY].contains(monstro.sensacao)){
                if(agente.getFlecha()>=1){

                    if(agente.Atirar()){
                        contarFlechasAtiradas();
                    }
                    if(numeros[indice]==0){
                        if(movimentarNorte(numeros[indice])) break;
                    }else{
                        if(movimentarLeste(numeros[indice])) break;
                    }

                }else{
                    if(moverLivre(numeros[indice])) break;
                }
            }else{
                if(moverLivre(numeros[indice])) break;
            }
        }
        else if(mundoWumpus.verificarPosicao(agente.posX, agente.posY) == 2){
            numeros = new int[]{0,2};
            indice = geradorDeMovimento.nextInt(numeros.length);
            if(ambienteWumpus[agente.posX][agente.posY].contains(monstro.sensacao)){
                if(agente.getFlecha()>=1){

                    if(agente.Atirar()){
                        contarFlechasAtiradas();
                    }
                    if(numeros[indice]==0){
                        if(movimentarNorte(numeros[indice])) break;
                    }else{
                        if(movimentarOeste(numeros[indice])) break;
                    }
                }else{
                    if(moverLivre(numeros[indice])) break;
                }
            }else{
                if(moverLivre(numeros[indice])) break;
            }
        }else if(mundoWumpus.verificarPosicao(agente.posX, agente.posY) == 3){
            numeros = new int[]{1,2};
           
            indice = geradorDeMovimento.nextInt(numeros.length);
            if(ambienteWumpus[agente.posX][agente.posY].contains(monstro.sensacao)){
                if(agente.getFlecha()>=1){

                    if(agente.Atirar()){
                        contarFlechasAtiradas();
                    }
                    if(numeros[indice]==1){
                        if(movimentarSul(numeros[indice])) break;
                    }else{
                        if(movimentarOeste(numeros[indice])) break;
                    }
                }else{
                    if(moverLivre(numeros[indice]))break;
                }

            }else{
                if(moverLivre(numeros[indice])) break;
            }

        }else if(mundoWumpus.verificarPosicao(agente.posX, agente.posY) == 4){
            numeros = new int[]{0,1,3};
            indice = geradorDeMovimento.nextInt(numeros.length);
            if(ambienteWumpus[agente.posX][agente.posY].contains(monstro.sensacao)){
                if(agente.getFlecha()>=1){
                    if(agente.Atirar()){
                        contarFlechasAtiradas();
                    }
                    if(numeros[indice]==0){
                        if(movimentarNorte(numeros[indice]))break;
                    }else if(numeros[indice]==1){
                        if(movimentarSul(numeros[indice]))break;
                    }else{
                        if(movimentarLeste(numeros[indice]))break;
                    }
                }else{
                    if(moverLivre(numeros[indice]))break;
                }
                
            }else{
                if(moverLivre(numeros[indice]))break;
            }
           
        }else if(mundoWumpus.verificarPosicao(agente.posX, agente.posY) == 5){
            numeros = new int[]{0,1,2};
            indice = geradorDeMovimento.nextInt(numeros.length);
            if(ambienteWumpus[agente.posX][agente.posY].contains(monstro.sensacao)){
                if(agente.getFlecha()>=1){
                    if(agente.Atirar()){
                        contarFlechasAtiradas();
                    }
                    if(numeros[indice]==0){
                        if(movimentarNorte(numeros[indice]))break;
                    }else if(numeros[indice]==1){
                        if(movimentarSul(numeros[indice]))break;
                    }else{
                        if(movimentarOeste(numeros[indice]))break;
                    }
                }else{
                    if(moverLivre(numeros[indice]))break;
                }
                
            }else{
                if(moverLivre(numeros[indice]))break;
            }
        }else if(mundoWumpus.verificarPosicao(agente.posX, agente.posY) == 6){
            numeros = new int[]{1,2,3};
            indice = geradorDeMovimento.nextInt(numeros.length);
            if(ambienteWumpus[agente.posX][agente.posY].contains(monstro.sensacao)){
                if(agente.getFlecha()>=1){
                    if(agente.Atirar()){
                        contarFlechasAtiradas();
                    }
                    if(numeros[indice]==1){
                        if(movimentarSul(numeros[indice]))break;
                    }else if(numeros[indice]==2){
                        if(movimentarOeste(numeros[indice]))break;
                        
                    }else{
                        if(movimentarLeste(numeros[indice]))break;
                    }
                }else{
                    if(moverLivre(numeros[indice]))break;
                }
            }else{
                if(moverLivre(numeros[indice]))break;
            }
        }else if(mundoWumpus.verificarPosicao(agente.posX, agente.posY) == 7){
            numeros = new int[]{0,2,3};
           
            indice = geradorDeMovimento.nextInt(numeros.length);
            if(ambienteWumpus[agente.posX][agente.posY].contains(monstro.sensacao)){

                if(agente.getFlecha()>=1){
                    if(agente.Atirar()){
                        contarFlechasAtiradas();
                    }
                    if(numeros[indice]==0){
                        if(movimentarNorte(numeros[indice]))break;
                    }else if(numeros[indice]==2){
                        if(movimentarOeste(numeros[indice]))break;
                    }else{
                        if(movimentarLeste(numeros[indice]))break;
                    }
                }else{
                    if(moverLivre(numeros[indice]))break;
                }
            }else{
                if(moverLivre(numeros[indice]))break;
            }
        }
        else{
            numeros = new int[]{0,1,2,3};
            indice = geradorDeMovimento.nextInt(numeros.length);
            if(ambienteWumpus[agente.posX][agente.posY].contains(monstro.sensacao)){
                if(agente.getFlecha()>=1){
                    if(agente.Atirar()){
                        contarFlechasAtiradas();
                    }
                    if(numeros[indice]==0){
                        if(movimentarNorte(numeros[indice])) break;
                    }else if(numeros[indice]==1){
                        if(movimentarSul(numeros[indice])) break;
                    }else if(numeros[indice]==3){

                        if(movimentarLeste(numeros[indice])) break;
                    }else{
                        if(movimentarOeste(numeros[indice])) break;
                    }
                }else{
                    if(moverLivre(numeros[indice]))break;
                }
                
            }else{
                if(moverLivre(numeros[indice])) break;
            }
        }
        verificarOuro();
    }  
    contarPartidasJogadas();
}
public static void moverAgente(int posMover){
    mapaPrincipal[agente.posX][agente.posY] = "";
    agente.mover(posMover);
    mapaPrincipal[agente.posX][agente.posY] = agente.nome;
}
public static void contarJogosGanhos(){
    System.out.println("Partida Ganha!");
    jogosGanhos+=1;
}
public static void contarMortesPorWumpus(){
    System.out.println("Morreu para o Monstro");
    mortesPorWumpos+=1;
}
public static void contarMortesPorPoco(){
    System.out.println("Caiu no poço");
    mortePorPoco+=1;
}
public static void contarOurosColetados(){
    System.out.println("Ouro Coletado");
    ourosColetados+=1;
}
public static void contarFlechasAtiradas(){
    System.out.println("atirou");
    flechasAtiradas+=1;
}
public static void contarJodosPerdidos(){
    System.out.println("Game Over");
    jogosPerdidos+=1;
}
public static void contarPartidasJogadas(){
    System.out.println("Partida Nova inicializada");
    partidasJogadas+=1;
}
public static void contarAcertosWumpus(){
    System.out.println("Gritoooo!\nMatou Wumpus");
    matouWumpus+=1;
}
public static boolean movimentarNorte(int posicao){
    boolean morto = false;
    if(mapaPrincipal[agente.posX-1][agente.posY] != null && mapaPrincipal[agente.posX-1][agente.posY].contains(monstro.nome)){
        contarAcertosWumpus();
        limparMonstro();
        moverAgente(posicao);
    }else{
        morto = moverLivre(posicao);
    }
    return morto;
}  
public static boolean movimentarSul(int posicao){
    boolean morto = false;
    if(mapaPrincipal[agente.posX+1][agente.posY] != null && mapaPrincipal[agente.posX+1][agente.posY].contains(monstro.nome)){
        contarAcertosWumpus();
        limparMonstro();
        moverAgente(posicao);
    }else{
        morto = moverLivre(posicao);
    }
    return morto;
}  
public static boolean movimentarOeste(int posicao){
    boolean morto = false;
    if(mapaPrincipal[agente.posX][agente.posY-1] != null && mapaPrincipal[agente.posX][agente.posY-1].contains(monstro.nome)){
        contarAcertosWumpus();
        limparMonstro();
        moverAgente(posicao);
    }else{
        morto = moverLivre(posicao);
    }
    return morto;
    
}  
public static boolean movimentarLeste(int posicao){
    boolean morto = false;
    if(mapaPrincipal[agente.posX][agente.posY+1] != null && mapaPrincipal[agente.posX][agente.posY+1].contains(monstro.nome)){
        contarAcertosWumpus();
        limparMonstro();
        moverAgente(posicao);
    
    }else{
        morto = moverLivre(posicao);
    }
    return morto;
}  
public static boolean moverLivre(int posicao){
    boolean morto = false;
    int posXlimpar = agente.posX; 
    int posYlimpar = agente.posY;

    agente.mover(posicao);
    mapaPrincipal[posXlimpar][posYlimpar] = "";

    if(mapaPrincipal[agente.posX][agente.posY] != null && mapaPrincipal[agente.posX][agente.posY].contains(poco.nome)){
        contarMortesPorPoco();
        contarJodosPerdidos();
        morto = true;
    }
    if(mapaPrincipal[agente.posX][agente.posY] != null && mapaPrincipal[agente.posX][agente.posY].contains(monstro.nome)){
        contarMortesPorWumpus();
        contarJodosPerdidos();
        morto = true;
    }
    mapaPrincipal[agente.posX][agente.posY] = agente.nome;
    return morto;
    }
    public static void limparMonstro(){
        String s;
        for(int i = 0; i < linha; i++){

            for(int j = 0; j < coluna; j++){
                if(mapaPrincipal[i][j] != null && mapaPrincipal[i][j].contains(monstro.nome)){
                    s = ","+monstro.sensacao;
                    if(mapaPrincipal[i][j].contains(s)){
                        mapaPrincipal[i][j] = mapaPrincipal[i][j].replaceAll(s, "");   
                    }
                    s = monstro.sensacao+",";
                    if(mapaPrincipal[i][j].contains(s)){
                        mapaPrincipal[i][j] = mapaPrincipal[i][j].replaceAll(s, "");  
                    }
                    if(mapaPrincipal[i][j].contains(monstro.sensacao)){
                        mapaPrincipal[i][j] = mapaPrincipal[i][j].replaceAll(monstro.sensacao, "");   
                    }        
                }
                if(ambienteWumpus[i][j].contains(monstro.sensacao)){
                    s = ","+monstro.sensacao;
                    if(ambienteWumpus[i][j].contains(s)){
                        ambienteWumpus[i][j] = ambienteWumpus[i][j].replaceAll(s, "");   
                    }
                    s = monstro.sensacao+",";
                
                    if(ambienteWumpus[i][j].contains(s)){
                        ambienteWumpus[i][j] = ambienteWumpus[i][j].replaceAll(s, "");  
                    }
                    if(ambienteWumpus[i][j].contains(monstro.sensacao)){
                        ambienteWumpus[i][j] = ambienteWumpus[i][j].replaceAll(monstro.sensacao, "");   
                    }         
                }
            }
        }
    }
    public static void verificarOuro(){
        if(ambienteWumpus[agente.posX][agente.posY].contains(ouro.sensacao)){
            agente.PegarOuro();
            contarOurosColetados();
            String s = ","+ouro.sensacao;
            if(ambienteWumpus[agente.posX][agente.posY].contains(s)){
                ambienteWumpus[agente.posX][agente.posY] = ambienteWumpus[agente.posX][agente.posY].replaceAll(s, "");   
            }
            s = ouro.sensacao+",";
            if(ambienteWumpus[agente.posX][agente.posY].contains(s)){
                ambienteWumpus[agente.posX][agente.posY] = ambienteWumpus[agente.posX][agente.posY].replaceAll(s, "");  
            }
            if(ambienteWumpus[agente.posX][agente.posY].contains(ouro.sensacao)){
                ambienteWumpus[agente.posX][agente.posY] = ambienteWumpus[agente.posX][agente.posY].replaceAll(ouro.sensacao, "");   
            }                
        }
    }
    public static void relatorio(){
        System.out.println("Flechas usadas: " + flechasAtiradas);
        System.out.println("Wumpus morreu: " + matouWumpus);
        System.out.println("Mortes: " + jogosPerdidos);
        System.out.println("Mortes pelo Wumpus: " + mortesPorWumpos);
        System.out.println("Quedas em poços: " + mortePorPoco);
        System.out.println("Ouro coletado: " + ourosColetados);
        System.out.println("Jogos ganhos: " + jogosGanhos);
        System.out.println("Partidas jogadas: " + partidasJogadas);
    }
}
