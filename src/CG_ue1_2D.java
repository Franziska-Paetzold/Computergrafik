	import static org.lwjgl.opengl.GL11.*;

import lenz.opengl.AbstractSimpleBase;

public class CG_ue1_2D extends AbstractSimpleBase {

	public static void main(String[] args) {
		new CG_ue1_2D().start();

	}


	private double xy;

	@Override
	protected void initOpenGL() {
		glMatrixMode(GL_PROJECTION);
		glOrtho(-1, 1, -1, 1, 0, 1); // orthgonal Projektion (Koordinatensystem) --> sinnvolle Zahlen -->2D
		// glOtho zu ersten urch glFrustum(l,r,u,o,n,f) (3D) bsp: glFrustum(-1,1,-1,1,1,100);
		glMatrixMode(GL_MODELVIEW); //Stack für Projektionen

		glShadeModel(GL_FLAT);
	}

	
	@Override
	protected void render() {
		glClear(GL_COLOR_BUFFER_BIT); //löschen 

		glPointSize(4);
		glBegin(GL_POINTS); //was möchte ich zeichnen? (Punkte, ...)
		glColor3f(1f, 1f, 0f); //gelb
		glVertex2f(-0.9f, 0.9f); // vertex--> braucht KOodianten, 2 --> 2D, f--> float i int, d double, b boolean
		glVertex2f(-0.9f, 0.8f);
		glVertex2f(-0.8f, 0.8f);
		glVertex2f(-0.8f, 0.9f);
		glEnd(); 
		
		glBegin(GL_LINES);
		glColor3f(0.5f, 0.8f, 0f); //limetten grün
		glVertex2f(-0.82f, 0.7f);
		glVertex2f(-0.67f, 0.85f);
		glEnd();
		
		glPointSize(50);
		glBegin(GL_POINTS);
		glColor3f(1f, 0, 0);// rot
		glVertex2f(-0.6f, 0.85f);
		glEnd();
		
		
//		//Polygon entsteht aus Dreiecken (?)
//		glBegin(GL_TRIANGLE_STRIP);
//		glColor3f(1, 0.9f, 0);
//		glVertex3f(-0.9f, -0.9f, -0.4f);     // A
//		glVertex3f( -0.3f, -0.2f, -0.4f);     // B
//		glVertex3f( 0.0f,  0.8f, -0.4f);     // C
//		glVertex3f(0.3f,  0.0f, -0.4f);     // D
//		glVertex3f(0.2f, -0.15f, -0.4f);     // E
//		glEnd();
		
		/*
		
		//Polygon besser:
		glBegin(GL_POLYGON); //Reihenfolge beachten (Kreis)
		glColor3f(0.2f, 1, 1); //RGB
		glVertex2d(0.5, 0.5); 
		glVertex2d(0.5, -0.5);
		glVertex2d(-0.5, -0.5);
		glVertex2d(-0.5, 0.5);
		glVertex2d(0,0.7);
		glEnd();
		
		*/
		
		
		//Linien über das ganze Bild
		glBegin(GL_LINES);
		for (float i=-1; i<= 1; i+=0.2)
		{
			glVertex2d(i, 1);
			glVertex2d(i, -1);
		}
		glEnd();
		
		//Linien über das ganze Bild über Kreuz 
		glBegin(GL_LINES); 
		for (float i=-1; i<= 1; i+=0.2)
		{
			glVertex2d(i, 1);
			glVertex2d(-i, -1);
		}
		glEnd();
		
		
		//Transformation
		//glTranslated(x,y,z); //3 Parameter --> Vektor (?), bei 2d z=0
		
		//Skalieren
		//glScale(x,y,z); //3 Parameter, zieht in die jeweiligen Achsenrichtungen, bei 2d z=1  (!)
		
		//Rotation
		//glRotate(w,x,y,z); //w = winkel in Grad (0-360), Richtungsvektor für Rotation (bei 2d 0,0,1)
		
		//immer VOR das Objekt erstellt wird und somit VOR begin&end  (!!!)
		
		//STACK oben rauf und oben wieder runter
		//		glLoadIdentity(); // setzt irgendwie alles 1 oder 0 oder so
		//		glPushMatrix(); //Matrix merken
		//		glPopMatrix(); //Matrix zurückholen
				
		//		beispiel Reihenfolge:
		//			glLoadIdentity();
		//			zeichnen
		//			glTranslate
		//			glPush
		//			zeichnen
		//			glRotate
		//			zeichenne
		//			glPop
		//			glRotate
		//			zeichenen
		
		//___________________________________________________________________________________
		// zu 3D Kamera, glFrustum(links, rechts, unten, oben, nah, fern)
		// l,r,u,o Dimension der Bildebene, siehe skizze Hefter
		
		
		//Skalieren
				if(xy<=5)
				{
					xy+=0.03; //xy darf nicht 0 sein
				}
				glScaled(xy,xy,1);
				glBegin(GL_POLYGON); //Reihenfolge beachten (Kreis)
				glColor3f(0.2f, 1, 1); 
				glVertex2d(0.2, 0.2); 
				glVertex2d(0.2, -0.2);
				glVertex2d(-0.2, -0.2);
				glVertex2d(-0.2, 0.2);
				glVertex2d(0,0.3);
				glEnd();
				
		
		glLoadIdentity();
		//Push alternativ
		//translation
		glTranslated(0.5, 0.5, 0); 
		glPointSize(10);
		glBegin(GL_POINTS);
		glColor3f(1, 0.9f, 0);
		glVertex2f(-0.5f, -0.5f);
		glEnd();
		
		
		glLoadIdentity();
		//pop alternativ
		//Rotation
			
		
		/*
		 * erst Skalieren, dann Translation:
		 * nur Polygon wächst, rest bleibt an Ort und Stelle
		 * 
		 * erst Translation, dann skalieren:
		 * alles wird nach oben, rechts verschoben und alles wächst
		 */
		
		
	}
}
