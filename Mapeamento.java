
public class Mapeamento {
    private char[] lugaresSeguros;
    private char Norte = 'N';
    private char Sul = 'S';
    private char Leste = 'L';
    private char Oeste = 'O';
    public Mapeamento(){
        this.lugaresSeguros = new char[4];
        for(int i = 0; i < 4; i++){
            lugaresSeguros[i] = ' ';
        }
    }
    
    public void setPosicaoSegura(char posicao){
        if(posicao == 'N' || posicao == 'S' || posicao == 'O' || posicao == 'L'){
            if(posicao == 'N')lugaresSeguros[0] = this.Norte;
            else if(posicao == 'S') lugaresSeguros[1] = Sul;
            else if(posicao == 'O') lugaresSeguros[2] = Oeste;
            else lugaresSeguros[3] = Leste;  
        }else{
            System.err.println("Posição inválida!!");
        }
    }
    public char[] getLugaresSeguros(){
        return this.lugaresSeguros;
    }

}
