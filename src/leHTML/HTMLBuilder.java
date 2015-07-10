package leHTML;

public class HTMLBuilder 
{
	/*
	 *
	 * HOW TO USE:
	 * 		Instantiate object first, the constructor will call the 'startDocument' method by default.
	 * 
	 * 		Make sure you call the methods in a way that you want the nutrition label to look.
	 * 
	 * 		Then, when you're done, call the 'finishDocument' method to add the last bit of HTML to the string.
	 * 
	 * 		To receive the HTML string call either 'getHTMLStringBuilder' or 'getHTMLString'.  They're pretty self explanatory
	 * 		of what they return.
	 * 
	 * 		Simple as that.
	 * 
	 * WHAT TO LOOK OUT FOR:
	 * 		Follow this link: http://www.nutritionix.com/search/item/5266a2359f05a39eb300fc90
	 * 			Notice how there are no Vitamins, Calcium or Iron lines?  All you have to do is not call those methods and
	 * 			it won't be built.  But don't forget to add a large bar at the end 'setBar1()' if you don't have Vitamin
	 * 			entries.
	 * 
	 * 			If you do need those, call 'setExtras()' and give it proper values (IN THAT ORDER!).  You DON'T have to add 
	 * 			a large bar at the end after the Vitamin entries.
	 * 
	 * 		Follow this link: http://www.nutritionix.com/search/item/547dd3d33055844701f634ae
	 * 			Notice after there is "(134g)" on the serving line.  Some labels don't have this.  There will be an override method with an
	 * 			extra parameter 'measurement'.
	 * 
	 * 			Also notice how under the input box you see a "Servings per container 5".  Some labels don't have this.
	 * 			There will be an override method for this as well 'setServing()'.
	 * 
	 * 			Also note that "Calories from Fat" isn't always there on some labels.  Call overloaded 'setCalories' with an extra parameter.
	 * 
	 * 		In my 'setServing()' method, you will need to give it the default serving value that is in the input box on the website.
	 * 
	 * 		Last things:
	 * 			For the main lines (ex. Protein, Total Fat, Calories) there are a few main types of lines:
	 * 				Line 1: Bold, not italic, not indented.
	 * 				Line 2: Bold, not italic, not indented, percent.
	 * 				Line 3: Not Bold, not italic, indented.
	 * 				Line 4: Not Bold, not italic, indented, percent.
	 * 				Line 5: Not Bold, italic, indented.
	 * 				Line 6: Not Bold, italic, indented, percent.
	 * 
	 * 				The only line that uses the italics is "Trans Fat 0g". 
	 * 
	 * 			IMPORTANT:  For each main line, you will have to provide a name, number, measurement.  There will be an override methods that
	 * 						you will use if there is a percent at the end of the line, if the line is indented, or if the line is indented and 
	 * 						italicized, etc. 
	 * 
	 * 
	 * EXTRA NOTES:
	 * 		Please use numbers that are rounded to the tenth place if they aren't integers.  It will minimize possible bugs.
	 * 
	 */
	StringBuilder html = new StringBuilder();
	
	public HTMLBuilder()
	{
		this.startDocument();
	}
	
	public void startDocument()
	{
		String styleSheet = getClass().getResource("/leHTML/nutritionLabelStyles.css").toString();
		String javascript = getClass().getResource("/leHTML/nutritionLabelScripts.js").toString();
		
		html.append("<html><head><link rel=\"stylesheet\" href=\"" + styleSheet + "\"/><script type=\"text/javascript\" "
				+ "src=\"" + javascript + "\"></script></head><body onload=\"pageLoad()\"><div class=\"nutritionLabel\" "
				+ "style=\"width: 283px;\"><div class=\"title\">Nutrition Facts</div>");
	}
	
	public void setTitle(String title)
	{
		html.append("<div class=\"name\">" + title + "</div>");
	}
	
	public void setServing(String defaultValue, String serving)
	{
		html.append("<div class=\"serving\"><div class=\"cf\"><div class=\"servingSizeText fl\">Serving Size:</div><div class="
				+ "\"rel servingSizeField fl\"><div class=\"setter\"><a href=\"javascript:increase()\" class=\"unitQuantityUp\""
				+ " rel=\"nofollow\"></a><a href=\"javascript:decrease()\" class=\"unitQuantityDown\" rel=\"nofollow\"></a></di"
				+ "v><input value=\"" + defaultValue + "\" id=\"servingInputBox\" class=\"unitQuantityBox\" type=\"text\" onkey"
				+ "press=\"enter(event)\"></div><div class=\"servingUnit fl unitHasTextbox\">" + serving + "</div></div></div>");
	}
	
	public void setServing(String defaultValue, String serving, String measurement)
	{
		html.append("<div class=\"serving\"><div class=\"cf\"><div class=\"servingSizeText fl\">Serving Size:</div><div class="
				+ "\"rel servingSizeField fl\"><div class=\"setter\"><a href=\"javascript:increase()\" class=\"unitQuantityUp"
				+ "\" rel=\"nofollow\"></a><a href=\"javascript:decrease()\" class=\"unitQuantityDown\" rel=\"nofollow\"></a><"
				+ "/div><input value=\"" + defaultValue + "\" id=\"servingInputBox\" class=\"unitQuantityBox\" type=\"text\" on"
				+ "keypress=\"enter(event)\"></div><div class=\"servingUnit fl unitHasTextbox\">" + serving + "</div><d"
				+ "iv class=\"servingWeightGrams fl gramsHasTextbox\">(" + measurement + ")</div></div></div>\r\n");
	}
	
	//	Incase we have a label that has a 'serving per container' but not a 'measurement'.
	public void setServing(String defaultValue, String serving, int servingsPerContainer)
	{
		html.append("<div class=\"serving\"><div class=\"cf\"><div class=\"servingSizeText fl\">Serving Size:</div><div class=\"rel "
				+ "servingSizeField fl\"><div class=\"setter\"><a href=\"javascript:increase()\" class=\"unitQuantityUp\" rel=\"nofo"
				+ "llow\"></a><a href=\"javascript:decrease()\" class=\"unitQuantityDown\" rel=\"nofollow\"></a></div><input value=\""
				+ "" + defaultValue + "\" id=\"servingInputBox\" class=\"unitQuantityBox\" type=\"text\" onkeypress=\"enter(event)\">"
				+ "</div><div class=\"servingUnit fl unitHasTextbox\">" + serving + "</div></div><div>ServingPerContainer " 
				+ servingsPerContainer + "</div></div>");
	}
	
	public void setServing(String defaultValue, String serving, String measurement, String servingsPerContainer)
	{
		html.append("<div class=\"serving\"><div class=\"cf\"><div class=\"servingSizeText fl\">Serving Size:</div><div class=\""
				+ "rel servingSizeField fl\"><div class=\"setter\"><a href=\"javascript:increase()\" class=\"unitQuantityUp\" rel"
				+ "=\"nofollow\"></a><a href=\"javascript:decrease()\" class=\"unitQuantityDown\" rel=\"nofollow\"></a></div><inp"
				+ "ut value=\"" + defaultValue + "\" id=\"servingInputBox\" class=\"unitQuantityBox\" type=\"text\" onkeypress=\""
				+ "enter(event)\"></div><div class=\"servingUnit fl unitHasTextbox\">" + serving + "</div><div class=\"se"
				+ "rvingWeightGrams fl gramsHasTextbox\">(" + measurement + ")</div></div><div>Servings Per Conta"
				+ "iner " + servingsPerContainer + "</div></div>");
	}
	
	public void setAmountPerServing()
	{
		html.append("<div class=\"line m\"><b>Amount Per Serving</b></div>");
	}
	
	public void setCalories(String calories)
	{
		html.append("<div class=\"line\"><div><b>Calories</b>" + calories + "</div></div>");
	}
	
	public void setCalories(String calories, String caloriesFromFat)
	{
		html.append("<div class=\"line\"><div class=\"fr\">Calories from Fat <span class=\"numberToChange\">" + caloriesFromFat 
				+ "</span></div><div><b>Calories</b> <span class=\"numberToChange\">" + calories + "</span></div></div>");
	}
	
	public void setDailyValue()
	{
		html.append("<div class=\"line ar\"><b>% Daily Value<sup>*</sup></b></div>");
	}
	
	public void setBar1()
	{
		html.append("<div class=\"bar1\"></div>");
	}
	
	public void setBar2()
	{
		html.append("<div class=\"bar2\"></div>");
	}
	
	public void setLineBold(String nutrition, String amount, String measurement)
	{
		html.append("<div class=\"line\"><b>" + nutrition + "</b> <span class=\"numberToChange\"> " + amount + "</span>" 
				+ measurement + "</div>");
	}
	
	public void setLineBold(String nutrition, String amount, String measurement, String percent)
	{
		html.append("<div class=\"line\"><div class=\"dv\"><b><span class=\"numberToChange\">" + percent + "</span></b>%</div><b>" 
				+ nutrition + "</b><span class=\"numberToChange\"> " + amount + "</span>" + measurement + "</div>");
	}
	
	public void setLineIndent(String nutrition, String amount, String measurement)
	{
		html.append("<div class=\"line indent\">" + nutrition + "<span class=\"numberToChange\"> " + amount + "</span>" 
				+ measurement + "</div>");
	}
	
	public void setLineIndent(String nutrition, String amount, String measurement, String percent)
	{
		html.append("<div class=\"line indent\"><div class=\"dv\"><b><span class=\"numberToChange\">" + percent + "</span></b>%</div>" 
				+ nutrition + "<span class=\"numberToChange\"> " + amount + "</span>" + measurement + "</div>");
	}
	
	public void setTransFat(String amount, String measurement)
	{
		html.append("<div class=\"line indent\"><i>Trans</i>Fat " + amount + measurement + "</div>");
	}
	
	public void setExtra(String title, String percent)
	{
		html.append("<div class=\"line\"><div class=\"dv\"><span class=\"numberToChange\">60\\" + percent + "</span>%</div>" 
				+ title + "</div>");
	}
	
	public void finishDocument()
	{
		html.append("<div class=\"dvCalorieDiet line\"><div class=\"calorieNote\"><span class=\"star\">*</span>Percent Daily Values "
				+ "are based on a 2000 calorie diet.</div></div></div></body></html>");
	}
	
	public StringBuilder getHTMLStringBuilder()
	{
		return html;
	}
	
	public String getHTMLString()
	{
		return html.toString();
	}
}
