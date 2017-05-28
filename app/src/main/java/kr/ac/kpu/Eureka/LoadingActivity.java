public class LoadingActivity extends Activity {    
	@Override   
	protected void onCreate(@Nullable Bundle savedInstanceState) {        
		super.onCreate(savedInstanceState);       
		setContentView(R.layout.activity_loading);        
		startLoading();    
	}    
	
	private void startLoading(){       
		Handler handler = new Handler();        
		handler.postDelayed(new Runnable() {            
		
			@Override           
			public void run() {                
				Intent intent = new Intent(getApplicationContext(),MainActivity.class);               
				startActivity(intent);                
				finish();            
			}    
		},2500);    
	}
}
