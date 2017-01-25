import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.Sys;

import lenz.opengl.AbstractSimpleBase;

public class CG_ue2_3D extends AbstractSimpleBase {
	private int r = 0;
	private double xy=0;

	public static void main(String[] args) {
		new CG_ue2_3D().start();

	}


	@Override
	protected void initOpenGL() {
		glMatrixMode(GL_PROJECTION);
		glFrustum(-1, 1, -1, 1, 1, 100);
		glMatrixMode(GL_MODELVIEW); // Stack für Projektionen
		glEnable(GL_CULL_FACE);
		glEnable(GL_TEXTURE_2D); //für Textur
		glShadeModel(GL_FLAT);
	}

	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT); // löschen
		glLoadIdentity();
		
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
		
		//kontinuierliche Translation und Rotation
		glTranslatef(0, 0, -5);
		glRotated(2 + r, 0, 1, 1); // Rotation um x,y,z
		r++;
		
		//Würfel
		glBegin(GL_QUADS);

		// vorne, grün
		glColor3d(0, 1, 0); // untergrund weiß, damit alpha nicht sichtbar
		glVertex3d(1, -1, -1); // A
		glVertex3d(1, 1, -1); // B
		glVertex3d(-1, 1, -1); // C
		glVertex3d(-1, -1, -1); // D

		// hinten, rot
		glColor3d(1, 0, 0);
		glVertex3d(1, -1, -3); // E
		glVertex3d(-1, -1, -3); // F
		glVertex3d(-1, 1, -3); // G
		glVertex3d(1, 1, -3); // H

		// rechts, blau
		glColor3d(0, 0, 1);
		glVertex3d(1, 1, -1);// B
		glVertex3d(1, -1, -1);// A
		glVertex3d(1, -1, -3);// E
		glVertex3d(1, 1, -3); // H
		
		// links, cyan
		glColor3d(0, 1, 1);
		glVertex3d(-1, 1, -3); // G
		glVertex3d(-1, -1, -3); // F
		glVertex3d(-1, -1, -1); // D
		glVertex3d(-1, 1, -1); // C
		
		// oben, magenta
		glColor3d(1, 0, 1);
		glVertex3d(1, 1, -1); // B
		glVertex3d(1, 1, -3); // H
		glVertex3d(-1, 1, -3); // G
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
