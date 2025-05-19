package com.example.task3;

public class Pool {
    private Feline[] pool;
    public Pool(int playerNum) {
        pool = new Feline[]{new Kitten(playerNum), new Kitten(playerNum),new Kitten(playerNum),new Kitten(playerNum),new Kitten(playerNum),new Kitten(playerNum),new Kitten(playerNum),new Kitten(playerNum), new Cat(playerNum), new Cat(playerNum), new Cat(playerNum), new Cat(playerNum), new Cat(playerNum), new Cat(playerNum), new Cat(playerNum), new Cat(playerNum) };
    }

    //public Feline[] getPool(){return pool;}

    public Kitten getNextKitten(){
        for (int i = 0; i < pool.length; i++) {
            if(pool[i] instanceof Kitten && !pool[i].getInPlay())
            {
                pool[i].setInPlay();
                return (Kitten)pool[i];
            }
        }
        return null;
    }
    public Cat getNextCat(){
        for (int i = 0; i < pool.length; i++) {
            if(pool[i] instanceof Cat && !pool[i].getInPlay())
            {
                pool[i].setInPlay();
                return (Cat)pool[i];
            }
        }
        return null;
    }

    public boolean hasKittenAvailable(){
        for (int i = 0; i < pool.length; i++) {
            if(pool[i] instanceof Kitten && !pool[i].getInPlay())
            {
                return true;
            }
        }
        return false;
    }
    public boolean hasCatAvailable(){
        for (int i = 0; i < pool.length; i++) {
            if(pool[i] instanceof Cat && !pool[i].getInPlay())
            {
                return true;
            }
        }
        return false;
    }
}
