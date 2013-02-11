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

public class AstrologyManager {
	private enum Sign {
		ARIES,
		TAURUS,
		GEMINI,
		CANCER,
		LEO,
		VIRGO,
		LIBRA,
		SCORPIO,
		SAGITTARIUS,
		CAPRICORN,
		AQUARIUS,
		PISCES,
	}
	
	private enum Element {
		FIRE,
		EARTH,
		AIR,
		WATER,
	}
	
	private String[] sign_summaries;
	private String[] element_summaries;
	
	AstrologyManager(String[] sign_summaries, String[] element_summaries) {
		// Populate sign_summaries:
		this.sign_summaries = new String[Sign.values().length];
		for(int i = 0; i < this.sign_summaries.length && i < sign_summaries.length; i++) {
			this.sign_summaries[i] = sign_summaries[i];
		}
		
		// Populate element_summaries:
		this.element_summaries = new String[Element.values().length];
		for(int i = 0; i < this.element_summaries.length && i < element_summaries.length; i++) {
			this.element_summaries[i] = element_summaries[i];
		}
	}
	
	public String calculate(int sign_id) {
		Element element;
		Sign    sign = Sign.values()[sign_id];
		
		// Calculate element:
		if     (  sign == Sign.ARIES  || sign == Sign.LEO     || sign == Sign.SAGITTARIUS) {
			element = Element.FIRE;
		}
		else if(  sign == Sign.TAURUS || sign == Sign.VIRGO   || sign == Sign.CAPRICORN) {
			element = Element.EARTH;
		}
		else if(  sign == Sign.GEMINI || sign == Sign.LIBRA   || sign == Sign.AQUARIUS) {
			element = Element.AIR;
		}
		else { // sign == Sign.CANCER || sign == Sign.SCORPIO || sign == Sign.PISCES
			element = Element.WATER;
		}
		
		return createString(sign, element);
	}
	
	private String createString(Sign sign, Element element) {
		return sign_summaries[sign.ordinal()] + "\n\n" +
	           element_summaries[element.ordinal()];
	}
}
