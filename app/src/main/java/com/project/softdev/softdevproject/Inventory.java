package com.project.softdev.softdevproject;

import android.support.v4.util.Pair;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;


public class Inventory extends ArrayList<Product> implements Serializable {

    public final static String SAMPLE_DESCRIPTION = "Lorem ipsum dolor sit amet, consectetur\n" +
            "        adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore\n" +
            "        magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco\n" +
            "        laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor\n" +
            "        in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla\n" +
            "        pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa\n" +
            "        qui officia deserunt mollit anim id est laborum.";


    Inventory(){}

    public void saveMaster(File file){
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMaster(File file){
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.clear();
            this.addAll((Inventory) ois.readObject());
            return;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (OptionalDataException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //no master inventory exists, populate with fake items
        final String username = "default";
        this.add(new Product(R.drawable.advil_200mg,
                "Advil", 26, 23, 200, SAMPLE_DESCRIPTION, username));
        this.add(new Product(R.drawable.bioflu_500mg,
                "Bioflu", 17, 14, 200, SAMPLE_DESCRIPTION, username));
        this.add(new Product(R.drawable.biogesic_500mg,
                "Biogesic", 15, 12, 200, SAMPLE_DESCRIPTION, username));
        this.add(new Product(R.drawable.kremils,
                "Kremil-S", 14, 10, 200, SAMPLE_DESCRIPTION, username));
        this.add(new Product(R.drawable.panadol_500mg,
                "Panadol", 42, 36, 200, SAMPLE_DESCRIPTION, username));
        this.add(new Product(R.drawable.neozep_500mg,
                "Neozep", 12, 7, 200, SAMPLE_DESCRIPTION, username));
        this.add(new Product(R.drawable.diatabs_2mg,
                "Diatabs", 17, 12, 200, SAMPLE_DESCRIPTION, username));
        this.add(new Product(R.drawable.imodium_2mg,
                "Imodium", 23, 19, 200, SAMPLE_DESCRIPTION, username));
        this.add(new Product(R.drawable.alaxan_200mg,
                "Alaxan_FR", 27, 25, 200, SAMPLE_DESCRIPTION, username));
        this.add(new Product(R.drawable.ascof_120ml,
                "Ascof", 17, 13, 200, SAMPLE_DESCRIPTION, username));
        this.add(new Product(R.drawable.buscopan_cramps,
                "Buscopan-cramps", 32, 27, 200, SAMPLE_DESCRIPTION, username));
        this.add(new Product(R.drawable.dolfenal_250mg,
                "Dolfenal", 24, 19, 200, SAMPLE_DESCRIPTION, username));
        this.add(new Product(R.drawable.medicol_200mg,
                "Medicol", 42, 38, 200, SAMPLE_DESCRIPTION, username));
        this.add(new Product(R.drawable.decolgen_forte,
                "Decolgen-forte", 32, 25, 200, SAMPLE_DESCRIPTION, username));
        this.add(new Product(R.drawable.plemex_120ml,
                "Plemex", 21, 19, 200, SAMPLE_DESCRIPTION, username));
        this.add(new Product(R.drawable.robitussin,
                "Robitussin", 79, 67, 200, SAMPLE_DESCRIPTION, username));
        this.add(new Product(R.drawable.solmux_500mg,
                "Solmux", 34, 28, 200, SAMPLE_DESCRIPTION, username));

    }


//master functions
    public Product getCloneByName(String name){
        for(Product p : this){
            if(p.getName().equals(name)) {
                return (Product)p.singleClone();
            }
        }
        return null;
    }
    public int getQtyByName(String name){
        for(Product p : this){
            if(name.equals(p.getName())) {
                return p.getQty();
            }
        }
        return 0;
    }
    public void deleteProduct(String name){
        for(Product p : this){
            if(name.equals(p.getName())) {
                remove(p);
                return;
            }
        }
    }
    public void checkout(ArrayList<Pair<String,Integer>> items){
        for(Pair pair : items){
            for(Product p : this){
                if(p.getName().equals(pair.first)) {
                    p.setQty(p.getQty() - (Integer)pair.second);
                }
            }
        }

    }
    public void updateQty(String name, int newQuantity){
        for(Product p : this){
            if(p.getName().equals(name)) {
                p.setQty(newQuantity);
            }
        }
    }
}