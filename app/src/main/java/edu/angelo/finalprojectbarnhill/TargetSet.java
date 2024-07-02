package edu.angelo.finalprojectbarnhill;

import java.util.ArrayList;
import java.util.List;

/**
 * provides a set of targets and the functions needed to maintain the set.
 * @Author   Mason Barnhill
 */
public class TargetSet {

    /**
     * All of the targets to maintain
     */
    List<Target>  targets;/**
     /**
     * creates an instance of a TargetSet object
     * @param numTargets: the number of targets in the set to be built
     */
    public TargetSet(int numTargets) {
        targets = new ArrayList<Target>();
        for (int i = 0; i < 10; i += 1) {
            Target temp = new Target();
            targets.add(temp);
        }

    }

    /*
     * creates an instance of a TargetSet object with a default numTargets value of 10
     */
    public TargetSet() {
        this(10);
    }

    /**
     * advances each target according to their own unique velocities
     */
    public void advance() {
        for (Target t : targets) {
            t.advance();
        }
    }

}
