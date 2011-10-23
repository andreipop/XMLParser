package com.android.bbcxmlfeed;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.android.testfeed3.R;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

/**
 * The application's main activity. Handles the onCreate, onResume,
 * refresh and getNews methods - which ultimately display the news items. 
 * 
 * @author Andrei
 *
 */
public class BBCXMLReader extends ListActivity {

    private static final int THREAD_FINISHED = 0;
    private XMLFeed myXMLFeed = null;
    private ProgressDialog progressDialog;
    private Button refreshFeed;
    private boolean resumeHasRun = false;
    //Handler handler = new Handler();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //
        processThread();
        //Get the UI controls - in this case we only have the refresh button.
        initControls();
    }
    /**
     * This method is used to handle all the UI controls. In this case it only deals with the refresh button. 
     */
    public void initControls(){


          refreshFeed = (Button) findViewById(R.id.refresh);

          refreshFeed.setOnClickListener(new Button.OnClickListener(){
              @Override
              public void onClick(View v){
                  processThread();          
              }
          });
    }
    /**
     * Tracks to see if onResume has been called before, 
     * to avoid for both onResume and onCreate to be called. 
     */
    protected void onResume() {
		super.onResume();
		
		if (!resumeHasRun){
			resumeHasRun = true;
			return;
		}
		
		processThread();
	}
    /**
     * This method is used as a background processor for the app. 
     * It shows the progress dialog and it retrieves the news items. 
     * 
     */
    protected void processThread() {
    	//Show a simple progress dialog. 
    	progressDialog = ProgressDialog.show(BBCXMLReader.this, "",
                "Fetching news items...", true, false);
    	//Create a new thread.
    	Thread t = new Thread(){
        	
    		public void run(){
        		//Retrieve the news items from the BBC link.
        		getNews();
        		//Sends message to the handler so it updates the UI.
        		handler.sendMessage(Message.obtain(handler, THREAD_FINISHED));; 
                 }
    
        };
        
        t.start();
        
    }
    /**
     * The handler mainly deals with the UI of the application. 
     * It handles the loading of the ListView with all the items in it, 
     * and also it handles the dismissal of the progress dialog after the UI has been loaded. 
     */
    private Handler handler = new Handler() {
        @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                
                switch (msg.what) {
                case THREAD_FINISHED:
                	 		UI();
                           progressDialog.dismiss();
                           break;    
                    };
                    
            };
    };
    /**
     * This method loads the ListView UI. 
     */
    private void UI(){
    	   if (myXMLFeed!=null){
               ArrayAdapter<XMLItem> adapter =
                    new ArrayAdapter<XMLItem>(this,
                      android.R.layout.simple_list_item_1,myXMLFeed.getList());
                   setListAdapter(adapter);
           }
    }
    /**
     * This method parses the data from the BBC XML link, via the SAX Parser and XMLHandler class.
     * @see XMLHandler
     */
    private void getNews(){
        try {
            URL xmlUrl = new URL("http://feeds.bbci.co.uk/news/uk/rss.xml");
            SAXParserFactory mySAXParserFactory = SAXParserFactory.newInstance();
            SAXParser mySAXParser = mySAXParserFactory.newSAXParser();
            XMLReader myXMLReader = mySAXParser.getXMLReader();
            XMLHandler myXMLHandler = new XMLHandler();
            myXMLReader.setContentHandler(myXMLHandler);
            InputSource myInputSource = new InputSource(xmlUrl.openStream());
            myXMLReader.parse(myInputSource);
            myXMLFeed = myXMLHandler.getFeed(); 
        }
        //catch a bunch of exceptions
        catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

	
	

