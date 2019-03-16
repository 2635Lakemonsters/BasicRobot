/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Flower extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  //DoubleSolenoid extender1;
  DoubleSolenoid extender2;
  DoubleSolenoid grabber;
  FlowerIO currentFlowerIO;
  FlowerBud currentFlowerBud;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public Flower(){
    //extender1 = new DoubleSolenoid(0, 1);
    extender2 = new DoubleSolenoid(0, 1);

    grabber = new DoubleSolenoid(2, 3);
  }

  public void setFlowerBud(FlowerBud input) {
    if(input == FlowerBud.FLOWER) {
        openFlower();
    } else if(input == FlowerBud.BUD) {
        closeFlower();
    }
    currentFlowerBud = input;
  }

  public void setFlowerIO(FlowerIO input) {
    if(input == FlowerIO.OUT) {
        extendFlower();
    } else if(input == FlowerIO.IN) {
        retractFlower();
    }
    currentFlowerIO = input;
  }

  public void extendFlower(){
    //extender1.set(Value.kForward);
    extender2.set(Value.kForward);
  }

  public void retractFlower(){
    //extender1.set(Value.kReverse);
    extender2.set(Value.kReverse);
  }

  public void toggleExtender(){
    Value currentStatus = extender2.get();

    if(currentStatus == Value.kReverse){
      //extender1.set(Value.kForward);
      extender2.set(Value.kForward);
    }else{
      //extender1.set(Value.kReverse);
      extender2.set(Value.kReverse);
    }
  }

  public void openFlower(){
    grabber.set(Value.kForward);
  }

  public void closeFlower(){
    grabber.set(Value.kReverse);
  }

  public void toggleFlower(){
    Value currentStatus = grabber.get();

    if(currentStatus == Value.kReverse){
      grabber.set(Value.kForward);
    }else{
      grabber.set(Value.kReverse);
    }
  }

  public static enum FlowerBud {
    BUD(),
    FLOWER();
  }

  public static enum FlowerIO {
    IN(),
    OUT();
  }
}
