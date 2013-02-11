/* Astrolo9 - An android astrology informer.
 * Copyright (C) 2013  Ivan Rubinson
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package soryy708.a9;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Spinner sign_spinner  = (Spinner) findViewById(R.id.sign_selection);
        
        // Construct sign spinner:
        sign_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        	public void onItemSelected(AdapterView<?> arg0, View v, int position, long id) {
        		changeBackground();
        	}
        	public void onNothingSelected(AdapterView<?> arg0) {
        		
        	}
        });

        // Populate sign spinners:
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sign_names, android.R.layout.simple_spinner_item); // Get sign names string array
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Adapt the string array (fetched above) for initialisation of the spinners
        sign_spinner.setAdapter(adapter);
        
        changeBackground();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    private void changeBackground() {
    	Spinner   sign_spinner = (Spinner)   findViewById(R.id.sign_selection);
    	ImageView bg_image     = (ImageView) findViewById(R.id.bg_image);
    	int[] image_resources = {
    		R.drawable.aries_wpp,
    		R.drawable.taurus_wpp,
    		R.drawable.gemini_wpp,
   			R.drawable.cancer_wpp,
   			R.drawable.leo_wpp,
   			R.drawable.virgo_wpp,
   			R.drawable.libra_wpp,
   			R.drawable.scorpio_wpp,
   			R.drawable.sagittarius_wpp,
   			R.drawable.capricorn_wpp,
   			R.drawable.aquarius_wpp,
   			R.drawable.pisces_wpp,
    	};
    	
    	bg_image.setImageResource(image_resources[sign_spinner.getSelectedItemPosition()]);
    }
    
    public void calculate(View view) {
    	Spinner sign_spinner  = (Spinner) findViewById(R.id.sign_selection);
    	
    	AstrologyManager astrology = new AstrologyManager(this.getResources().getStringArray(R.array.sign_summaries), this.getResources().getStringArray(R.array.element_summaries));
    	
    	// Create and move to the results screen:
    	Intent intent = new Intent(this, DisplayResultsActivity.class);
    	intent.putExtra("soryy708.a9.MESSAGE", astrology.calculate(sign_spinner.getSelectedItemPosition())); // Pass the new intent the calculations made by the astrology manager
    	startActivity(intent);
    }
}
