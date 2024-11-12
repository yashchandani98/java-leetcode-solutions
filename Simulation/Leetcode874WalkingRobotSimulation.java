package Simulation;

import java.util.*;

public class Leetcode874WalkingRobotSimulation {
    Set<String> obstaclesSet = new HashSet<>();
    public int robotSim(int[] commands, int[][] obstacles) {
        for(int[] obs: obstacles) {
            String indice = obs[0] + "_" + obs[1];
            obstaclesSet.add(indice);
        }

        int i=0, j=0;
        String NORTH = "North";
        String SOUTH = "South";
        String EAST = "East";
        String WEST = "West";
        String currentPos = NORTH;
        int maxEuclideanDistance = Integer.MIN_VALUE;

        for(int command: commands) {
            if(command>0) {
                if(currentPos == NORTH) {
                    while(command>0){
                        command--;
                        if(!isObstacle(i,j+1)){
                            j++;
                        } else {
                            command = 0;
                        }

                    }
                }
                else if(currentPos == EAST) {
                    while(command>0){
                        command--;
                        if(!isObstacle(i+1,j)){
                            i++;
                        } else {
                            command = 0;
                        }
                    }
                }
                else if(currentPos == SOUTH) {
                    while(command>0){
                        command--;
                        if(!isObstacle(i,j-1)){
                            j--;
                        } else {
                            command = 0;
                        }
                    }
                }
                else if(currentPos == WEST) {
                    while(command>0){
                        command--;
                        if(!isObstacle(i-1,j)){
                            i--;
                        } else {
                            command = 0;
                        }
                    }
                }
            } else if(command == -1) {
                if(currentPos == NORTH) {
                    currentPos = EAST;
                } else if(currentPos == SOUTH) {
                    currentPos = WEST;
                } else if(currentPos == EAST) {
                    currentPos = SOUTH;
                } else if(currentPos == WEST) {
                    currentPos = NORTH;
                }
            }  else if(command == -2) {
                if(currentPos == NORTH) {
                    currentPos = WEST;
                } else if(currentPos == SOUTH) {
                    currentPos = EAST;
                } else if(currentPos == EAST) {
                    currentPos = NORTH;
                } else if(currentPos == WEST) {
                    currentPos = SOUTH;
                }
            }
            int distance = (int) (Math.pow(i, 2) + Math.pow(j, 2));
            maxEuclideanDistance = Math.max(maxEuclideanDistance, distance);
        }
        return maxEuclideanDistance;

    }

    private boolean isObstacle(int i, int j) {
        String indice = i + "_" + j;
        return obstaclesSet.contains(indice);
    }
}
