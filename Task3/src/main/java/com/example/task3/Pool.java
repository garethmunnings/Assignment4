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

    public void removeKitten(Kitten kitten) {
        kittenPool.remove(kitten);
    }
    public void removeCat(Cat cat) {
        catPool.remove(cat);
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
        kitten.setOutOfPlay();
        kittenPool.add(kitten);
    }
    public void addCat(Cat cat){
        cat.setOutOfPlay();
        catPool.add(cat);
    }

    public void addFeline(Feline feline){
        if(feline instanceof Kitten)
            addKitten((Kitten)feline);
        else
            addCat((Cat)feline);
    }

//    public void writePoolToConsole(){
//        int i = 0;
//        int n = 0;
//        for(Kitten kitten : kittenPool)
//        {
//            System.out.println(kitten + " " + i);
//            n = kitten.getPlayer();
//            i++;
//        }
//        System.out.println("Player " + n);
//        System.out.println("-----------");
//    }
}
