/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5752.robot;

//import org.usfirst.frc.team5752.robot.commands.autoDrive;




import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team5752.robot.commands.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	public static Joystick stick = new Joystick(0);
	
	Button button1 = new JoystickButton(stick, 1), //trigger
			button2 = new JoystickButton(stick, 2), //side thumb
			button3 = new JoystickButton(stick, 3), //bottom left
			button4 = new JoystickButton(stick, 4), //bottom right
			button5 = new JoystickButton(stick, 5), //upper left
			button6 = new JoystickButton(stick, 6), //upper right
			button7 = new JoystickButton(stick, 7), //labeled
			button8 = new JoystickButton(stick, 8), //labeled
			button9 = new JoystickButton(stick, 8), //labeled
			button10 = new JoystickButton(stick, 10), //labeled
			button11 = new JoystickButton(stick, 11), //labeled
			button12 = new JoystickButton(stick, 12); //labeled
	
	public OI() {
		button12.whenPressed( new ForwardUntilBump() );
	}
}