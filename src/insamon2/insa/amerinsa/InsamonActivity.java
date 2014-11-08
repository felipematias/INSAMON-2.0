package insamon2.insa.amerinsa;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class InsamonActivity extends Activity {
    /** Called when the activity is first created. */
	
    
    	MediaPlayer mp;
    
        @Override
		public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.main);
          Button start=(Button)findViewById(R.id.start);
          mp = MediaPlayer.create(getApplicationContext(), R.raw.pok_init);
          startMusic();
          
          start.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
        
                Intent myintent2 = new Intent(InsamonActivity.this,InitActivity.class);
                pauseMusic();
                startActivity(myintent2);

            }
        });
     }     
		
        private void startMusic() {
    		
    		try{
    			mp.start();
    			Toast.makeText(this,"Music Playing", Toast.LENGTH_SHORT).show();
    		}catch(Exception e){
    			Toast.makeText(this, "Erro"+e, Toast.LENGTH_SHORT).show();
    		}
    		
    	}
    	
    	private void pauseMusic() {
    		if (mp.isPlaying()) {
    			mp.pause();
    			Toast.makeText(this, "Music Paused", Toast.LENGTH_SHORT).show();
    		}
    	}
    	
    	@Override
    	public void onDestroy() {
    		// TODO Auto-generated method stub
    		super.onDestroy();
    		if (mp != null) {
    			if (mp.isPlaying()) {
    				mp.stop();
    			}
    			mp.release();
    		}
    	}
}