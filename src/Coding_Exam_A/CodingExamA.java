package Coding_Exam_A;

import java.awt.Color;

import javax.swing.JOptionPane;

import org.jointheleague.graphical.robot.Robot;

public class CodingExamA {
	public static void main(String[] args) {
		/*
		 * Write a program that asks the user for three pieces of information.
		 * 1. How many robots
		 * 2. The color of the shapes
		 * 3. How many sides each shape will have
		 * 
		 * Once the information has been collected, the program will then make the requested number of robots
		 * each draw the requested shape in the requested color. The robots should execute at the same time so 
		 * Threads will need to be used. Arrange the robots so that the shapes do not overlap.
		 * For full credit, define the Thread functions using lambdas.
		 * 
		 * See the Coding_Exam_A_Demo.jar for an example of what the finished product should look like.
		 */
		int robots = Integer.parseInt(JOptionPane.showInputDialog("How many robots?"));
		robots = robots > 0 ? robots : 1;
		int sides = Integer.parseInt(JOptionPane.showInputDialog("How many sides will each shape have?"));
		Color c = JOptionPane.showInputDialog("Should they be red(if not, they will be green)").contentEquals("yes") ? Color.red : Color.green;
		Robot[] rs = new Robot[robots];
		int i = 0;
		int j = 0;
		Thread[] ts = new Thread[robots];
		for (int q = 0; q < robots; q++)
		{
			rs[q] = new Robot(100 + (i * 200),50 + (j * 100));
			rs[q].setSpeed(500);
			rs[q].setPenColor(c);
			rs[q].penDown();
			Robot r = rs[q];
			i++;
			if (i == 4) { i = 0; j++; }
		    ts[q] = new Thread(()->{
		    	for (int x = 0; x < sides; x++)
		    	{
		    		r.move(50);
		    		r.turn(360/(sides));
		    	}
	    	});
		}
		for (Thread t : ts)
		{
			t.start();
		}
	}
}
