package com.lgshuo.demo;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private SearchDialogFragment mSdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final View fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSdf = new SearchDialogFragment();
                final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                mSdf.show(ft, "df");
                mSdf.setOnDismissListener(new SearchDialogFragment.OnStatusChangedListener() {
                    @Override
                    public void onDismiss() {
                        fab.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onShow() {
                        fab.setVisibility(View.GONE);
                    }
                });
            }
        });

    }
}
