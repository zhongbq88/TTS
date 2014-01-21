package com.example.tts;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.Menu;

public class MainActivity extends Activity {
	
	boolean ttsIsinit = false;
	TextToSpeech tts = null;
	
	private static int TTS_DATA_CHECK =1;
	
	private void initTextToSpeech(){
		Intent intent = new Intent(android.speech.tts.TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(intent, TTS_DATA_CHECK);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode==TTS_DATA_CHECK){
			if(resultCode==android.speech.tts.TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
				 tts = new TextToSpeech(this, new OnInitListener() {
						
						@Override
						public void onInit(int arg0) {
							if(arg0 ==TextToSpeech.SUCCESS){
								ttsIsinit = true;
								speak();
							}
							
						}
					});
			}else{
				Intent intent = new Intent(android.speech.tts.TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(intent);
			}
		}
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextToSpeech();
    }
    
    
    
    
    private void speak(){
    	if(tts!=null){
    		tts.speak(" Hello ÄãºÃ tts ²âÊÔ £¬ÄãºÃÂð¹þ¹þ", TextToSpeech.QUEUE_ADD, null);
    	}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    protected void onDestroy() {
    	if(tts!=null){
    		tts.stop();
    		tts.shutdown();
    	}
    	super.onDestroy();
    }
    
}
