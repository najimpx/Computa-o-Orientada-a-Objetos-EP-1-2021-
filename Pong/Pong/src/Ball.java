import java.awt.*;

/**
	Esta classe representa a bola usada no jogo. A classe princial do jogo (Pong)
	instancia um objeto deste tipo quando a execução é iniciada.
*/

public class Ball {
	
	private double cx;
	private double cy;
	private double width;
    private double height;
    private double speed;
    private Color color;
    double speedx = 0.5;
    double speedy = 0.5;
	/**
		Construtor da classe Ball. Observe que quem invoca o construtor desta classe define a velocidade da bola 
		(em pixels por millisegundo), mas não define a direção deste movimento. A direção do movimento é determinada 
		aleatóriamente pelo construtor.

		@param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
		@param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
		@param width largura do retangulo que representa a bola.
		@param height altura do retangulo que representa a bola.
		@param color cor da bola.
		@param speed velocidade da bola (em pixels por millisegundo).
	*/

	public Ball(double cx, double cy, double width, double height, Color color, double speed){
        this.cx = cx;
        this.cy = cy;
        this.width = width;
        this.height = height;
        this.color = color;
        this.speed = speed;
		
	}


	/**
		Método chamado sempre que a bola precisa ser (re)desenhada.
	*/

	public void draw(){
        GameLib.setColor(this.color);
        GameLib.fillRect(this.cx, this.cy, this.width, this.height);
	}

	/**
		Método chamado quando o estado (posição) da bola precisa ser atualizado.
		
		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/

	public void update(long delta){

        this.cx += this.speed * delta * speedx;
        this.cy += this.speed * delta * speedy;
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com um jogador.
	
		@param playerId uma string cujo conteúdo identifica um dos jogadores.
	*/
	public void resetBall(String wallId) {
		this.cx = 400;
		this.cy = 300;
        this.speed = 0.65;
        speedy = 0;
        if(wallId == "Left") {
        	speedx = 0.5;
        }
        if(wallId == "Right") {
        	speedx = -0.5;
        }
	}
	
	public void onPlayerCollision(String playerId){
		if(playerId == "Player 1") {
			speedx = 1;
		}
		else if(playerId == "Player 2") {
			speedx = -1;
		}
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com uma parede.

		@param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
	*/

	public void onWallCollision(String wallId){
			if(wallId == "Left" || wallId == "Right") {
				resetBall(wallId);
				speedx = -speedx;
			}
			else if(wallId == "Top" || wallId == "Bottom") {
				speedy = -speedy;
			}
	}

	/**
		Método que verifica se houve colisão da bola com uma parede.

		@param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/
	
	public boolean checkCollision(Wall wall){
		String direc = wall.getId();
		if(direc == "Left"  &&  getCx() - this.width/2 < wall.getCx() + wall.getWidth()/2) {
			return true;
		}
		else if(direc == "Right" && (getCx() + this.width/2) > (wall.getCx() - wall.getWidth()/2)) {
			return true;
		}
		else if(direc == "Top" && (getCy() - this.height) <= (wall.getCy() - wall.getHeight()/2)) {
			return true;
		}
		else if(direc == "Bottom" && (getCy() + this.height) >= (wall.getCy() + wall.getHeight()/2)) {
			return true;
		}
		return false;
	}

	/**
		Método que verifica se houve colisão da bola com um jogador.

		@param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/	

	public boolean checkCollision(Player player){
		String direc = player.getId();
		if(direc == "Player 1" ) { 
			if( player.getCx() + player.getWidth()/2 >= getCx() - this.width/2 && player.getCx() - player.getWidth()/2 <= getCx() + this.width/2) {
				if(player.getCy() + player.getHeight()/2 >= getCy() - this.height/2 && player.getCy() - player.getHeight()/2 <= getCy() + this.height/2) {
					//define o angulo da bola ao bater em player
					this.speedy = this.speedy + (this.cy - player.getCy())/100;
					return true;
				}
			}
		}
		else if(direc == "Player 2") {
			if( player.getCx() - player.getWidth()/2 <= getCx() + this.width/2 && player.getCx() + player.getWidth()/2 >= getCx() - this.width/2) {
				if(player.getCy() +  player.getHeight()/2 >= getCy() - this.height/2 && player.getCy() - player.getHeight()/2 <= getCy() + this.height/2) {
					//define o angulo da bola ao bater em player
					this.speedy = this.speedy + (this.cy - player.getCy())/100;
					return true;
				}
			}
		}
		return false;
	}

	/**
		Método que devolve a coordenada x do centro do retângulo que representa a bola.
		@return o valor double da coordenada x.
	*/
	
	public double getCx(){

		return this.cx;
	}

	/**
		Método que devolve a coordenada y do centro do retângulo que representa a bola.
		@return o valor double da coordenada y.
	*/

	public double getCy(){

		return this.cy;
	}

	/**
		Método que devolve a velocidade da bola.
		@return o valor double da velocidade.

	*/

	public double getSpeed(){

		return this.speed;
	}

}
