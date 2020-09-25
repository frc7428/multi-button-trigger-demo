/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {
  private final SpeedController mElevatorMotor = new PWMVictorSPX(Constants.ELEVATOR_MOTOR_PWM);
  /**
   * Creates a new ElevatorSubsystem.
   */
  public ElevatorSubsystem() {

  }

  public void raise() {
    mElevatorMotor.set(1);
  }

  public void lower() {
    mElevatorMotor.set(-1);
  }

  public void stop() {
    mElevatorMotor.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
