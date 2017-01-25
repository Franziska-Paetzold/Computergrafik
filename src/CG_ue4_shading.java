import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import lenz.opengl.AbstractSimpleBase;
import lenz.opengl.utils.ShaderProgram;
import lenz.opengl.utils.Texture;

public class CG_ue4_shading extends AbstractSimpleBase {
	

	public static void main(String[] args) {
		new CG_ue4_shading().start();

	}

	int r = 0;
	private double xy=0;
	private Texture t;
	private Texture t2;
	private Texture t3;
	private int rotateX;
	private int  rotateY;
	private ShaderProgram sp;

	@Override
	protected void initOpenGL() {
		glMatrixMode(GL_PROJECTION);
		glFrustum(-1, 1, -1, 1, 1, 100);
		glMatrixMode(GL_MODELVIEW); // Stack für Projektionen
		glEnable(GL_CULL_FACE);
		glEnable(GL_TEXTURE_2D); //für Textur
		t = new Texture("textur_heller_felsen.jpg", 10); //10x kleiner Mipmappiing
		t2 = new Texture("minitextur.jpg");
		t3 = new Texture("minitextur2.jpg");
		glShadeModel(GL_FLAT);
		sp = new ShaderProgram("Textur_Shader");
		
		
	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT); // löschen
		glBindTexture(GL_TEXTURE_2D, t.getId());
		glLoadIdentity();

		glRotated(rotateX, 0,1,0);
		glRotated(rotateY, 1,0,0);
		glTranslated(0, 0, -5);
		glRotated(2 + r, 0, 1, 1); // Rotation um x,y,z
		r++;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S) == true || Keyboard.isKeyDown(Keyboard.KEY_DOWN) == true)
		{
		rotateY -= 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_W) == true || Keyboard.isKeyDown(Keyboard.KEY_UP) == true)
		{
		
		rotateY +=1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S) == true || Keyboard.isKeyDown(Keyboard.KEY_RIGHT) == true)
		{
		rotateX -= 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_W) == true || Keyboard.isKeyDown(Keyboard.KEY_LEFT) == true)
		{
		
		rotateX +=1;
		}
		//Skalierungsanimation via Zeit
				long time =(Sys.getTime()/10000);
				if(time%2==0)
				{
					glScaled(xy,xy,1);
					xy+=0.01; //xy darf nicht 0 sein
				}
				else
				{
					glScaled(-xy,-xy,1);
					xy-=0.01;
				}
		createCube(); //Alt + Shift + m zum Methoden aus 
		
		glUseProgram(sp.getId()); //an
		createTetrahedron();
		glUseProgram(0); //aus
		

	}
	
	private void createTetrahedron()
	{
		glLoadIdentity();
		glRotated(1, 0,1,0);
		
		//TETAEDRON BY HAND
	    glBegin(GL_TRIANGLES);

	    //front triangle
	    glColor3d(1.0, 0.0, 0.0);
	    glVertex3f(0.0f, 2.0f, 0.0f);
	    glVertex3f( -2.0f, -2.0f, 0.0f);
	    glVertex3f( 2.0f,  -2.0f, 0.0f);

	    //right side triangle
	    glColor3d(0.0, 0.0, 1.0);
	    glVertex3f( 2.0f,  -2.0f, 0.0f);
	    glVertex3f(0.0f, 2.0f, 0.0f);
	    glVertex3f( 0.0f,  -2.0f, -2.0f);

	    //left side triangle
	    glColor3d(1.0, 1.0, 1.0);
	    glVertex3f( -2.0f, -2.0f, 0.0f);
	    glVertex3f(0.0f, 2.0f, 0.0f);
	    glVertex3f( 0.0f,  -2.0f, -2.0f);

	    //bottom triangle
	    glColor3d(0.0, 1.0, 0.0);
	    glVertex3f( -2.0f, -2.0f, 0.0f);
	    glVertex3f( 2.0f,  -2.0f, 0.0f);
	    glVertex3f( 0.0f,  -2.0f, -2.0f);

	    glEnd();

		}

	private void createCube() {
		glBegin(GL_QUADS);

		// vorne, grün
		glColor3d(1, 1, 1); // untergrund weiß, damit alpha nicht sichtbar
		glTexCoord2d(1, 0); //eigene Koordinaten
		glVertex3d(1, -1, -1); // A
		glTexCoord2d(1, 1);
		glVertex3d(1, 1, -1); // B
		glTexCoord2d(0, 1);
		glVertex3d(-1, 1, -1); // C
		glTexCoord2d(0, 0);
		glVertex3d(-1, -1, -1); // D

		// hinten, rot
		//glColor3d(1, 0, 0); //oder Farben ganz raus, damit ssie nicht durchscheinen
		glVertex3d(1, -1, -3); // E
		glVertex3d(-1, -1, -3); // F
		glVertex3d(-1, 1, -3); // G
		glVertex3d(1, 1, -3); // H
		glEnd();

		
		glBindTexture(GL_TEXTURE_2D, t2.getId()); // nur eine Textur pro end Begin/ End block
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glBegin(GL_QUADS);
		// rechts, blau
		//glColor3d(0, 0, 1);
		glTexCoord2d(1, 0);
		glVertex3d(1, 1, -1);// B
		glTexCoord2d(1, 1);
		glVertex3d(1, -1, -1);// A
		glTexCoord2d(0, 1);
		glVertex3d(1, -1, -3);// E
		glTexCoord2d(0, 0);
		glVertex3d(1, 1, -3); // H
		glEnd();
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR); //Ursprung
		glBegin(GL_QUADS);
		// links, cyan
		//glColor3d(0, 1, 1);
		glTexCoord2d(0, 0);
		glVertex3d(-1, 1, -3); // G
		glTexCoord2d(1, 0);
		glVertex3d(-1, -1, -3); // F
		glTexCoord2d(1, 1);
		glVertex3d(-1, -1, -1); // D
		glTexCoord2d(0, 1);
		glVertex3d(-1, 1, -1); // C
		glEnd();

		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glBegin(GL_QUADS);
		// oben, magenta
		//glColor3d(1, 0, 1);
		glTexCoord2d(0, 0);
		glVertex3d(1, 1, -1); // B
		glTexCoord2d(1, 0);
		glVertex3d(1, 1, -3); // H
		glTexCoord2d(1, 1);
		glVertex3d(-1, 1, -3); // G
		glTexCoord2d(0, 1);
		glVertex3d(-1, 1, -1); // C

		// unten, gelb
		glColor3d(1, 1, 0);
		glVertex3d(1, -1, -1); // A
		glVertex3d(-1, -1, -1); // D
		glVertex3d(-1, -1, -3); // F
		glVertex3d(1, -1, -3); // E

		glEnd();
	}
}
