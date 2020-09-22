package com.pgj.retrofitx.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.pgj.retrofitx.MyApplication;
import com.pgj.retrofitx.net.callback.IRequest;
import com.pgj.retrofitx.net.callback.ISuccess;
import com.pgj.retrofitx.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;
import okhttp3.ResponseBody;

/**
 * Created by pgj on 2020/9/22
 **/
public class SaveFileTask extends AsyncTask<Object,Void, File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest request, ISuccess success) {
        REQUEST = request;
        SUCCESS = success;
    }


    @Override
    protected File doInBackground(Object... params) {
        String downloadDir= (String) params[0];
        String extension= (String) params[1];
        String name=(String)params[2];
        final ResponseBody body= (ResponseBody) params[3];
        final InputStream is=body.byteStream();
        if(downloadDir==null||downloadDir.equals("")){
            downloadDir="down_loads";
        }
        if(extension==null||extension.equals("")){
            extension="";
        }
        if(name==null){
            return FileUtil.writeToDisk(is,downloadDir,extension.toUpperCase(),extension);
        }else{
            return FileUtil.writeToDisk(is,downloadDir,name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if(SUCCESS!=null){
            SUCCESS.onSuccess(file.getAbsolutePath());
        }
        if(REQUEST!=null){
            REQUEST.onRequestEnd();
        }
    }

    private void autoInstallApk(File file){
        if(FileUtil.getExtension(file.getPath()).equals("apk")){
            final Intent intent=new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            MyApplication.getContext().startActivity(intent);
        }
    }
}
