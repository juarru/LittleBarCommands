package com.juan_arillo.littlebarcommands.utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.view.ViewGroup;

import com.juan_arillo.littlebarcommands.R;
import com.juan_arillo.littlebarcommands.models.Dish;
import com.juan_arillo.littlebarcommands.models.Menu;

import java.lang.ref.WeakReference;
import java.util.LinkedList;

/**
 * Created by juan_arillo on 11/12/16.
 */

public class BackgroundMenuDownloading extends AsyncTask<Void, Void, LinkedList<Dish>> {

    private ProgressDialog mProgressDialog;
    private ViewGroup mContainer;
    private WeakReference<Activity> mActivity;


    public BackgroundMenuDownloading(ViewGroup container, Activity activity) {
        super();
        mContainer = container;
        mActivity = new WeakReference<>(activity);
    }

    @Override
    protected void onPreExecute() {
        mContainer.setAlpha(0.0f);

        mProgressDialog = new ProgressDialog(mActivity.get());
        mProgressDialog.setTitle(mActivity.get().getString(R.string.download));
        mProgressDialog.show();
    }

    @Override
    protected LinkedList<Dish> doInBackground(Void... params) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Menu.downloadMenu();
    }

    @Override
    protected void onPostExecute(LinkedList<Dish> courses) {
        try {
            mProgressDialog.dismiss();
        } catch (IllegalArgumentException ex) {
        }
        if (courses == null) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mActivity.get());
            alertDialog.setTitle(R.string.downloadError);
            alertDialog.setMessage(R.string.downloadErrorText);
            alertDialog.setCancelable(false);
            alertDialog.setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mActivity.get().finish();
                }
            });
            alertDialog.show();
        }
        else if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mContainer.animate()
                    .setDuration(2)
                    .alpha(1.0f);
        }
    }

}
