class SavedRollEntry {
    String title;
    String summary;
    int[][] diceData;
    int additionalModifier;
    int startIndex;

    public SavedRollEntry(String title, String summary, int[][] diceData, int additionalModifier, int startIndex) {
        this.title = title;
        this.summary = summary;
        this.diceData = diceData;
        this.additionalModifier = additionalModifier;
        this.startIndex = startIndex;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public int[][] getDiceData() {
        return diceData;
    }

    public int getAdditionalModifier() {
        return additionalModifier;
    }

    public int getStartIndex() {
        return startIndex;
    }

    @Override
    public String toString() {
        return startIndex + ": " + title + "\n(" + summary + ")";
    }
}
