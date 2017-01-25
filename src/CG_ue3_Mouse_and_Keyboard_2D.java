import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import lenz.opengl.AbstractSimpleBase;

public class CG_ue3_Mouse_and_Keyboard_2D extends AbstractSimpleBase {
	int counter=0;

public static void main(String[] args) {
new CG_ue3_Mouse_and_Keyboard_2D().start();
}

@Override
protected void initOpenGL() {
glMatrixMode(GL_PROJECTION);
glOrtho(-1, 1, -1, 1, 0, 1);
glMatrixMode(GL_MODELVIEW);

glShadeModel(GL_FLAT);
}

double greenX =0;
double greenY =0;
@Override
protected void render() {
//glClear(GL_COLOR_BUFFER_BIT);
int x= Mouse.getX();
int y=Mouse.getY();
double xNew= x/400.0-1;
double yNew= y/300.0-1;

if (Mouse.isButtonDown(0) == true)
{
glPointSize(10);
glBegin(GL_POINTS);
glColor3d(1, 0, 0);
glVertex2d(xNew, yNew);
glEnd();

Display.update();
glBegin(GL_POINTS);
glColor3d(1, 0, 0);
glVertex2d(xNew, yNew);
glEnd();

}

if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) == true)
{
createGreenPoint(greenX, greenY);
Display.update();
createGreenPoint(greenX, greenY);
}
if(Keyboard.isKeyDown(Keyboard.KEY_W) == true || Keyboard.isKeyDown(Keyboard.KEY_UP) == true)
{
greenY+= 0.01;
createGreenPoint(greenX, greenY);
Display.update();
createGreenPoint(greenX, greenY);
}
if(Keyboard.isKeyDown(Keyboard.KEY_S) == true || Keyboard.isKeyDown(Keyboard.KEY_DOWN) == true)
{
greenY-= 0.01;
createGreenPoint(greenX, greenY);
Display.update();
createGreenPoint(greenX, greenY);
}
if(Keyboard.isKeyDown(Keyboard.KEY_A) == true || Keyboard.isKeyDown(Keyboard.KEY_LEFT) == true)
{
greenX-= 0.01;
createGreenPoint(greenX, greenY);
Display.update();
createGreenPoint(greenX, greenY);
}
if(Keyboard.isKeyDown(Keyboard.KEY_D) == true || Keyboard.isKeyDown(Keyboard.KEY_RIGHT) == true)
{
greenX+= 0.01;
createGreenPoint(greenX, greenY);
Display.update();
createGreenPoint(greenX, greenY);
}


}


private void createGreenPoint(double greenX, double greenY)
{
glPointSize(10);
glBegin(GL_POINTS);
glColor3d(0, 1, 0);
glVertex2d(greenX, greenY);
glEnd();
}

}
