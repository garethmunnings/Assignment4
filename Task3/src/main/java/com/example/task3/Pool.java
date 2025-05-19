package com.example.task3;

import java.util.ArrayList;

public class Pool {
    private ArrayList<Kitten> kittenPool;
    private ArrayList<Cat> catPool;

    public Pool(int playerNum) {
        kittenPool = new ArrayList<Kitten>();
        for (int i = 0; i < 8; i++) {
            kittenPool.add(new Kitten(playerNum));
        }
        catPool = new ArrayList<>();
    }

    public Kitten getNextKitten(){
        Kitten kitten = kittenPool.getFirst();
        if(!kitten.getInPlay())
        {
            kitten.setInPlay();
            kittenPool.remove(kitten);
            return kitten;
        }
        return null;
    }

    public Cat getNextCat(){
        Cat cat = catPool.getFirst();
        if(!cat.getInPlay())
        {
            cat.setInPlay();
            catPool.remove(cat);
            return cat;
        }
        return null;
    }

    public boolean hasKittenAvailable(){
        if(kittenPool.isEmpty()) return false;
        return true;
    }
    public boolean hasCatAvailable(){
        if(catPool.isEmpty()) return false;
        return true;
    }

    public int getNumberOfKittensAvailable(){return kittenPool.size();}
    public int getNumberOfCatsAvailable(){return catPool.size();}

    public ArrayList<Kitten> getKittenPool(){return kittenPool;}
    public ArrayList<Cat> getCatPool(){return catPool;}

    public void addKitten(Kitten kitten){
        kittenPool.add(kitten);
    }
    public void addCat(Cat cat){
        catPool.add(cat);
    }
}
