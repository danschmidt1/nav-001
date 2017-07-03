package navicure.nav_001.letter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Letter implements Comparable<Letter>{

	private Character theLetter;

	private int firstOccuranceInParagraph;
	private int numberOfOccurancesInParagraph;

	public Letter(char theLetter, int firstOccuranceInParagraph) {
		this.theLetter = theLetter;
		this.firstOccuranceInParagraph = firstOccuranceInParagraph;
		setNumberOfOccurancesInParagraph(1);
	}

	public int getFirstOccuranceInParagraph() {
		return firstOccuranceInParagraph;
	}

	public void setFirstOccuranceInParagraph(int rankInParagraph) {
		this.firstOccuranceInParagraph = rankInParagraph;
	}

	public Character getTheLetter() {
		return theLetter;
	}

	public void setTheLetter(Character theLetter) {
		this.theLetter = theLetter;
	}

	public int getNumberOfOccurancesInParagraph() {
		return numberOfOccurancesInParagraph;
	}

	public void setNumberOfOccurancesInParagraph(int numberOfOccurancesInParagraph) {
		this.numberOfOccurancesInParagraph = numberOfOccurancesInParagraph;
	}

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Letter)) {
            return false;
        }

        Letter rhs = (Letter) obj;
        return new EqualsBuilder()
                .append(theLetter, rhs.theLetter)
                .isEquals();
    }
	
	public int hashCode() {
		// you pick a hard-coded, randomly chosen, non-zero, odd number
		// ideally different for each class
		return new HashCodeBuilder(17, 37).append(numberOfOccurancesInParagraph)
				.append(firstOccuranceInParagraph)
				.append(theLetter).toHashCode();
	}

	@Override
	public int compareTo(Letter o) {
		return this.firstOccuranceInParagraph - o.firstOccuranceInParagraph;
	}

	public String toString() {
		return String.valueOf(this.theLetter);
	}
}
