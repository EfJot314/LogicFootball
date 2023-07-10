package org.football;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private final List<int[]> path;

    public Path(){
        this.path = new ArrayList<>();
    }

    public void addToPath(int[] position){
        this.path.add(position);
    }


}
