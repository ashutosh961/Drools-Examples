package org.drools.examples.Example;

public  class Pair
{
    public  int x;
    public  int y;

    public Pair(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    public void printPair()
    {
        System.out.println("Pairs1:"+ "(" + this.x + "," + this.y + ")" + "\t");
    }

    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }
}