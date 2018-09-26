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
    private int length;
    private int height;
    private int width;
    private int mass;
    private int crewNum;
    private int passNum;
    private int maxCap;
    private int carNum;
    private int doorNum;
    private int accelLimit;
    private int deccelLimit;
    private int speed;
    private int authority;
    private int block;
    private int target; //destination

    //for CTC
    public Train(String line, int number, int speed, int authority, int block, int target) {
        this.line = line;
        this.number = number;
        this.speed = speed;
        this.authority = authority;
        this.block = block;
        this.target = target;
    }

    //all of them
    public Train(String line, int number, int length, int height, int width, int mass, int crewNum, int passNum, int maxCap, int carNum, int doorNum, int accelLimit, int deccelLimit, int speed, int authority, int block, int target) {
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
        this.speed = speed;
        this.authority = authority;
        this.block = block;
        this.target = target;
    }

    //for train model and train controller
    public Train(String line, int number, int length, int height, int width, int mass, int crewNum, int passNum, int maxCap, int carNum, int doorNum, int accelLimit, int deccelLimit) {
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getMass() {
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

    public int getAccelLimit() {
        return accelLimit;
    }

    public void setAccelLimit(int accelLimit) {
        this.accelLimit = accelLimit;
    }

    public int getDeccelLimit() {
        return deccelLimit;
    }

    public void setDeccelLimit(int deccelLimit) {
        this.deccelLimit = deccelLimit;
    }

    public int getSpeed() {
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
    
    
    
    
}
