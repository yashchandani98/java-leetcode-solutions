package Combinations;

import java.util.*;
import java.util.stream.Collectors;

/*
* Topics/Algo/Data Structures used here are:
* - Stack
* - Simulation
* - Sorting
* - minHeap
*
* */
public class Leetcode2751RobotCollisions {
    private static class Robot {
        private int idx;
        private int pos;
        private int health;
        private char dir ;
        public Robot(int idx, int pos, int health, char dir){
            this.idx = idx;
            this.pos = pos;
            this.health = health;
            this.dir = dir;
        }
    }
    private char left = 'L';
    private char right = 'R';
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions){
        PriorityQueue<Robot> pq = new PriorityQueue<Robot>((Robot a, Robot b)->a.pos-b.pos);
        Stack<Robot> robots = new Stack<Robot>();
        int idx = 0;
        for(int pos: positions){
            pq.add(new Robot(idx,pos,healths[idx], directions.charAt(idx++)));
        }

        while(!pq.isEmpty()){
            Robot rbt = pq.poll();
            if(!robots.isEmpty()){
                if((robots.peek().dir == right && rbt.dir == left && robots.peek().pos < rbt.pos) || (robots.peek().dir == left && rbt.dir == right && robots.peek().pos > rbt.pos)){
                    if(robots.peek().health == rbt.health){
                        robots.pop();
                    } else if(robots.peek().health < rbt.health){
                        robots.pop();
                        rbt.health-=1;
                        robots.push(rbt);
                    } else{
                        robots.peek().health-=1;
                    }
                } else {
                    robots.push(rbt);
                }
            } else{
                robots.push(rbt);
            }
            checkIfAnyRobotCollision(robots);
        }
        List<Robot> ls = new ArrayList<Robot>(robots);
        Collections.sort(ls, (Robot a, Robot b)->a.idx-b.idx);
        List<Integer> result = new ArrayList<>(ls.stream().map(robot->robot.health).collect(Collectors.toList()));
        return result;
    }

    private void checkIfAnyRobotCollision(Stack<Robot> robots){
        if(!robots.isEmpty()){
            Robot top = robots.pop();
            boolean addBackTop = true;
            while(!robots.isEmpty() && (top.dir == left && robots.peek().dir == right && top.pos>robots.peek().pos)){
                System.out.println(top.health);
                if(top.health < robots.peek().health){
                    robots.peek().health-=1;
                } else if(top.health > robots.peek().health){
                    robots.pop();
                    top.health-=1;
                    robots.push(top);
                } else{
                    robots.pop();
                }
                if(!robots.isEmpty()){
                    top = robots.pop();
                    addBackTop = true;
                } else{
                    addBackTop = false;
                }
            }
            if(addBackTop)
                robots.push(top);
        }
    }
}
