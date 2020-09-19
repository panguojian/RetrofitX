# RetrofitX
基于Retrofit实现的通用网络请求框架
# AVLoadingIndicatorView
该项目集成[AVLoadingIndicatorView](https://github.com/81813780/AVLoadingIndicatorView)框架，便于呈现网络加载的状态  
  
![image](https://github.com/81813780/AVLoadingIndicatorView/blob/master/screenshots/avi.gif)
# 使用方法
    findViewById(R.id.btn_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestClient.builder()
                        .url("")
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                //Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                                System.out.println(response);
                            }
                        })
                        .request(new IRequest() {
                            @Override
                            public void onRequestStart() {
                                RestLoader.showLoading(MainActivity.this);
                            }

                            @Override
                            public void onRequestEnd() {
                                RestLoader.stopLoading();
                            }
                        })
                        .failure(new IFailure() {
                            @Override
                            public void onFailure(Throwable t) {
                                Toast.makeText(MainActivity.this,"请求失败："+t.getMessage().toString(),Toast.LENGTH_LONG).show();
                            }
                        })
                        .error(new IError() {
                            @Override
                            public void onError(int code, String msg) {
                                Toast.makeText(MainActivity.this,"请求错误:"+code+" "+msg,Toast.LENGTH_LONG).show();
                            }
                        })
                        .build()
                        .get();
            }
        });
