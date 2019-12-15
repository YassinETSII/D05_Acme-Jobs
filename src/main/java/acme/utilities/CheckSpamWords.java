
package acme.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import acme.entities.customisation.Customisation;

public class CheckSpamWords {

	public static boolean isSpam(final String inputString, final Customisation configuration) {
		boolean res;
		String[] configurationWords = configuration.getSpamWords().split("\\s*,\\s*");
		Double threshold = configuration.getSpamThreshold();
		String inputWords = inputString.toLowerCase();
		int count = 0;

		for (String spamWord : configurationWords) {
			Pattern p = Pattern.compile(spamWord);
			Matcher m = p.matcher(inputWords);
			while (m.find()) {
				count++;
			}
		}
		if (CheckSpamWords.countWordsUsingSplit(inputWords) != 0 && count * 100 / CheckSpamWords.countWordsUsingSplit(inputWords) >= threshold) {
			res = true;
		} else {
			res = false;
		}

		return res;
	}

	public static int countWordsUsingSplit(final String input) {
		if (input == null || input.isEmpty()) {
			return 0;
		}

		String[] words = input.split("\\s+");
		return words.length;
	}
}
