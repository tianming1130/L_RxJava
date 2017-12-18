package com.zknu.l_rxjava;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Button btnRxJava;
    private Subscriber<String> subscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRxJava = (Button) findViewById(R.id.btn_rxjava);

        btnRxJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Observable<String> observable = Observable.create(new Observable
                        .OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        int i=0;
                        while (true){
                            subscriber.onNext(String.valueOf(++i));
                            SystemClock.sleep(1000);
                        }
                    }
                });
                //订阅
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
            }
        });


        // 创建subscriber
        subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        };

    }
}
