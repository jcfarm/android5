package com.example.kai.qfarm;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.VideoView;

public class VideoViewDemo extends Activity {
    /** Called when the activity is first created.
     * RTSP（Real Time Streaming Protocol），RFC2326，实时流传输协议，是TCP/IP协议体系中的一个应用层协议。
     * 该协议定义了一对多应用程序如何有效地通过IP网络传送多媒体数据。RTSP在体系结构上位于RTP和RTCP之上，它使用TCP或UDP完成数据传输。
     * TCP（Transmission Control Protocol 传输控制协议）是一种面向连接的、可靠的、基于字节流的传输层通信协议
     * UDP协议全称是用户数据报协议[1]  ，在网络中它与TCP协议一样用于处理数据包，是一种无连接的协议。在第四层——传输层
     * HTTP与RTSP相比，HTTP请求由客户机发出，服务器作出响应；使用RTSP时，客户机和服务器都可以发出请求，即RTSP可以是双向的。
     * RTSP是用来控制声音或影像的多媒体串流协议，并允许同时多个串流需求控制
     * */
// 为什么会顶替上一个项目
    //定义button和各种text和view
    Button playButton ;
    VideoView videoView ;
    EditText rtspUrl ;
    RadioButton radioStream;
    RadioButton radioFile;

//在o的Create函数中根据ID从布局中获取控件，当playButton点击时根据RadioButton的选择（取网络流还是播放本地文件）调用不同的函数。
// （主要是用到了Android原生的VideoView，而这个控件播放网络流使用 videoView.setVideoURI()函数，播放本地文件使用setVideoPath()函数）。
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jian_main);

        rtspUrl = (EditText)this.findViewById(R.id.url);
        playButton = (Button)this.findViewById(R.id.start_play);
        radioStream = (RadioButton)this.findViewById(R.id.radioButtonStream);
        radioFile = (RadioButton)this.findViewById(R.id.radioButtonFile);

        playButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                if (radioStream.isChecked()) {
                    PlayRtspStream(rtspUrl.getEditableText().toString());
                }
                else if (radioFile.isChecked()){
                    PlayLocalFile(rtspUrl.getEditableText().toString());
                }
            }
        });

        videoView = (VideoView)this.findViewById(R.id.rtsp_player);

    }

    //播放视频流函数
    private void PlayRtspStream(String rtspUrl){
        videoView.setVideoURI(Uri.parse(rtspUrl));//调用视频
        videoView.requestFocus();//把输入焦点放入videoView控件上
        videoView.start();//开始播放
    }

    //播放本地文件函数
    private void PlayLocalFile(String filePath){
        videoView.setVideoPath(Environment.getExternalStorageDirectory() + "/" + filePath);
        videoView.requestFocus();
        videoView.start();
    }
}
