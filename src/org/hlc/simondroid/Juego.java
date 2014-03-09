package org.hlc.simondroid;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Juego extends Activity {
	private Button btnAmarrillo1, btnRojo2, btnAzul3, btnVerde4;
	// Secuencia de botones generador por el juego e introducidos por el
	// jugador:
	private ArrayList<Integer> secuenciaJuego, secuenciaJugador;
	// Puntuacion del jugador:
	private int puntuacion = 0;
	// Reproductores de sonidos:
	MediaPlayer sonidoGenerar, sonidoPulsar;

	// SoundPool soundPool;
	// int sonidoPulsar, sonidoGenerar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.juego);
		// Obtencion de botones:
		btnAmarrillo1 = (Button) findViewById(R.id.btn_amarillo);
		btnRojo2 = (Button) findViewById(R.id.btn_rojo);
		btnAzul3 = (Button) findViewById(R.id.btn_azul);
		btnVerde4 = (Button) findViewById(R.id.btn_verde);
		// Coloreamos los botones:
		pintarBotones();
		// Inicializacion de secuencias:
		secuenciaJuego = new ArrayList<Integer>();
		secuenciaJugador = new ArrayList<Integer>();
		// Inicializacion de sonidos:
		sonidoGenerar = MediaPlayer.create(this, R.raw.generar);
		sonidoPulsar = MediaPlayer.create(this, R.raw.pulsar);
		// soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		// sonidoPulsar = soundPool.load(getBaseContext(), R.raw.pulsar, 0);
		// sonidoGenerar = soundPool.load(getBaseContext(), R.raw.generar, 0);
		// Inicia la partida:
		nuevaRonda();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.juego, menu);
		return true;
	}

	public void nuevaRonda() {
		deshabilitarBotones();
		generarColor();
		habilitarBotones();
	}

	/**
	 * Pinta los botones del color por defecto
	 */
	public void pintarBotones() {
		btnAmarrillo1.setBackgroundColor(Color.rgb(230, 126, 34));
		btnRojo2.setBackgroundColor(Color.rgb(192, 57, 43));
		btnAzul3.setBackgroundColor(Color.rgb(41, 128, 185));
		btnVerde4.setBackgroundColor(Color.rgb(39, 174, 96));
	}

	/**
	 * Ilumina el boton correspondiente a la ID pasada por el parametro.
	 * 
	 * @param boton
	 *            El boton a iluminar
	 */
	public void iluminarBoton(int boton) {
		switch (boton) {
		case 1:
			btnAmarrillo1.setBackgroundColor(Color.rgb(241, 196, 15));
			break;
		case 2:
			btnRojo2.setBackgroundColor(Color.rgb(231, 76, 60));
			break;
		case 3:
			btnAzul3.setBackgroundColor(Color.rgb(52, 152, 219));
			break;
		case 4:
			btnVerde4.setBackgroundColor(Color.rgb(46, 204, 113));
			break;
		default:
			break;
		}
	}

	public void generarColor() {
		int color = 0;
		color = (int) Math.floor(Math.random() * 4 + 1);
		secuenciaJuego.add(color);
		for (int colorSecuencia = 0; colorSecuencia < secuenciaJuego.size(); colorSecuencia++) {
			iluminarBoton(colorSecuencia);
			sonidoGenerar.start();
			// soundPool.play(sonidoGenerar, 1, 1, 1, 0, 1);
			// pintarBotones();
		}
	}

	public void deshabilitarBotones() {
		btnAmarrillo1.setEnabled(false);
		btnRojo2.setEnabled(false);
		btnAzul3.setEnabled(false);
		btnVerde4.setEnabled(false);
	}

	public void habilitarBotones() {
		btnAmarrillo1.setEnabled(true);
		btnRojo2.setEnabled(true);
		btnAzul3.setEnabled(true);
		btnVerde4.setEnabled(true);
	}

	public void compararSecuencias() {
		for (int i = 0; i < secuenciaJugador.size(); i++) {
			if (secuenciaJuego.get(i) != secuenciaJugador.get(i)) {
				Toast.makeText(this, "¡Perdiste! Puntuación: " + puntuacion,
						Toast.LENGTH_LONG).show();
				finish();
			} else if (secuenciaJugador.size() == secuenciaJuego.size()) {
				puntuacion++;
				nuevaRonda();
			}
		}
	}

	/**
	 * Lanzado cuando el usuario pulsa el boton: - Ilumina el boton. - Reproduce
	 * un sonido - Lo agrega la secuencia de botones del jugador. - Pinta de
	 * nuevo los botones del color normal. - Compara ambas secuencias.
	 * 
	 * @param boton
	 *            que se ha pulsado
	 */
	public void pulsarBoton(View boton) {
		int color = 0;
		switch (boton.getId()) {
		case R.id.btn_amarillo:
			color = 1;
			// iluminarBoton(color);
			sonidoPulsar.start();
			// soundPool.play(sonidoPulsar, 1, 1, 1, 0, 1);
			secuenciaJugador.add(color);
			// pintarBotones();
			compararSecuencias();
			break;
		case R.id.btn_rojo:
			color = 2;
			// iluminarBoton(color);
			sonidoPulsar.start();
			// soundPool.play(sonidoPulsar, 1, 1, 1, 0, 1);
			secuenciaJugador.add(color);
			// pintarBotones();
			compararSecuencias();
			break;
		case R.id.btn_azul:
			color = 3;
			// iluminarBoton(color);
			sonidoPulsar.start();
			// soundPool.play(sonidoPulsar, 1, 1, 1, 0, 1);
			secuenciaJugador.add(color);
			// pintarBotones();
			compararSecuencias();
			break;
		case R.id.btn_verde:
			color = 4;
			// iluminarBoton(color);
			sonidoPulsar.start();
			// soundPool.play(sonidoPulsar, 1, 1, 1, 0, 1);
			secuenciaJugador.add(color);
			// pintarBotones();
			compararSecuencias();
			break;
		default:
			break;
		}
	}

}
