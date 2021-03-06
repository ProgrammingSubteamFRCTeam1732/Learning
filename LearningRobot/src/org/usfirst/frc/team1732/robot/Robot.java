/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1732.robot;

import static org.usfirst.frc.team1732.robot.config.RobotConfig.config;

import org.usfirst.frc.team1732.robot.commands.DriveBackwardsTwice;
import org.usfirst.frc.team1732.robot.input.Joysticks;
import org.usfirst.frc.team1732.robot.sensors.Sensors;
import org.usfirst.frc.team1732.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

	// subsystems
	public static Drivetrain drivetrain;
	public static Sensors sensors;
	public static Joysticks joysticks;

	// config
	public static final int PERIOD_MS = 10;
	public static final int CONFIG_TIMEOUT = 10; // recommended timeout by CTRE

	private Command autoCommand;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		setPeriod(PERIOD_MS / 1000.0); // periodic methods will loop every 10 ms (1/100 sec)
		drivetrain = new Drivetrain(config.getNode("drivetrain"));
		sensors = new Sensors();
		joysticks = new Joysticks(config.getNode("joysticks"));
		autoCommand = new DriveBackwardsTwice(); // put your command here
	}

	@Override
	public void robotPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		autoCommand.start();
	}
}
