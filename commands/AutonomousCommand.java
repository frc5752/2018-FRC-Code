package org.usfirst.frc.team5752.robot.commands;

import org.usfirst.frc.team5752.robot.Robot;
import org.usfirst.frc.team5752.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5752.robot.subsystems.Camera;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
/**
 *
 */
public class AutonomousCommand extends CommandGroup {

    public AutonomousCommand() {
    	requires( Robot.drivetrain );
    	requires( Robot.myCamera );
    	double S = DriverStation.getInstance().getLocation();
    	Timer t = new Timer();
    	t.start();
    	t.reset();
    	t.delay(0.25);// This delay is to make sure that the data got to us regarding the field setup.
    	if( Robot.myCamera.getRange() == -1 )
    	{
    		// Then we're facing the wall at the start of Auto,
    		// so do something OTHER THAN The plan below
    		// maybe delay first and then proceed with the plan?
    		t.delay(3);  //Wait for any other robots to move out of the way.
    	}
    	else
    	{
 
 
    	double p1;
    	double p2=90;
    	if( isRight() )
    	{
    		S=-S;
    		p2=-p2;
    	}
     	// Where does this equation for p1 (below) come from?
    	//
    	// There are 6 scenarios
    	// -3, -2, -1, 1, 2, and 3
    	// When S is set equal to DriverStation.getLocation() above, it
    	// has a value of 1, 2, or 3... that's the location of the driver
    	// station on the field (left to right).
    	// 
    	// the isLeft() function returns true if the teams SAME COLOUR is to the
    	// left on the Switch when looking out into the field.
    	//
    	// The different scenarios are:
    	//
    	// Team is Position 1, Team's Colour is to the Right: Scenario: -1
    	// Team is Position 2, Team's Colour is to the Right: Scenario: -2
    	// Team is Position 3, Team's Colour is to the Right: Scenario: -3
      	// Team is Position 1, Team's Colour is to the Left: Scenario: 1
      	// Team is Position 2, Team's Colour is to the Left: Scenario: 2
      	// Team is Position 3, Team's Colour is to the Left: Scenario: 3
    	//
    	// In these scenarios there is a function:
    	// -3 -> 0 Degrees as the first Angle
    	// -2 -> 48, -1 -> 30, 1 -> 0, 2 -> -48, 3-> -30
    	//
    	// A system of equations can be created by these values
    	// Ax^6 + Bx^5 + Cx^4 + Dx^3 + Ex^2 + Fx^1 + Gx^0 = VALUE
    	//
    	// Or:
    	// B(-3)^5 + C(-3)^4 + D(-3)^3 + E(-3)^2 + F(-3)^1 + G(-3)^0 = 0
    	// B(-2)^5 + C(-2)^4 + D(-2)^3 + E(-2)^2 + F(-2)^1 + G(-2)^0 = 48
    	// B(-1)^5 + C(-1)^4 + D(-1)^3 + E(-1)^2 + F(-1)^1 + G(-1)^0 = 30
    	// B(1)^5 + C(1)^4 + D(1)^3 + E(1)^2 + F(1)^1 + G(1)^0 = -30
    	// B(2)^5 + C(2)^4 + D(2)^3 + E(2)^2 + F(2)^1 + G(2)^0 = -48
    	// B(3)^5 + C(3)^4 + D(3)^3 + E(3)^2 + F(3)^1 + G(3)^0 = 0
    	// 
    	// Which simplifies to:
    	// -243B + 81C -27D + 9E -3F + G = 0
    	// -32B + 16C  -8D + 4E -2F + G = 48
    	// -B   + C  - D  + E - F + G = 30
    	// B   + C  + D  + E + F + G = -30
    	// 32B + 16C  +8D + 4E +2F + G = -48
    	// 243B + 81C +27D + 9E +3F + G = 0
    	//
    	// This may LOOK complex, but it's just a system of simultaneous equations
    	// it can be solved using a TI-83 calculator or any CAS using an augmented matrix and
    	// Reduced Row Echelon Form.  The solutions are the coefficients of 
    	// the equation below... B -> G.
    	p1=0.85*Math.pow(S, 5)+0.25*Math.pow(S, 4)-7.25*Math.pow(S, 3) - 6.25*S*S-8.6*S+21;
    	
    	Robot.drivetrain.rotateTo(p1);
    	new ForwardUntilBump();
    	Robot.drivetrain.rotateTo(p2);
       	if( (S==-3) || (S==1))
    	{
       		new ForwardUntilDistance(4.3);	// 168 inches (approx)
    	}
       	else
       	{
       		new ForwardUntilBump();
       	}
    	new TimedFlipper();
    	}
    	
    	
    	
       	//x, y, roation, time
    	//A POSITIVE X-VALUE MAKES IT MOVE RIGHT
    	//A POSITIVE ROTATION-VALUE MAKES IT ROTATE CLOCKWISE
    	
    	//addSequential(new autoDrive(0, 0, .3, 100));
    	
    	
    	//addSequential(new autoDrive(0, .3, 0, 4));
    	//addSequential(new autoDrive(0,0,0,45));
    	//addSequential(new autoDrive(0,0,0,-45));
    	//addSequential(new ForwardUntilBump());
    	//addSequential(new rotate(-90));
    	
    	//addSequential(new autoVision(90));
    	
    	//sam: we want to be able to drive forward a certain distance, then rotate left or right 60 degrees. 
    	//Then we want a "look" function that gets the x value of the peg/tape, and an "adjust" function that strafes the bot until it can deliver the peg
    	//pseudocode for starting right position:
    	//threshold=the position of the tape/peg that we want
    	//x=the number the camera thinks the peg is currently
        // move forward (some distance)
    	// rotate left (60 degrees)
    	//while (x!=threshold){
    	//     if(x>threshold){
    	//			move left
    	//		}
    	//		if(x<threshold){
    	//			move right
    	//		}
    	//}
    	//move forward(some distance)
    	t.stop();
    }
	public boolean isLeft()
	{
		boolean ret=false;
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if( gameData.length() > 0 )
		{
			if( gameData.charAt(0) == 'L')
			{
				ret = true;
			}
			
		}
		return ret;
	}
	public boolean isRight()
	{
		return !isLeft();
	}
}

