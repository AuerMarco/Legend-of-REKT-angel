/**
 * @author Wedenig Manuel
 */
package zeldiablo;

import java.io.Serializable;

public class Currency implements Serializable {
    
    //Currency is called silverserpents
        private int silverserpents;
       
	public Currency()
	{
	silverserpents = 0;
	}
      
	

        public int getSilverserpents()
        {
                //to check out the current amount of silverserpents
                return silverserpents;
        }

        public void setValue(int value)
        {
                silverserpents = value;
        }

	public void changeValue(int value)
	{
		silverserpents = silverserpents + value;
	}



        public void mobdrop(int level)
        {
        	int dropvalue;
        	
               if (level >= 1)
               {
            	   dropvalue = 25 + (level * 5);
                   silverserpents = silverserpents + dropvalue;
               }
         
               else
               {
            	   dropvalue = 0;
               }
               
        }
    
}
