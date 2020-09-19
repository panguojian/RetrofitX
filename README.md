# RetrofitX
基于Retrofit实现的通用网络请求框架
# AVLoadingIndicatorView
该项目集成[AVLoadingIndicatorView](https://github.com/81813780/AVLoadingIndicatorView)框架，便于呈现网络加载的状态  
  
![image](https://github.com/81813780/AVLoadingIndicatorView/blob/master/screenshots/avi.gif)
# 简单使用事例
    //省略部分代码
    findViewById(R.id.btn_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestClient.builder()
                        .url("")
                        .success(new ISuccess() {
                            @Override
                            public void onSuccess(String response) {
                                //TODO 请求成功回调到这里
                                //Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                                System.out.println(response);
                            }
                        })
                        .request(new IRequest() {
                            @Override
                            public void onRequestStart() {
                                //TODO 请求开始回调到这里
                                RestLoader.showLoading(MainActivity.this);
                            }

                            @Override
                            public void onRequestEnd() {
                                //TODO 请求结束回调到这里
                                RestLoader.stopLoading();
                            }
                        })
                        .failure(new IFailure() {
                            @Override
                            public void onFailure(Throwable t) {
                                //TODO 对请求失败做出响应
                            }
                        })
                        .error(new IError() {
                            @Override
                            public void onError(int code, String msg) {
                                //TODO 对请求错误做出响应
                            }
                        })
                        .build()
                        .get();
            }
        });
        //省略部分代码
