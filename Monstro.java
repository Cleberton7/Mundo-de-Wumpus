public class Monstro extends Entidade{
    private Boolean grito = false;

    public Monstro(int posX, int posY){
        this.nome = "W";
        this.sensacao = "F";
        this.posX = posX;
        this.posY = posY;
    }
    public Boolean getGrito() {
        return grito;
    }
    public void setGrito() {
        this.grito = true;
    }
    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }

    
}
