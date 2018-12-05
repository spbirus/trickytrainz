/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication;

/**
 *
 * @author burri
 */
public class Train {
    
    private final double M_TO_FT = 3.281; 
    private final double M_TO_MI = 0.0006214;
    private final double N_TO_FTLBS = 0.22481;
    private final double GRAVITY = 32.17405; //ft/s^2
    //private final double SEC_TO_HR = 3600;
    private final double FTPS_TO_MPH = 0.681818;
    private final double MPH_TO_MiPS = 0.000277778; //miles per hr to miles to miles per second
    private final double serviceBrakeDecel = 1.2 * 3.2808399; //ft/s^2
    private final double emergencyBrakeDecel = 2.73 * 3.2808399; //ft/s^2
    private double multiplier = 1;
    private final double origDeltaT = 0.017;
    private double deltaT = 0.017; //deltaT is a change in time that helps us not miss the beacon   
    private final int seatCap = 74;
    private final int standCap = 148;
    private final int maxCap = seatCap + standCap;
    private String line; //red or green line
    private int number; //train id number
    private double length = 32.2*M_TO_FT; //ft
    private double height = 3.42*M_TO_FT; //ft
    private double width = 2.65*M_TO_FT; //ft
    private int crewNum = 1;
    private int passNum = 0;
    private int carNum = 1;
    private int doorNum = 8;
    private double accelLimit =  0.5 * 3.2808399;  //ft/s
    private double deccelLimit = 0; //ft/s
    private double speed;       //
    private double authority; // total distance
    private int block = 0;
    private int previousBlock = 0;
    private int target; //destination block
    private int temperature;
    
    private int maxCapacity = 148 + 74;
    private double trainMass = 81800; //lbs
    private double totalMass; //mass of car and people
    private double velActual = 0; //thing to return to train controller
    private double trainAccel = 0; //accelteration to return to train controller in ft/s^2
    private double force;
    
    private int numberOfWheels = 12; //will probably need to change
    private final double coefficientOfFriction = 0.00035; //from  https://en.wikipedia.org/wiki/Rolling_resistance#Rolling_resistance_coefficient_examples
    private int direction; // 0 and 1
    private int brakes = 0;

    //for CTC
    public Train(String line, int number, double speed, int target, double authority) {
        this.line = line;
        this.number = number;
        this.speed = speed;
        this.target = target;
        this.authority = authority;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
        deltaT = origDeltaT * multiplier;
    }

    public int getBrakes() {
        return brakes;
    }

    public void setBrakes(int brakes) {
        this.brakes = brakes;
    }

    public int getPreviousBlock() {
        return previousBlock;
    }

    public void setPreviousBlock(int previousBlock) {
        this.previousBlock = previousBlock;
    }

    public double getM_TO_MI() {
        return M_TO_MI;
    }

    public double getN_TO_FTLBS() {
        return N_TO_FTLBS;
    }

    public double getGRAVITY() {
        return GRAVITY;
    }

    public double getFTPS_TO_MPH() {
        return FTPS_TO_MPH;
    }

    public double getMPH_TO_MiPS() {
        return MPH_TO_MiPS;
    }

    public double getServiceBrakeDecel() {
        return serviceBrakeDecel;
    }

    public double getEmergencyBrakeDecel() {
        return emergencyBrakeDecel;
    }

    public double getDeltaT() {
        return deltaT;
    }

    public double getCoefficientOfFriction() {
        return coefficientOfFriction;
    }

    public double getTrainMass() {
        return trainMass;
    }

    public void setTrainMass(double trainMass) {
        this.trainMass = trainMass;
    }

    public double getTotalMass() {
        return totalMass;
    }

    public void setTotalMass(double totalMass) {
        this.totalMass = totalMass;
    }

    public double getVelActual() {
        return velActual;
    }

    public void setVelActual(double velActual) {
        this.velActual = velActual;
    }

    public double getForce() {
        return force;
    }

    public void setForce(double force) {
        this.force = force;
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public void setNumberOfWheels(int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
    
    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    public double getDeccelLimit() {
        return deccelLimit;
    }

    public void setDeccelLimit(double deccelLimit) {
        this.deccelLimit = deccelLimit;
    }

    public int getCrewNum() {
        return crewNum;
    }

    public void setCrewNum(int crewNum) {
        this.crewNum = crewNum;
    }

    public int getPassNum() {
        return passNum;
    }

    public void setPassNum(int passNum) {
        this.passNum = passNum;
    }

    public int getMaxCap() {
        return maxCap;
    }

    public int getCarNum() {
        return carNum;
    }

    public void setCarNum(int carNum) {
        this.carNum = carNum;
    }

    public int getDoorNum() {
        return doorNum;
    }

    public void setDoorNum(int doorNum) {
        this.doorNum = doorNum;
    }

    public double getAccelLimit() {
        return accelLimit;
    }

    public void setAccelLimit(int accelLimit) {
        this.accelLimit = accelLimit;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getAuthority() {
        return authority;
    }

    public void setAuthority(double authority) {
        this.authority = authority;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.previousBlock = this.block;
        this.block = block;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
    
    
    public double calculateVelocity(double power, double currentSpeed,double grade,int brake,double speedLimit,int passengers){
        
        
        totalMass = trainMass + 150*passengers; //might need to add 1 for the operator
        
        /*
                                                                                   F             a         vel
        (1000)*power(.0006214)/(currentSpeed) X (.22481)/(currentSpeed*.000277778) X 1/totalMass X 1*deltaT*multiplier
                      to mi                     to ft lbs            to mi per sec
        
        
        */
        
//        System.out.println("current speed: " + currentSpeed);

        //FORCE
        if(currentSpeed == 0){
            //train is stopped therefore can't divide by 0
//            System.out.println("speed is 0");
            force = (power*1000)*(N_TO_FTLBS)/1; //convert from kW to Watts
        }else{
            force = (power*1000)*(N_TO_FTLBS/currentSpeed);
        }
        
        //to downward force
        double slope = Math.atan2(grade, 100); //given in radians
        double angle = Math.toDegrees(slope);
//        System.out.println("Slope: " + slope + " Angle: " + angle);
        
        //get all forces acting in the x plane that will counteract the force in the positive direction
        double normalForce = (totalMass/numberOfWheels) * GRAVITY * Math.sin(angle*Math.PI/180);
        double downwardForce = (totalMass/numberOfWheels) * GRAVITY * Math.cos(angle*Math.PI/180);
        
        //frictional force
        double frictionalForce = (coefficientOfFriction*downwardForce) + normalForce;
//        System.out.println("Frictionalforce: " +frictionalForce + " ftlbs");
//        System.out.println("Friction: " + force);
        
        force = force - frictionalForce;
        
//        System.out.println("force: " + force + " ftlbs");
        
        trainAccel = force/totalMass;
        
        
        //ACCEL
        //check to make sure train does not exceed max
        if(trainAccel >= accelLimit){
            trainAccel = accelLimit;
        }
        
        //brakes = 3 emergency brake is pulled
        //brakes = 1 service brake is pulled
        if(brakes == 3){
            trainAccel -= emergencyBrakeDecel;
        }
        if(brakes == 1){
            trainAccel -= serviceBrakeDecel;
        }
        
//        System.out.println("accel: " + trainAccel +" ft/s^2");
        
        //VELOCITY
        velActual = currentSpeed + (trainAccel * deltaT)*FTPS_TO_MPH; //deltaT is a change in time that helps us not miss the beacon
        
        //prevent it from going backwards
        if(velActual < 0){
            velActual = 0;
        }
        
        if(velActual > speedLimit){
            velActual = speedLimit;
        }
        
//        System.out.println("velocity: " + velActual +" MPH");

        
        return velActual;
    }
    
    
    
}
