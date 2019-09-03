package com.example.forkinvestor;

import java.util.Comparator;

class ListComp implements Comparator<UserScore> {
    @Override
    public int compare(UserScore uc1, UserScore uc2){
        if(Integer.parseInt(uc1.getUserScore()) < Integer.parseInt(uc2.getUserScore())){
            return 1;
        }else{
            return -1;
        }
    }
}
