/*
 * Copyright (C) 2014 The Android Open Source Project.
 *
 *        yinglovezhuzhu@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.opensource.widget.horizontalgridview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.opensource.widget.HorizontalGridView;
import com.opensource.widget.OnChildSelectedListener;
import com.opensource.widget.RecyclerView;


public class MainActivity extends Activity {

    private HorizontalGridView mHorizontalGridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHorizontalGridView = (HorizontalGridView) findViewById(R.id.hgv);
        mHorizontalGridView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                ImageView iv = new ImageView(MainActivity.this);
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                iv.setImageResource(R.drawable.ic_launcher);
                final RecyclerView.ViewHolder viewHolder = new ViewHolder(iv);
                //TODO 把Item的点击事件在这里做，调用View.setOnClickListener(View.OnClickListener)，然后用viewHoder.getPosition()来获取点击的位置
                //以同样的方法可以实现OnLongClickListener等
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mHorizontalGridView.setSelectedPosition(viewHolder.getPosition());
                    }
                });
                return viewHolder;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
                //TODO 把一些View的相关改变在这里实现
            }

            @Override
            public int getItemCount() {
                return 50;
            }
        });
        mHorizontalGridView.setOnChildSelectedListener(new OnChildSelectedListener() {
            @Override
            public void onChildSelected(ViewGroup parent, View view, int position, long id) {
                Log.i("MainActivity", "Item " + position + " was selected");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
