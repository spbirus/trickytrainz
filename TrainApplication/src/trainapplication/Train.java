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
    
    private String line; //red or green line
    private int number; //train id number
    private double length; //ft
    private double height; //ft
    private double width;  //ft
    private double mass;   //lbs
    private int crewNum;
    private int passNum;
    private int maxCap;
    private int carNum = 1;
    private int doorNum;
    private double accelLimit =  0.5 * 3.2808399;  //ft/s
    private double deccelLimit = 0; //ft/s
    private double speed;       //
    private int authority;
    private int block;
    private int target; //destination
    private int temperature;
    
    private double trainMass = 81800; //lbs
    private double totalMass; //mass of car and people
    private double velActual = 0; //thing to return to train controller
    private double force;
    private final double M_TO_MI = 0.0006214;
    private final double N_TO_FTLBS = 0.22481;
    private final double GRAVITY = 32.17405; //ft/s^2
    //private final double SEC_TO_HR = 3600;
    private final double FTPS_TO_MPH = 0.681818;
    private final double MPH_TO_MiPS = 0.000277778;
    private final double serviceBrakeDecel = 1.2 * 3.2808399; //ft/s^2
    private final double emergencyBrakeDecel = 2.73 * 3.2808399; //ft/s^2
    private final double deltaT = 0.01; //used to say how everything is updating?????
    private int numberOfWheels = 12; //will probably need to change
    private final double coefficientOfFriction = 0.00035; //from  https://en.wikipedia.org/wiki/Rolling_resistance#Rolling_resistance_coefficient_examples
    private int direction; // 0 and 1

    //for CTC
    public Train(String line, int number, double speed, int authority, int block, int target) {
        this.line = line;
        this.number = number;
        this.speed = speed;
        this.authority = authority;
        this.block = block;
        this.target = target;
    }

    //for train model and train controller
    public Train(String line, int number, double length, double height, double width, double mass, int crewNum, int passNum, int maxCap, int carNum, int doorNum, double accelLimit, double deccelLimit) {
        this.line = line;
        this.number = number;
        this.length = length;
        this.height = height;
        this.width = width;
        this.mass = mass;
        this.crewNum = crewNum;
        this.passNum = passNum;
        this.maxCap = maxCap;
        this.carNum = carNum;
        this.doorNum = doorNum;
        this.accelLimit = accelLimit;
        this.deccelLimit = deccelLimit;
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

    public double getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
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

    public void setMaxCap(int maxCap) {
        this.maxCap = maxCap;
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

    public double getDeccelLimit() {
        return deccelLimit;
    }

    public void setDeccelLimit(int deccelLimit) {
        this.deccelLimit = deccelLimit;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
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
                                                                                   F             a           vel
        (1000)*power(.0006214)/(currentSpeed) X (.22481)/(currentSpeed*.000277778) X 1/totalMass X 1/seconds
                      to mi                     to ft lbs      to mi per sec
        
        
        */
        
//        System.out.println("current speed: " + currentSpeed);

        //FORCE
        if(currentSpeed == 0){
            //train is stopped therefore can't divide by 0
            force = (power*1000*M_TO_MI)/1; //convert from kW to Watts
        }else{
            force = (power*1000*M_TO_MI)*(N_TO_FTLBS/(currentSpeed*MPH_TO_MiPS));
        }
        
        //to downward force
        double slope = Math.atan2(grade, 100); //given in radians
        double angle = Math.toDegrees(slope);
        
        //get all forces acting in the x plane that will counteract the force in the positive direction
        double normalForce = (totalMass/numberOfWheels) * GRAVITY * Math.sin(angle*Math.PI/180);
        double downwardForce = (totalMass/numberOfWheels) * GRAVITY * Math.cos(angle*Math.PI/180);
        
        //frictional force
        double frictionalForce = (coefficientOfFriction*downwardForce) + normalForce;
        System.out.println("Frictionalforce: " +frictionalForce + " ftlbs");
        
        force = force - frictionalForce;
        
        System.out.println("force: " + force + " ftlbs");
        
        double trainAccel = force/totalMass;
        
        
        //ACCEL
        //check to make sure train does not exceed max
        if(trainAccel >= accelLimit){
            trainAccel = accelLimit;
        }
        
        //brakes = 3 emergency brake is pulled
        //brakes = 1 service brake is pulled
        if(brake == 3){
            trainAccel -= emergencyBrakeDecel;
        }
        if(brake == 1){
            trainAccel -= serviceBrakeDecel;
        }
        
        System.out.println("accel: " + trainAccel +" ft/s^2");
        
        //VELOCITY
        //TODO: figure out what the seconds part is to get from accel to velocity
        velActual = currentSpeed + (trainAccel * deltaT)*FTPS_TO_MPH; //0.01 is a change in time that helps us not miss the beacon
        
        //prevent it from going backwards
        if(velActual < 0){
            velActual = 0;
        }
        
        System.out.println("velocity: " + velActual +" ft/s");

        
        return velActual;
    }
    
    
    
}
