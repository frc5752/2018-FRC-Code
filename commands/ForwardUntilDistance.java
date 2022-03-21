package org.usfirst.frc.team5752.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5752.robot.*;
/**
 *
 */
public class ForwardUntilDistance extends Command {
	double dDistanceTravelled;
	double dDispX;
	double dDispY;
	double dStartX;
	double dStartY;
	double dDistToGo;

    public ForwardUntilDistance(double d) {
        
    	requires(Robot.drivetrain);
    	setTimeout(5);
    	dDistToGo=d;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ahrs.resetDisplacement();
    	dStartX=Robot.ahrs.getDisplacementX();
    	dStartY=Robot.ahrs.getDisplacementY();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	dDispX = Robot.ahrs.getDisplacementX();
    	dDispY = Robot.ahrs.getDisplacementY();
    	Robot.drivetrain.drive( 0,  0, - 0.9 );
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	boolean ret=isTimedOut();
    	
    	if( (Math.sqrt( (dDispX - dStartX)*(dDispX - dStartX)   -   (dDispY - dStartY)*(dDispY - dStartY)) >= dDistToGo) )
    	{
    		ret=true;
    		}
        return ret	;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
