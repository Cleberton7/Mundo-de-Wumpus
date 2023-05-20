

public class Agente extends Entidade{
    private int flecha = 1;
    private boolean pegarOuro = false;
    public Agente(int posX,int posY){
        this.nome = "A";
        this.posX = posX;
        this.posY = posY;
        this.pegarOuro = false;
    }
    public void mover(int direcao){
        switch(direcao){
            case 0:
                posX-=1;
            break;
            case 1:
                posX+=1;
            break;
            case 2:
                posY-=1;
            break;
            case 3:
                posY+=1;
            break;
        }
    }
    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }
    public int getPosY() {
        return posY;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }
    public int getFlecha() {
        return flecha;
    }
    public boolean Atirar() {
        
        if(flecha>=1) {
            this.flecha = flecha - 1;
            return true;
        }else{
            return false;
        }
    }
    public boolean isPegarOuro() {
        return pegarOuro;
    }
    public void PegarOuro() {
        this.pegarOuro = true;
    }
    public void setFlecha(int flecha){
        this.flecha = flecha;
    }
}
