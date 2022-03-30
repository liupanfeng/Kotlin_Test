package com.test.kotlin_test.optimaze;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.test.kotlin_test.R;

import java.lang.ref.WeakReference;

/**
 * Handler 防止内存泄露 的正确使用
 */
public class HandlerActivity extends AppCompatActivity {

    private final InnerHandler mInnerHandler = new InnerHandler(this);

    private static class InnerHandler extends Handler{

        private final WeakReference<HandlerActivity> weakReference;

        private InnerHandler(HandlerActivity handlerActivity) {
            this.weakReference = new WeakReference<HandlerActivity>(handlerActivity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler2);

    }
}