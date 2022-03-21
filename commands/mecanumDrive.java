package org.usfirst.frc.team5752.robot.commands;

import org.usfirst.frc.team5752.robot.OI;
import org.usfirst.frc.team5752.robot.Robot;
import org.usfirst.frc.team5752.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class mecanumDrive extends Command {

    public mecanumDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	double X_DIRECTION = OI.stick.getRawAxis(0);
    	double Y_DIRECTION = OI.stick.getRawAxis(1);
    	double ROTATION = OI.stick.getRawAxis(2);
    	
    	DriveTrain.drive(X_DIRECTION, Y_DIRECTION, ROTATION);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
