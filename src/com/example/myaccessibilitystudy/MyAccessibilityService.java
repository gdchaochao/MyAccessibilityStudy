package com.example.myaccessibilitystudy;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

public class MyAccessibilityService extends AccessibilityService {
	
    /** Speak. */
    private static final int MESSAGE_SPEAK = 1;

    /** Stop speaking. */
    private static final int MESSAGE_STOP_SPEAK = 2;

    /** Start the TTS service. */
    private static final int MESSAGE_START_TTS = 3;

    /** Stop the TTS service. */
    private static final int MESSAGE_SHUTDOWN_TTS = 4;

    /** Play an earcon. */
    private static final int MESSAGE_PLAY_EARCON = 5;

    /** Stop playing an earcon. */
    private static final int MESSAGE_STOP_PLAY_EARCON = 6;

    /** Vibrate a pattern. */
    private static final int MESSAGE_VIBRATE = 7;

    /** Stop vibrating. */
    private static final int MESSAGE_STOP_VIBRATE = 8;
    
    /** Minimal timeout between accessibility events we want to receive. */
    private static final int EVENT_NOTIFICATION_TIMEOUT_MILLIS = 80;

	/** Packages we are interested in.
     * <p>
     *   <strong>
     *   Note: This code sample will work only on devices shipped with the
     *   default Clock application.
     *   </strong>
     * </p>
     */
    // This works with AlarmClock and Clock whose package name changes in different releases
    private static final String[] PACKAGE_NAMES = new String[] {
            "com.oppo.camera", "com.coloros.contacts"
    };
    
    private final static String LOG_TAG = "MyAccessibility";
    
    Context mContext;
    
    /** The {@link TextToSpeech} used for speaking. */
    private TextToSpeech mTts;
    
    @Override
	public void onCreate() {
    	mContext = getApplicationContext();
		super.onCreate();
	}
    
	/**
	 * 可选。系统会在成功连接上你的服务的时候调用这个方法，在这个方法里你可以做一下初始化工作，
	 * 例如设备的声音震动管理，也可以调用setServiceInfo()进行配置工作。
	 */
	@Override
	protected void onServiceConnected() {
		Toast.makeText(mContext, "Service connected!", Toast.LENGTH_LONG).show();
		setServiceInfo(AccessibilityServiceInfo.FEEDBACK_SPOKEN);
		super.onServiceConnected();
	}

	/**
	 * 可选。在系统将要关闭这个AccessibilityService会被调用。在这个方法中进行一些释放资源的工作。
	 */
	@Override
	public boolean onUnbind(Intent intent) {
		Toast.makeText(mContext, "Service disconnected!", Toast.LENGTH_LONG).show();
		return super.onUnbind(intent);
	}

	/**
	 * 必须。通过这个函数可以接收系统发送来的AccessibilityEvent，
	 * 接收来的AccessibilityEvent是经过过滤的，过滤是在配置工作时设置的。
	 */
	@Override
	public void onAccessibilityEvent(AccessibilityEvent event) {
		Log.i(LOG_TAG, "Event is: " + event.toString());
	}

	/**
	 * 必须。这个在系统想要中断AccessibilityService返给的响应时会调用。
	 * 在整个生命周期里会被调用多次。
	 */
	@Override
	public void onInterrupt() {
		Log.i(LOG_TAG, "onInterrupt");
	}
	
	private void setServiceInfo(int feedbackType) {  
	    AccessibilityServiceInfo info = new AccessibilityServiceInfo();  
	    // We are interested in all types of accessibility events.  
	    info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK; //监听哪些行为 
	    // We want to provide specific type of feedback.  
	    info.feedbackType = feedbackType;  //反馈
	    // We want to receive events in a certain interval.  
	    info.notificationTimeout = EVENT_NOTIFICATION_TIMEOUT_MILLIS;  //通知的时间
	    // We want to receive accessibility events only from certain packages.  
	    info.packageNames = PACKAGE_NAMES;  //监听过滤的包名
	    setServiceInfo(info);  
	}  
	
    
    /** {@link Handler} for executing messages on the service main thread. */
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case MESSAGE_SPEAK:
                    String utterance = (String) message.obj;
//                    mTts.speak(utterance, QUEUING_MODE_INTERRUPT, null);
                    return;
                case MESSAGE_STOP_SPEAK:
//                    mTts.stop();
                    return;
                case MESSAGE_START_TTS:
//                    mTts = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
//                        public void onInit(int status) {
//                            // Register here since to add earcons the TTS must be initialized and
//                            // the receiver is called immediately with the current ringer mode.
//                            registerBroadCastReceiver();
//                        }
//                    });
                    return;
                case MESSAGE_SHUTDOWN_TTS:
//                    mTts.shutdown();
                    return;
                case MESSAGE_PLAY_EARCON:
                    int resourceId = message.arg1;
//                    playEarcon(resourceId);
                    return;
                case MESSAGE_STOP_PLAY_EARCON:
//                    mTts.stop();
                    return;
                case MESSAGE_VIBRATE:
                    int key = message.arg1;
//                    long[] pattern = sVibrationPatterns.get(key);
//                    if (pattern != null) {
//                        mVibrator.vibrate(pattern, -1);
//                    }
                    return;
                case MESSAGE_STOP_VIBRATE:
//                    mVibrator.cancel();
                    return;
            }
        }
    };

}
