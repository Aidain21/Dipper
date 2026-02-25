package io.github.some_example_name;

public class Spikes extends TileFills{
    int damage=1;
    public Spikes(int d){
        fill="spikes";
        damage=d;
        canWalk=true;
    }

    public int getDamage(){
        return damage;
    }
}
