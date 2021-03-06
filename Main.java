import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//PIANO
class Piano extends NotesSoundBit {
	public double get(char c, double t) {
		return 1*sns(t) + 0.2714*sns(2*t) + 0.2035*sns(3*t) + 0.1255*sns(4*t) + 0.0950*sns(5*t) + 0.0780*sns(6*t) + 0.1085*sns(7*t) + 0.0780*sns(8*t) + 0.0678*sns(9*t) + 0.0475*sns(10*t);
	}

	public String getName() {
		return "Piano";
	}
}

//FLUTE DE PAN
class PanFlute extends NotesSoundBit {
	public double get(char c, double t) {
		return  (0.6*sns(t)+0.3*sns(3*t)+0.1*sns(5*t));
	}

	public String getName() {
		return "Flute de pan";
	}
}

//VOIX
class Voice extends NotesSoundBit {
	public double get(char c, double t) {
		return 0.1 * sin(t) + 1 * sin(2*t) + 0.2*sin(3*t) + 0.4 * sin(4*t) + 0.3 * sin(5*t) + 0.1*sin(6*t);
	}

	public String getName() {
		return "Voix";
	}
}

class SynthKeyboard extends JPanel {
	private Integer octaveToDisplay = 3;

	public void paintComponent(Graphics g){
		g.setColor(new Color(157, 101, 45));
		g.fillRect(0, 0, 2000, 1500);

		int xOffset = 20, yOffset = 20;
		//Draw the 7 white keys
		g.setColor(Color.WHITE);
		for(int i = 0; i<=6; i++) {
			g.fillRect(xOffset + i * 50, yOffset, 50, 200);
		}

		//Draw a black border to the white keys
		g.setColor(Color.BLACK);
		for(int i = 0; i<=6; i++) {
			g.drawRect(xOffset + i * 50, yOffset, 50, 200);
		}

		//Draw the black keys
		//First step : the first group
		g.setColor(Color.BLACK);
		for(int i = 0; i<2; i++) {
			g.fillRect( (xOffset + 40) + i * 50, yOffset, 20, 150);
		}
		//Second step : the second group
		for(int i = 0; i<3; i++) {
			g.fillRect((xOffset  + 40) + (i + 3) * 50, yOffset, 20, 150);
		}

		//Draw the legend
		g.setColor(Color.WHITE);
		g.drawString("1. Piano", xOffset, yOffset + 250);
		g.drawString("2. Voix", xOffset, yOffset + 270);
		g.drawString("3. Flute de pan", xOffset, yOffset + 290);
		g.drawString("+ : Augmentation d'odctave", xOffset + 200, yOffset + 250);
		g.drawString("- : Diminution d'odctave", xOffset +200, yOffset + 270);

		g.drawString("Octave : " + octaveToDisplay.toString(), xOffset + 200, yOffset + 290);
	}

	public void setOctave(Integer pOctave) {
		this.octaveToDisplay = pOctave;
	}
}

class MainWindow extends JFrame implements KeyListener {
	private NotesSoundBit instrument;
	private SoundBit orchestre;
	private String note;
	private Integer octave;
	private SynthKeyboard pan = new SynthKeyboard();

	public MainWindow(String name) {
		this.note = "";
		this.octave = 3;

		this.setTitle(name);
		this.setSize(410, 370);
		this.setContentPane(pan);
		addKeyListener (this);
		this.instrument  = new Piano();

		this.orchestre = new SoundBit(){
			public double get(char c, double t) {
				return 0.7 * instrument.sound.get(c,t);
			}
		};

		this.setVisible(true);
	}

	public void keyPressed(KeyEvent evt){
	}

	public void keyReleased(KeyEvent evt){
	}

	public void keyTyped(KeyEvent evt) {
		String note = "";
		Boolean resetOctave = false;

		switch(evt.getKeyChar())
		{
			//C note (Do)
			case 'q':
				System.out.print("Do");
				note = "C";
				break;

			//C# note (Do#)
			case 'z':
				System.out.print("Do#");
				note = "C#";
				break;

			//D note (Ré)
			case 's':
				System.out.print("Ré");
				note = "D";
				break;

			//D# note (Ré#)
			case 'e':
				System.out.print("Ré#");
				break;

			//E note (Mi)
			case 'd':
				System.out.print("Mi");
				note = "E";
				break;

			//F note (Fa)
			case 'f':
				System.out.print("Fa");
				note = "F";
				break;

			//F# note (Fa#)
			case 't':
				System.out.print("Fa#");
				break;

			//G note (Sol)
			case 'g':
				System.out.print("Sol");
				note = "G";
				break;

			//G# note (Sol#)
			case 'y':
				System.out.print("Sol#");
				break;

			//A note (La)
			case 'h':
				System.out.print("La");
				note = "A";
				break;

			//A# note (La#)
			case 'u':
				System.out.print("La#");
				break;

			//B note (Si)
			case 'j':
				System.out.print("Si");
				note = "B";
				break;

			//C note (Do)
			case 'k':
				System.out.print("Do");
				note = "C";
				octave++;
				resetOctave = true;
				break;

			//D note (Ré)
			case 'l':
				System.out.print("Ré");
				note = "D";
				octave++;
				resetOctave = true;
				break;

			//E note (Mi)
			case 'm':
				System.out.print("Mi");
				note = "E";
				octave++;
				resetOctave = true;
				break;

			//F note (Fa)
			case 'ù':
				System.out.print("Fa");
				note = "F";
				octave++;
				resetOctave = true;
				break;

			//G note (Sol)
			case '*':
				System.out.print("Sol");
				note = "G";
				octave++;
				resetOctave = true;
				break;

			case '+':
				octave++;
				println("\nAugmentation d'octave");
				break;
			case '-':
											//B note (Si)
				octave--;
				println("\nDiminution d'octave");
				break;

			case '1':
				this.instrument = new Piano();
				println("\nSélection : Piano");
				break;
			case '2':
				this.instrument = new Voice();
				println("\nSélection : Voix");
				break;
			case '3':
				this.instrument = new PanFlute();
				println("\nSélection : Flute de Pan");
				break;

			default:
				println("Touche non-valide");
				break;
		}

		this.instrument.reset(note + octave.toString());
		this.orchestre.setLength(0.8);
		this.orchestre.play();

		//Reset
		if(resetOctave) octave--;
		note = "";

		//Update the UI :
		this.pan.setOctave(octave);
		this.pan.repaint();
	}
}



void main(){
	MainWindow win = new MainWindow("Synthé son");
}
