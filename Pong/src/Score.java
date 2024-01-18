import java.awt.*;

/**
	Esta classe representa um placar no jogo. A classe princial do jogo (Pong)
	instancia dois objeto deste tipo, cada um responsável por gerenciar a pontuação
	de um player, quando a execução é iniciada.
*/

public class Score {
	private String playerId;
	private int score;
	/**
		Construtor da classe Score.

		@param playerId uma string que identifica o player ao qual este placar está associado.
	*/
	public Score(String playerId){
		this.playerId = playerId;
	}

	/**
		Método de desenho do placar.
	*/
	
	private void drawmiddle() {
		for(int i = 120; i<600; i = i+50) {
			GameLib.drawLine(400.0,i,400.0,i+10);
		}
	}
	
	public void draw(){
		drawmiddle();
		//define cada player diferente
		if(this.playerId == "Player 1") {
			GameLib.setColor(Color.GREEN);
			GameLib.drawText("Player 1", 70, GameLib.ALIGN_LEFT);
			GameLib.setColor(Color.WHITE);

			GameLib.drawText("           "+this.score, 70, GameLib.ALIGN_LEFT);
		}
		if(this.playerId == "Player 2") {
			GameLib.setColor(Color.BLUE);
			GameLib.drawText("Player 2", 70, GameLib.ALIGN_RIGHT);
			GameLib.setColor(Color.WHITE);
			GameLib.drawText("                             "+this.score, 70, GameLib.ALIGN_LEFT);
		}
	}

	/**
		Método que incrementa em 1 unidade a contagem de pontos.
	*/
	public void inc(){
		this.score++;
	}

	/**
		Método que devolve a contagem de pontos mantida pelo placar.

		@return o valor inteiro referente ao total de pontos.
	*/

	public int getScore(){
		return this.score;
	}
}
