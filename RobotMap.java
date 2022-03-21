/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5752.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static final int MOTOR_FRONT_LEFT = 0;
	public static final int MOTOR_FRONT_RIGHT = 1;
	public static final int MOTOR_BACK_LEFT =2;
	public static final int MOTOR_BACK_RIGHT = 3;
	public static final int UNUSED_TALON = 4;// Motor that climbs

	public static final int CLIMBER_MOTOR = 5;// Motor that climbs TALON
	public static final int CLIMBER_MOTOR2 = 8;// Motor2 that climbs SPARK
	
	public static final int HOOK_MOTOR = 7;// Motor that raises the hook
	public static final int CAMERA_MOTOR = 9;
	public static final int FLIPPER_MOTOR = 6;// Motor that raises the lift

	public static final double ROTATION_DEADBAND = .2;
	public static final double XY_DEADBAND = .2;

}
