/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.LowerElevatorCommand;
import frc.robot.commands.RaiseElevatorCommand;
import frc.robot.commands.StopElevatorCommand;
import frc.robot.subsystems.ElevatorSubsystem;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ElevatorSubsystem mElevator = new ElevatorSubsystem();

  private final RaiseElevatorCommand mRaise = new RaiseElevatorCommand(mElevator);
  private final LowerElevatorCommand mLower = new LowerElevatorCommand(mElevator);
  private final StopElevatorCommand mStop = new StopElevatorCommand(mElevator);

  private final Joystick mJoystick = new Joystick(Constants.MECHANISM_JOYSTICK_USB);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton elevatorButton = new JoystickButton(mJoystick, Constants.ELEVATOR_BUTTON);
    JoystickButton reverseButton = new JoystickButton(mJoystick, Constants.REVERSE_BUTTON);

    // Buttons are examples of Triggers. A trigger is what "triggers" the command to occur.
    // Triggers are more general than buttons, but some ideas remain the same.
    // whileHeld -> whileActiveContinuous
    // whenInactive -> whenReleased

    // If we want to raise while the elevator button is pressed and the reverse button is NOT pressed.
    // The .and() lets us use both buttons.
    // The .negate(), basically, says we want to check when the button ISN'T pressed.
    // And we do need both - one pressed and one not pressed - to be checked. We can't leave out the reverse button here. Why?
    elevatorButton.and(reverseButton.negate()).whileActiveContinuous(mRaise).whenInactive(mStop);

    // In the case of lowering, we want the elevatorButton to be pressed and the reverse button to be pressed.
    // Since we're checking both to be pressed, we don't need to negate the reverse.
    elevatorButton.and(reverseButton).whileActiveContinuous(mLower).whenInactive(mStop);

    // There are other ways to combine triggers:
    // - .and() if both must be pressed
    // - .or() if multiple different buttons can do the same thing

    // One cool thing is that the buttons can even be on different controllers!
    // The .and() is especially useful if you want BOTH drivers doing something at the same time to trigger the action.
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
