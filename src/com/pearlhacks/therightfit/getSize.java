package com.pearlhacks.therightfit;

import java.util.List;


public class getSize {
	
	public String getTopSize(List<Top> brandTopSizes, float Bust){
		
		float diffPrev = 0; 
		float diff = 0; 
		float min = 100;
		String Size = ""; 
		for (Top o : brandTopSizes){
			 
			if ( diff <= min){
					diff = min;
					Size = o.Size + Character.toUpperCase(o.Type); 		
			}	
			if (diff > diffPrev)
				return Size; 
			diffPrev = diff; 
		}
		return Size; 
	}
	
	public String getDressSize(List<Dress> brandDressSizes, float Bust, float Waist){
		
		float diffPrev = 0; 
		float diff = 0; 
		float min = 100; 
		String Size = ""; 
		for (Dress o : brandDressSizes){
			if (Waist == 0)
				diff = Math.abs(o.Bust - Bust);
			else if (Bust == 0)
				diff = Math.abs(o.Waist - Waist);
			else
				diff = (float) (Math.pow(o.Waist-Waist,2)+Math.pow(o.Bust-Bust,2));
			if (diff < min){
				diff = min; 
				Size = o.Size + Character.toUpperCase(o.Type); 		
			}
			if (diff > diffPrev)
				return Size; 
			diffPrev = diff; 
		}
		return Size; 
	}
	
	public String getBottomSize(List<Bottom> brandBottomSizes, float Hip, float Waist, float Inseam){
		
		float min = 100; 
		float diffPrev = 0; 
		float diff = 0; 
		String Size = ""; 
		for (Bottom o : brandBottomSizes){
			if (o.Waist == 0)
				diff = (float) (Math.pow(o.Inseam-Inseam,2)+Math.pow(o.Hip-Hip,2));
			else if (o.Hip == 0)
				diff = (float) (Math.pow(o.Waist-Waist,2)+Math.pow(o.Inseam-Inseam,2));
			else if (o.Inseam == 0)
				diff = (float) (Math.pow(o.Waist-Waist,2)+Math.pow(o.Hip-Hip,2));
			else 
				diff = (float) (Math.pow(o.Waist-Waist,2)+Math.pow(o.Hip-Hip,2)+Math.pow(o.Inseam-Inseam,2));
			if (diff < min){
				diff = min; 
				Size = o.Size + Character.toUpperCase(o.Type); 		
			}
			if (diff > diffPrev)
				return Size; 
			diffPrev = diff; 
		}
		return Size; 
	}

}
