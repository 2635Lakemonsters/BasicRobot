/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.model;

import frc.robot.subsystems.Elevator.Height;
import frc.robot.subsystems.Switcher.SwitcherState;
import frc.robot.subsystems.Flower.FlowerBud;
import frc.robot.subsystems.Flower.FlowerIO;
import frc.robot.Robot;


/**
 * Add your docs here.
 */
public class GameToolStateMachine {
    public GameTools currentState;

    public GameToolStateMachine(){
        currentState = GameTools.INITIAL;
    }

    public void reset() {
        GameTools nextState = resetState();
        Robot.elevator.setTargetHeight(nextState.elevator);
        Robot.switcher.moveSwitch(nextState.switcher);
        Robot.flower.setFlowerIO(nextState.io);
        Robot.flower.setFlowerBud(nextState.bud);
        currentState = nextState;
        System.out.println(currentState);
    }

    GameTools resetState() {
        return GameTools.INITIAL;
    }

    public void autonomousReset() {
        GameTools nextState = autonomousResetState();
        Robot.elevator.setTargetHeight(nextState.elevator);
        Robot.switcher.moveSwitch(nextState.switcher);
        Robot.flower.setFlowerIO(nextState.io);
        Robot.flower.setFlowerBud(nextState.bud);
        currentState = nextState;
        System.out.println(currentState);
    }

    GameTools autonomousResetState() {
        return GameTools.INITIAL_AUTO;
    }

    public void autoHatch() {
        GameTools nextState = autoHatchState();
        Robot.elevator.setTargetHeight(nextState.elevator);
        Robot.switcher.moveSwitch(nextState.switcher);
        Robot.flower.setFlowerIO(nextState.io);
        Robot.flower.setFlowerBud(nextState.bud);
        currentState = nextState;
        System.out.println(currentState);
    }

    GameTools autoHatchState() {
        return GameTools.AUTO_HATCHF;
    }

    public void increment() {
        GameTools nextState = incrementState();
        Robot.elevator.setTargetHeight(nextState.elevator);
        Robot.switcher.moveSwitch(nextState.switcher);
        Robot.flower.setFlowerIO(nextState.io);
        Robot.flower.setFlowerBud(nextState.bud);
        currentState = nextState;
        System.out.println(currentState);
    }

    GameTools incrementState() {
        switch(currentState) {
            case INITIAL:
                return GameTools.CARGO1;
            case INITIAL_AUTO:
                return GameTools.HATCH1F;
            case AUTO_HATCHF:
                return GameTools.HATCH2F;
            case AUTO_HATCHB:
                return GameTools.HATCH2B;
            case CARGO1:
                return GameTools.CARGOTRANSIT;
            case CARGOTRANSIT:
                return GameTools.CARGO2;
            case CARGO2:
                return GameTools.CARGOS;
            case CARGOS:
                return GameTools.CARGO3;
            case CARGO3:
                return GameTools.CARGO4;
            case CARGO4:
                return GameTools.CARGO4;
            case HATCH1B:
                return GameTools.HATCH2B;
            case HATCH2B:
                return GameTools.HATCH3B;
            case HATCH3B:
                return GameTools.HATCH3B;
            case HATCH1F:
                return GameTools.HATCH2F;
            case HATCH2F:
                return GameTools.HATCH3F;
            case HATCH3F:
                return GameTools.HATCH3F;
            default:
                return currentState;
        }
    }

    public void decrement() {
        GameTools nextState = decrementState();
        Robot.elevator.setTargetHeight(nextState.elevator);
        Robot.switcher.moveSwitch(nextState.switcher);
        Robot.flower.setFlowerIO(nextState.io);
        Robot.flower.setFlowerBud(nextState.bud);
        currentState = nextState;
        System.out.println(currentState);
    }

    GameTools decrementState() {
        switch(currentState) {
            case CARGO1:
                return GameTools.CARGO1;
            case CARGO2:
                return GameTools.CARGOTRANSIT;
            case CARGOTRANSIT:
                return GameTools.CARGO1;
            case CARGOS:
                return GameTools.CARGO2;
            case CARGO3:
                return GameTools.CARGOS;
            case CARGO4:
                return GameTools.CARGO3;
            case HATCH1B:
                return GameTools.HATCH1B;
            case HATCH2B:
                return GameTools.HATCH1B;
            case HATCH3B:
                return GameTools.HATCH2B;
            case HATCH1F:
                return GameTools.HATCH1F;
            case HATCH2F:
                return GameTools.HATCH1F;
            case HATCH3F:
                return GameTools.HATCH2F;
            default:
                return currentState;
        }
    }

    public void swap(){
        GameTools nextState = swapState();
        Robot.elevator.setTargetHeight(nextState.elevator);
        Robot.switcher.moveSwitch(nextState.switcher);
        Robot.flower.setFlowerIO(nextState.io);
        Robot.flower.setFlowerBud(nextState.bud);
        currentState = nextState;
        System.out.println(currentState);
    }

    GameTools swapState(){
        switch(currentState) {
            case CARGO2:
                return GameTools.HATCH1B;
            case CARGOTRANSIT:
                return GameTools.HATCH1B;
            case CARGOS:
                return GameTools.HATCH1B;
            case CARGO3:
                return GameTools.HATCH1B;
            case CARGO4:
                return GameTools.HATCH1B;
            case HATCH1B:
                return GameTools.CARGO2;
            case HATCH2B:
                return GameTools.CARGO2;
            case HATCH3B:
                return GameTools.CARGO2;
            default:
                return currentState;
        }
    }

    public void flower(){
        GameTools nextState = flowerState();
        Robot.elevator.setTargetHeight(nextState.elevator);
        Robot.switcher.moveSwitch(nextState.switcher);
        Robot.flower.setFlowerIO(nextState.io);
        Robot.flower.setFlowerBud(nextState.bud);
        currentState = nextState;
        System.out.println(currentState);
    }

    GameTools flowerState(){
        switch(currentState) {
            case HATCH1B:
                return GameTools.HATCH1F;
            case HATCH2B:
                return GameTools.HATCH2F;
            case HATCH3B:
                return GameTools.HATCH3F;
            case HATCH1F:
                return GameTools.HATCH1B;
            case HATCH2F:
                return GameTools.HATCH2B;
            case HATCH3F:
                return GameTools.HATCH3B;
            case AUTO_HATCHF:
                return GameTools.AUTO_HATCHB;
            case AUTO_HATCHB:
                return GameTools.AUTO_HATCHF;
            default:
                return currentState;
        }
    }

    public static enum GameTools {
        INITIAL(Height.GROUND, SwitcherState.FLOOR, FlowerBud.BUD, FlowerIO.IN), 
        INITIAL_AUTO(Height.LEVEL1H, SwitcherState.HATCH, FlowerBud.FLOWER, FlowerIO.IN), //Initial state for auto (we think)
        CARGO1(Height.GROUND, SwitcherState.CARGO, FlowerBud.BUD, FlowerIO.IN),
        CARGO2(Height.LEVEL1B, SwitcherState.CARGO, FlowerBud.BUD, FlowerIO.IN),
        CARGO3(Height.LEVEL2B, SwitcherState.CARGO, FlowerBud.BUD, FlowerIO.IN),
        CARGO4(Height.LEVEL3B, SwitcherState.REAR, FlowerBud.BUD, FlowerIO.IN),
        HATCH1B(Height.LEVEL1H, SwitcherState.HATCH, FlowerBud.BUD, FlowerIO.OUT),
        HATCH2B(Height.LEVEL2H, SwitcherState.HATCH, FlowerBud.BUD, FlowerIO.OUT),
        HATCH3B(Height.LEVEL3H, SwitcherState.HATCH, FlowerBud.BUD, FlowerIO.OUT),
        HATCH1F(Height.LEVEL1H, SwitcherState.HATCH, FlowerBud.FLOWER, FlowerIO.OUT),
        HATCH2F(Height.LEVEL2H, SwitcherState.HATCH, FlowerBud.FLOWER, FlowerIO.OUT),
        HATCH3F(Height.LEVEL3H, SwitcherState.HATCH, FlowerBud.FLOWER, FlowerIO.OUT),
        CARGOS(Height.LEVELSB, SwitcherState.CARGO, FlowerBud.BUD, FlowerIO.IN),
        CARGOTRANSIT(Height.TRANSIT, SwitcherState.CARGO, FlowerBud.BUD, FlowerIO.IN),
        AUTO_HATCHF(Height.LEVELAH, SwitcherState.HATCH, FlowerBud.FLOWER, FlowerIO.OUT),
        AUTO_HATCHB(Height.LEVELAH, SwitcherState.HATCH, FlowerBud.BUD, FlowerIO.OUT);

        public Height elevator;
        public SwitcherState switcher;
        public FlowerBud bud;
        public FlowerIO io;
        private GameTools(Height elevator, SwitcherState switcher, FlowerBud bud, FlowerIO io) {
            this.elevator = elevator;
            this.switcher = switcher;
            this.bud = bud;
            this.io = io;
        }
    }
}
