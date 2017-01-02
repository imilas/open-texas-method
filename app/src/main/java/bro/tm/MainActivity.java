package bro.tm;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this,TimerActivity.class);
        startActivity(intent);
    }



}
