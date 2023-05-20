import java.util.Random;

public class MundoWumpus {
    private Poco poco;
    private Ouro ouro;
    private Monstro monstro;
    private Agente agente;
    private int tamanho;
    private int linha;
    private int coluna;
    private String mapa[][];
    private String ambienteMapa[][];
    

    public MundoWumpus( int tamanho){
        this.tamanho = tamanho;
        this.linha = tamanho;
        this.coluna = tamanho;
        mapa = new String[linha][coluna];
        ambienteMapa = new String[linha][coluna];
        prencherMapaNull();
        sortearAmbiente();
        preencherSensacoes();
    }
    public void sortearAmbiente(){
        preencherAgente();
        preencherWumpus();
        preencherOuro();
        prencherTodosOsPocos();
    }
    public void preencherPoco(){

        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                if(i == poco.posX && j == poco.posY){ 
                    if(poco!=null){
                        mapa[i][j] = poco.nome;
                    }
                }
            }
        }

    }
    public void prencherMapaNull(){
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                mapa[i][j] = null;
                ambienteMapa[i][j] = null;
            }
        }
    }
    public void preencherWumpus(){
        Random gerador = new Random();
        int quantidadeWumpos = (int)(((int)((tamanho*tamanho)*0.2))*0.4);
        agente.setFlecha(quantidadeWumpos);
        for(int k = 0; k < quantidadeWumpos ; k++){
            int x = gerador.nextInt(tamanho);
            int y = gerador.nextInt(tamanho);
            while( mapa[x][y] != null ){
                x = gerador.nextInt(tamanho);
                y = gerador.nextInt(tamanho);
            }
            monstro = new Monstro(x, y);
            for (int i = 0; i < linha; i++) {
                for (int j = 0; j < coluna; j++) {
                    if(i == monstro.posX && j == monstro.posY){ 
                        if(monstro!=null){
                            mapa[i][j] = monstro.nome;
                        }
                    }
                }
            }
        }
        
    }
    public void preencherOuro(){
        Random gerador = new Random();
        int x = gerador.nextInt(tamanho);
        int y = gerador.nextInt(tamanho);
        while( mapa[x][y] != null ){
            if(mapa[x][y].equals(monstro.nome)){
                break;
            }else{
                x = gerador.nextInt(tamanho);
                y = gerador.nextInt(tamanho);
            }
        }
        ouro = new Ouro(x, y);
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                if(i == ouro.posX && j == ouro.posY){
                    if(ouro!=null){
                        if(mapa[i][j] != null){
                            mapa[i][j] = mapa[i][j].concat(",").concat(ouro.nome);
                        }else{
                            mapa[i][j] = ouro.nome;
                        } 
                    }
                }
            }
        }
    }
    public void preencherAgente(){
        agente = new Agente(linha-1 , 0);
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                if(i == agente.posX && j == agente.posY){
                    if(agente != null){
                        mapa[i][j] = agente.nome;
                    } 
                }
            }
        }
    }
    public void prencherTodosOsPocos(){
        int quantidadePocos = (int)((tamanho*tamanho)*0.2);
       
        int x,y;
        Random gerador = new Random();
        for(int i = 0; i < quantidadePocos; i++){
            x = gerador.nextInt(tamanho);
            y = gerador.nextInt(tamanho);
            if(i == 0){
                while(mapa[x][y] != null){
                    x = gerador.nextInt(tamanho);
                    y = gerador.nextInt(tamanho);
                }
                poco = new Poco(x, y);
                preencherPoco();
            }else{
                boolean teste ;
                while(true){
                    teste = false;
                    if(mapa[x][y] == null){
                        if((x == agente.posX - 1 && y == agente.posY)  || (x == agente.posX && y == agente.posY + 1)){
                            if((x == agente.posX - 1 && y == agente.posY) ){
                                if(mapa[agente.posX][agente.posY+1] == null || !mapa[agente.posX][agente.posY+1].equals(poco.nome)){
                                    poco = new Poco(x, y);
                                    preencherPoco();
                                    break;
                                }else{
                                    teste = true;
                                }
                            }else{
                                if(mapa[agente.posX-1][agente.posY] == null || !mapa[agente.posX-1][agente.posY].equals(poco.nome)){
                                    poco = new Poco(x, y);
                                    preencherPoco();
                                    break;
                                }else{
                                    teste = true;
                                }
                            }
                        }else if((verificarPosicao(ouro.posX, ouro.posY)==0) || (verificarPosicao(ouro.posX, ouro.posY)==2) ||(verificarPosicao(ouro.posX, ouro.posY)==3)){
                            if(verificarPosicao(ouro.posX, ouro.posY)==0){//canto superior esquerdo
            
                                if(x == ouro.posX+1 && y == ouro.posY){
                                    if(mapa[ouro.posX][ouro.posY+1] == null || !mapa[ouro.posX][ouro.posY+1].equals(poco.nome)){
                                        poco = new Poco(x, y);
                                        preencherPoco();
                                        break;
                                    }else{
                                        teste = true;
                                    }
                                }else if(x == ouro.posX && y == ouro.posY+1) {
                                    if(mapa[ouro.posX+1][ouro.posY] == null || !mapa[ouro.posX+1][ouro.posY].equals(poco.nome)){
                                        poco = new Poco(x, y);
                                        preencherPoco(); 
                                        break;
                                    }else{
                                        teste = true;
                                    }
                                }else{
                                    poco = new Poco(x, y);
                                    preencherPoco();
                                    break;
                                } 
                            }else if(verificarPosicao(ouro.posX, ouro.posY)==2){
                                if(x == ouro.posX && y == ouro.posY-1){
                                    if(mapa[ouro.posX-1][ouro.posY] == null || !mapa[ouro.posX-1][ouro.posY].equals(poco.nome)){
                                        poco = new Poco(x, y);
                                        preencherPoco();
                                        break;
                                    }else{
                                        teste = true;
                                    }
                                }else if(x == ouro.posX-1 && y == ouro.posY){
                                    if(mapa[ouro.posX][ouro.posY-1] == null || !mapa[ouro.posX][ouro.posY-1].equals(poco.nome)){
                                        poco = new Poco(x, y);
                                        preencherPoco();
                                        break;                
                                    }else{
                                        teste = true;
                                    }
                                }else{
                                    poco = new Poco(x, y);
                                    preencherPoco();
                                    break;
                                }
                            }else{
                                if(x == ouro.posX && y == ouro.posY-1){
                                    if(mapa[ouro.posX+1][ouro.posY] == null || !mapa[ouro.posX+1][ouro.posY].equals(poco.nome)){
                                        poco = new Poco(x, y);                                        
                                        preencherPoco();
                                        break;                                      
                                    }else{
                                        teste = true;
                                    }
                                }else if(x == ouro.posX+1 && y == ouro.posY){
                                    if(mapa[ouro.posX][ouro.posY-1] == null || !mapa[ouro.posX][ouro.posY-1].equals(poco.nome)){
                                        poco = new Poco(x, y);
                                        preencherPoco();
                                        break;                                      
                                    }else{
                                        teste = true;
                                    }
                                }else{
                                    poco = new Poco(x, y);
                                    preencherPoco();
                                    break;
                                }
                            }
                        }else{
                            poco = new Poco(x, y);
                            preencherPoco();
                            break; 
                        }
                        if(teste){
                            x = gerador.nextInt(tamanho);
                            y = gerador.nextInt(tamanho);
                        }
                    }else{
                        x = gerador.nextInt(tamanho);
                        y = gerador.nextInt(tamanho);
                    }
                }
            }
        } 
    }
    public int verificarPosicao(int posX, int posY){
        int posAtual = -1;
        if(posX == 0 && posY == 0) posAtual = 0; //canto superior esquerdo       
        else if(posX + 1 >= linha && posY - 1 < 0) posAtual = 1;//canto inferior esquerdo 
        else if(posX + 1 >= linha && posY + 1 >= coluna) posAtual = 2;//canto inferior direito
        else if(posX - 1 < 0 && posY + 1 >= coluna) posAtual = 3;//canto superior direito
        else if(posY - 1 < 0) posAtual = 4; //parede esquerda
        else if(posY + 1 >= coluna) posAtual = 5;//parede direita
        else if(posX - 1 < 0) posAtual = 6; //parede superior
        else if(posX + 1 >= linha) posAtual = 7; //parede inferior
        else posAtual = 8;//centro
        return posAtual;
    }
    public void verificarElemento(int posX, int posY, boolean norte, boolean sul, boolean oeste, boolean leste){
        if(mapa[posX][posY] != null && mapa[posX][posY].contains(ouro.nome)) ambienteMapa[posX][posY] = ouro.sensacao;
        if(mapa[posX][posY] == null || mapa[posX][posY].contains(agente.nome) || mapa[posX][posY].contains(ouro.nome)){
            if(norte) preencherNorte(posX,posY);
            if(sul) preencherSul(posX,posY);;
            if(oeste) preencherOeste(posX,posY);;
            if(leste) preencherLeste(posX,posY);;
        }
    }
    public void preencherNorte(int posX, int posY){
        if(mapa[posX-1][posY] != null){
            //ambienteMapa[posX-1][posY] = mapa[posX-1][posY];
            if(ambienteMapa[posX][posY] == null){
                if(mapa[posX-1][posY].contains(monstro.nome)) ambienteMapa[posX][posY] = monstro.sensacao;
                else if(mapa[posX-1][posY].equals(poco.nome)) ambienteMapa[posX][posY] = poco.sensacao;
            }else{
                if(mapa[posX-1][posY].contains(monstro.nome)) ambienteMapa[posX][posY] = ambienteMapa[posX][posY].concat(",").concat(monstro.sensacao);
                else if(mapa[posX-1][posY].equals(poco.nome) && !ambienteMapa[posX][posY].contains(poco.sensacao)) ambienteMapa[posX][posY] = ambienteMapa[posX][posY].concat(",").concat(poco.sensacao);
            }
        }
    }
    public void preencherSul(int posX, int posY){

        if(mapa[posX+1][posY] != null){
           // ambienteMapa[posX+1][posY] = mapa[posX+1][posY];
            if(ambienteMapa[posX][posY] == null){
                if(mapa[posX+1][posY].contains(monstro.nome)) ambienteMapa[posX][posY] = monstro.sensacao;
                else if(mapa[posX+1][posY].equals(poco.nome)) ambienteMapa[posX][posY] = poco.sensacao;
            }else{
                if(mapa[posX+1][posY].contains(monstro.nome)) ambienteMapa[posX][posY] = ambienteMapa[posX][posY].concat(",").concat(monstro.sensacao);
                else if(mapa[posX+1][posY].equals(poco.nome) && !ambienteMapa[posX][posY].contains(poco.sensacao)) ambienteMapa[posX][posY] = ambienteMapa[posX][posY].concat(",").concat(poco.sensacao);
            }
        }
    }
    public void preencherLeste(int posX, int posY){
        if(mapa[posX][posY+1] != null){
           // ambienteMapa[posX][posY+1] = mapa[posX][posY+1];
            if(ambienteMapa[posX][posY] == null){
                if(mapa[posX][posY+1].contains(monstro.nome)) ambienteMapa[posX][posY] = monstro.sensacao;
                else if(mapa[posX][posY+1].equals(poco.nome)) ambienteMapa[posX][posY] = poco.sensacao;
            }else{
                if(mapa[posX][posY+1].contains(monstro.nome)) ambienteMapa[posX][posY] = ambienteMapa[posX][posY].concat(",").concat(monstro.sensacao);
                else if(mapa[posX][posY+1].equals(poco.nome) && !ambienteMapa[posX][posY].contains(poco.sensacao)) ambienteMapa[posX][posY] = ambienteMapa[posX][posY].concat(",").concat(poco.sensacao);
            } 
        }
    }
    public void preencherOeste(int posX, int posY){
        if(mapa[posX][posY-1] != null){
            //ambienteMapa[posX][posY-1] = mapa[posX][posY-1];
            if(ambienteMapa[posX][posY] == null){
                if(mapa[posX][posY-1].contains(monstro.nome)) ambienteMapa[posX][posY] = monstro.sensacao;
                else if(mapa[posX][posY-1].equals(poco.nome)) ambienteMapa[posX][posY] = poco.sensacao;
            }else{
                if(mapa[posX][posY-1].contains(monstro.nome)) ambienteMapa[posX][posY] = ambienteMapa[posX][posY].concat(",").concat(monstro.sensacao);
                else if(mapa[posX][posY-1].equals(poco.nome) && !ambienteMapa[posX][posY].contains(poco.sensacao)) ambienteMapa[posX][posY] = ambienteMapa[posX][posY].concat(",").concat(poco.sensacao);
            } 
        }
    }
    public void preencherSensacoes(){
        
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                if(verificarPosicao(i,j) == 0)      verificarElemento(j, j, false,true,false,true);//canto superior esquerdo
                else if(verificarPosicao(i,j) == 1) verificarElemento(i, j, true,false,false,true);//canto inferior esquerdo 
                else if(verificarPosicao(i,j) == 2) verificarElemento(i, j, true,false,true,false);//canto inferior direito
                else if(verificarPosicao(i,j) == 3) verificarElemento(i, j, false,true,true,false);//canto superior direito
                else if(verificarPosicao(i,j) == 4) verificarElemento(i, j, true,true,false,true);//parede esquerda
                else if(verificarPosicao(i,j) == 5) verificarElemento(i, j, true,true,true,false);//parede direita
                else if(verificarPosicao(i,j) == 6) verificarElemento(i, j, false,true,true,true); //parede superior
                else if(verificarPosicao(i,j) == 7) verificarElemento(i, j, true,false,true,true);//parede inferior
                else if(verificarPosicao(i,j) == 8) verificarElemento(i, j, true,true,true, true);//centro
            }
        }
        
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
              if(ambienteMapa[i][j] == null && mapa[i][j] == null){
                    ambienteMapa[i][j] = "S";
              }  
            }
        }
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
              if(ambienteMapa[i][j] == null ){
                    ambienteMapa[i][j] = " ";
              }  
            }
        }
        
    }

    
    public String[][] getAmbiente(){
        return ambienteMapa;
    }
    public String[][] getMapa(){
        return mapa;
    }
    public Poco getPoco() {
        return poco;
    }
    public Ouro getOuro() {
        return ouro;
    }
    public Monstro getMonstro() {
        return monstro;
    }
    public Agente getAgente() {
        return agente;
    }
    public int getTamanho() {
        return tamanho;
    }
    public int getLinha() {
        return linha;
    }
    public int getColuna() {
        return coluna;
    }
    
}   
