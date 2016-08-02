package com.example.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;
import android.content.Intent;







public class MainActivity extends Activity {

    private PersonDB mPersonDB;
    private DishDB mDishDB;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPersonDB = new PersonDB(this);
        if(!mPersonDB.check("chefaaron","aaron")){

            mPersonDB.insert("chefaaron", "aaron","chef");
            mPersonDB.insert("waiteraaron", "aaron","waiter");
            mPersonDB.insert("chefnanfang","ricky","chef");
            mPersonDB.insert("waiterdennis", "dennis","waiter");
            mPersonDB.insert("waiterjerry", "jerry","waiter");
            mPersonDB.insert("manageraaron", "aaron","manager");

            mDishDB = new DishDB(this);
            mDishDB.insertDish(R.drawable.chashao, "Cha Shao", "10");

            mDishDB.insertDish(R.drawable.fengzhao, "Feng Zhao", "30");
            mDishDB.insertDish(R.drawable.shaoya, "Shao Ya", "35");


            //mDishDB.insertDish(R.drawable.barbecuedpork, "Barbecue Prok", "38");
            mDishDB.insertDish(R.drawable.barleyandmelonstewduck, "Barley Duck", "48");
            mDishDB.insertDish(R.drawable.beansfriedpork, "Beans Fried Pork", "38");
/*            mDishDB.insertDish(R.drawable.blackbeansaucefriedscarab, "Black Bean Sauce Fried Scarab", "48");
            mDishDB.insertDish(R.drawable.boiledprawns, "Boiled prawns", "38");
            mDishDB.insertDish(R.drawable.cabbagesalad, "Cabbage Salad", "18");

            mDishDB.insertDish(R.drawable.cashewcelerylily, "Celery Lily", "22");
            mDishDB.insertDish(R.drawable.chrysanthemumfish, "Chrysanthemum Fish", "68");
            mDishDB.insertDish(R.drawable.coldfernrootpowder, "Cold Fern Root Powder", "15");
            mDishDB.insertDish(R.drawable.cornmushroomribssoup, "Mushroom Soup", "38");
            mDishDB.insertDish(R.drawable.crabvermicellipot, "Crab Vermicelli Pot", "68");
            mDishDB.insertDish(R.drawable.crispypork, "Crispy Pork", "48");
            mDishDB.insertDish(R.drawable.friedstuffedpeppers, "Fried Peppers", "28");
            mDishDB.insertDish(R.drawable.fruithasma, "Fruit hasma", "20");
            //mDishDB.insertDish(R.drawable.gastrodiatuberandfishheadsoup, "Gastrodia Tuber and Fish Head Soup", "68");
            mDishDB.insertDish(R.drawable.goldenbeancurd, "Golden Bean Curd", "28");

*/




        }

        Intent intent = new Intent(MainActivity.this, enterActivity.class);
        startActivity(intent);
        finish();
    }



}







