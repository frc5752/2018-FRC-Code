/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5752.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5752.robot.commands.*;
import org.usfirst.frc.team5752.robot.subsystems.Climber;
import org.usfirst.frc.team5752.robot.subsystems.Flipper;
import org.usfirst.frc.team5752.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5752.robot.subsystems.Camera;
import edu.wpi.first.wpilibj.DriverStation.*;
import com.kauailabs.navx.frc.AHRS;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	public static final DriveTrain drivetrain = new DriveTrain();
	public static final Climber myClimber = new Climber();
	public static final Flipper myFlipper = new Flipper();
	public static final Camera myCamera = new Camera();
	public static OI oi;
	public static DriverStation ds = DriverStation.getInstance();
	public static AHRS ahrs;
	

//	public static final DigitalInput diLimit1 = new DigitalInput(0);
	
	Command AutonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		CameraServer.getInstance().startAutomaticCapture();
		try {

			ahrs = new AHRS(SPI.Port.kMXP);
			ahrs.reset();
			ahrs.zeroYaw();
		} catch (RuntimeException ex) {

			DriverStation.reportError("navx-mxp not connected. Error: " + ex.getMessage(), true);
		}
		
		// Clean out the Smart Dashboard Values of Old
		for( int i=0; i<=9; i++)
		{
			showOnDash(i, "");
		}
		
		// Set auto mode command?
		AutonomousCommand = new AutonomousCommand();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		myCamera.StopSubsystem();
		myFlipper.StopSubsystem();
		myClimber.StopSubsystem();
		drivetrain.StopSubsystem();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		myCamera.StopSubsystem();
		myFlipper.StopSubsystem();
		myClimber.StopSubsystem();
		drivetrain.StopSubsystem();

	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		// Put a 0.5 Second Delay here to make sure that the machine gets valid data
		
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
    	Alliance a = ds.getAlliance();

    	//SmartDashboard.putString( "DB/String 1", "Alliance: " + a.toString());

		// schedule the autonomous command (example)
		if (AutonomousCommand != null) {
			AutonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.

		if (AutonomousCommand != null) {
			AutonomousCommand.cancel();
		}
    	DriverStation.Alliance colour = ds.getAlliance();
    	showOnDash( 0, "Alliance: " + colour.toString());
    	//SmartDashboard.putString( "DB/String 0", "Alliance: " + colour.toString());
    	
    	int station = ds.getLocation();
    	showOnDash( 1, "Station: " + station);
    	//SmartDashboard.putString( "DB/String 1", "Station: " + station);
    	
    	if( colour == DriverStation.Alliance.Blue )
    	{
    		///  Then I'm Blue!
    	}
    	else
    	{
    		/// I must be Red!
    		
    	}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		double dXDirection = oi.stick.getRawAxis(0);
		double dYDirection = oi.stick.getRawAxis(1);
		double dRotation= oi.stick.getRawAxis(2);	
		// IS THIS TRUE?
		// LETS PUT THESE VALUES ON THE DASH
		showOnDash(7,"X:"+Double.toString(dXDirection));
		showOnDash(8,"Y:"+Double.toString(dYDirection));
		showOnDash(9,"R:"+Double.toString(dRotation));
		
		drivetrain.drive(dXDirection, dYDirection, dRotation);	
		
		double dPrecision = (oi.stick.getRawAxis(3)+1)/2;
		double dMax = 0.25;

		if( oi.stick.getRawButton(7))
		{
			myClimber.hookUp(dPrecision*dMax);
		}
		else if( oi.stick.getRawButton(8))
		{
			myClimber.hookDown(dPrecision*dMax);
		}
		else
		{
			myClimber.hookStop();
		}

		// If button 2 is pressed the climb away!
		if( oi.stick.getRawButton(2))
		{
			myClimber.ClimberUp(dPrecision*dMax);
		}
		else if( oi.stick.getRawButton(4))
		{
			myClimber.ClimberDown( dPrecision*dMax );
		}
		else
		{
			myClimber.ClimberStop();
		}
		
		
		if( oi.stick.getRawButton(3) && oi.stick.getRawButton(1))
		{
			myFlipper.Raise(dPrecision);
		}
		else
		{ 
			myFlipper.Stop();
		}
		
		
		if( (oi.stick.getPOV() > 180) && (oi.stick.getPOV() < 360) )
		{
			myCamera.Left();
		}
		else if( (oi.stick.getPOV() > 0) && (oi.stick.getPOV() < 180))
		{
			myCamera.Right();
		}
		else
		{
			myCamera.Stop();
		}
		
		// THIS IS A TEST FEATURE - BUTTON 11 SPINS ROBOT to 45 DEG
		if( oi.stick.getRawButton(11))
		{
			drivetrain.rotateTo(45);
		}
		
		// Button 12 - LOOK!
		if( oi.stick.getRawButton(12))
		{
			double dr = myCamera.getRange();
			if( dr == -1 )
			{
				showOnDash(8,"WALL");
			}
			else
			{
				showOnDash(8, "in: " + Double.toString(dr));
			}
		}
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
	public void showOnDash( int x, String y)
	{
		if( (x>=0) && (x<=9))
		{
			String s = new String( "DB/String " + Integer.toString(x));
			SmartDashboard.putString(s, y);
		}		
	}
	

}

